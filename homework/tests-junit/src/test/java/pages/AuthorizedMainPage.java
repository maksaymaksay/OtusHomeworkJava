package pages;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;

public class AuthorizedMainPage extends MainPage {
    private org.apache.logging.log4j.Logger logger = LogManager.getLogger(AuthorizedMainPage.class);
    By mainProfile = By.cssSelector(".ic-blog-default-avatar");
    By myProfileBtn = By.cssSelector(".header2-menu__dropdown_right > a:nth-child(1) > div > b");

    public AuthorizedMainPage(WebDriver driver) {
        super(driver);
    }

    public LKPage entryLK() {
        WebDriverWait wait = new WebDriverWait(driver, ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(mainProfile));

        WebElement icon = driver.findElement(mainProfile);
        Actions actions = new Actions(driver);
        actions.moveToElement(icon).build().perform();
        driver.findElement(myProfileBtn).click();
        logger.info("Вход в личный кабинет осуществлен успешно");
        return new LKPage(driver);
    }
}
