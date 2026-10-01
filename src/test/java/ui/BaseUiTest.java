package ui;

import config.Configuration;
import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class BaseUiTest {

    private WebDriver driver;

    private void startDriver() {
        DriverFactory driverFactory = new DriverFactory(new Configuration());
        driver = driverFactory.createDriver();
    }

    protected WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    protected void beforeMethod(Method method) {
        DriverFactory.logf("Run %s.%s", this.getClass().getName(), method.getName());

        DriverFactory.log("Open the browser");
        startDriver();
    }

    @AfterMethod
    protected void afterMethod(Method method, ITestResult testResult) {
        if (driver != null) {
            driver.quit();

            driver = null;
        }

        DriverFactory.logf("Execution time is %.3f sec", (testResult.getEndMillis() - testResult.getStartMillis()) / 1000.0);
    }
}
