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
                    options.addArguments("--window-size=1920,1080");
                }
                return new ChromeDriver(options);
            default:
                throw new IllegalArgumentException("Неподдерживаемый браузер: " + browser);
        }
    }

    public static void log(String str) {
        System.out.println(str);
    }

    public static void logf(String str, Object... arg) {
        log(String.format(str, arg));
    }
}
