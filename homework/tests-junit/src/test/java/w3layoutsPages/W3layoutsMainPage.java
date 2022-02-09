package w3layoutsPages;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import otusPages.AbstractPage;

public class W3layoutsMainPage extends AbstractPage {
    private org.apache.logging.log4j.Logger logger = LogManager.getLogger(W3layoutsMainPage.class);
    private String thirdPicture = "li:nth-child(3) > span > a > div.content-overlay";
    private By modalWindow = By.cssSelector("div.pp_pic_holder.light_rounded");

    public By getModalWindow() {
        return modalWindow;
    }

    public W3layoutsMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public W3layoutsMainPage openW3layouts() {
        driver.get("https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818");
        logger.info("Страница W3layouts открыта");
        return this;
    }

    public W3layoutsMainPage chooseThirdPicture(){
        /*WebElement picture = */driver.findElement(By.cssSelector(thirdPicture)).click();
//        driver.manage().timeouts().implicitlyWait(ofSeconds(5));
//        Actions actions = new Actions(driver);
//        actions.moveToElement(picture).click().build().perform();
        return this;
    }
}
