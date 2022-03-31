package duckDuckGoPages;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import otusPages.AbstractPage;

public class DuckDuckSearchPage extends AbstractPage {
    private org.apache.logging.log4j.Logger logger = LogManager.getLogger(DuckDuckSearchPage.class);
    private By firstElement = By.cssSelector("#r1-0 > div > h2 > a.result__a.js-result-title-link");

    public DuckDuckSearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public WebElement getFirstElement (){
        WebElement element = driver.findElement(firstElement);
        return element;
    }
}
