package webDriverFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class WDFactory {

    public static WebDriver create(Browsers webDriverName) {
        switch (webDriverName) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            case OPERA:
                WebDriverManager.operadriver().setup();
                return new OperaDriver();
            case SAFARI:
                WebDriverManager.safaridriver().setup();
                return new SafariDriver();
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            default:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
        }
    }

    public static WebDriver create(Browsers webDriverName, MutableCapabilities options) {
        switch (webDriverName) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver((ChromeOptions) options);
            case OPERA:
                WebDriverManager.operadriver().setup();
                return new OperaDriver((OperaOptions) options);
            case SAFARI:
                WebDriverManager.safaridriver().setup();
                return new SafariDriver((SafariOptions) options);
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver((FirefoxOptions) options);
            default:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver((ChromeOptions) options);
        }
    }
}
