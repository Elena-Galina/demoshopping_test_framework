package driver;

import config.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    private final Configuration configuration;

    public DriverFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    public WebDriver createDriver() {
        String browser = configuration.getBrowser();

        switch (browser) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();

                if (configuration.isHeadless()) {
                    options.addArguments("--headless");
                }
                return new ChromeDriver(options);
            default:
                throw new IllegalArgumentException("Неподдерживаемый браузер: " + browser);
        }
    }
}
