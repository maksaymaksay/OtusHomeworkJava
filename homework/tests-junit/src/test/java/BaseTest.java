import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import webDriverFactory.WDFactory;

import static java.time.Duration.ofSeconds;
import static webDriverFactory.Browsers.getBrowserByStringName;

public class BaseTest {

    protected WebDriver driver;
    private org.apache.logging.log4j.Logger logger = LogManager.getLogger(BaseTest.class);

    @Before
    public void startUp() {
        driver = WDFactory.create(getBrowserByStringName(System.getProperty("browser")));
        driver.manage().timeouts().implicitlyWait(ofSeconds(5));
        logger.info("Драйвер поднят");
    }

    @After
    public void end() {
        if (driver != null) {
            driver.quit();
        }
    }
}
