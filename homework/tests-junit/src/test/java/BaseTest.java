import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import webDriverFactory.Browsers;
import webDriverFactory.WDFactory;

import static java.time.Duration.ofSeconds;

public class BaseTest {

    protected WebDriver driver;
    private org.apache.logging.log4j.Logger logger = LogManager.getLogger(OldTests.class);

    @Before
    public void startUp() {
        driver = WDFactory.create(Browsers.CHROME);
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
