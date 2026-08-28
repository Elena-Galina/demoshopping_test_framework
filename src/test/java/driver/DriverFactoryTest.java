package driver;

import config.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DriverFactoryTest {

    private static final String UNSUPPORTED_BROWSER = "safari";

    @Test
    public void testCreateChromeDriver() {
        Configuration configuration = new Configuration();
        DriverFactory driverFactory = new DriverFactory(configuration);

        WebDriver driver = driverFactory.createDriver();

        try {
            Assert.assertTrue(driver instanceof ChromeDriver, "DriverFactory должен создать ChromeDriver");

        } finally {
            driver.quit();
        }
    }

    @Test(
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Неподдерживаемый браузер: " + UNSUPPORTED_BROWSER
    )
    public void testRejectUnsupportedBrowser() {
        Configuration configuration = new Configuration() {

            @Override
            public String getBrowser() {
                return UNSUPPORTED_BROWSER;
            }
        };

        DriverFactory driverFactory = new DriverFactory(configuration);
        driverFactory.createDriver();
    }
}
