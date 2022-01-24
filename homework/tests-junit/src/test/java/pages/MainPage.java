package pages;

import config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends AbstractPage {
    private ServerConfig serverConfig = ConfigFactory.create(ServerConfig.class);
    private org.apache.logging.log4j.Logger logger = LogManager.getLogger(MainPage.class);

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private void openOtus() {
        driver.get(serverConfig.url());
        logger.info("Страница Отуса открыта");
    }
}
