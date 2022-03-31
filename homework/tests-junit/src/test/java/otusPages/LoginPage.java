package otusPages;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage {
    private org.apache.logging.log4j.Logger logger = LogManager.getLogger(LoginPage.class);
    String login = System.getProperty("login");
    String password = System.getProperty("password");
    private By loginField = By.cssSelector("div.new-input-line_slim:nth-child(3) > input:nth-child(1)");
    private By passwordField = By.cssSelector(".js-psw-input");
    private By loginButton = By.cssSelector("div.new-input-line_last:nth-child(5) > button:nth-child(1)");

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public AuthorizedMainPage authOtus() {
        driver.findElement(loginField).sendKeys(login);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).submit();
        logger.info("Авторизация прошла успешно");
        return new AuthorizedMainPage(driver);
    }
}
