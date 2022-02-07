package pages;

import config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static java.time.Duration.ofSeconds;

public class MainPage extends AbstractPage {
    private ServerConfig serverConfig = ConfigFactory.create(ServerConfig.class);
    private org.apache.logging.log4j.Logger logger = LogManager.getLogger(MainPage.class);

    private By loginAndRegistrationButton = By.cssSelector("button.header2__auth.js-open-modal");

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public MainPage openOtus() {
        driver.get(serverConfig.url());
        logger.info("Страница Отуса открыта");
        return this;
    }

    public MainPage openOtusInClearBrowser() {
        driver.quit();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(ofSeconds(5));
        logger.info("Драйвер поднят");
        driver.get(serverConfig.url());
        return this;
    }

    public LoginPage login(){
        driver.findElement(loginAndRegistrationButton).click();
        return new LoginPage(driver);
    }
}
