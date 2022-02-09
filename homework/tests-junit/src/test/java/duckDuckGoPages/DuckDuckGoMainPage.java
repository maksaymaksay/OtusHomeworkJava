package duckDuckGoPages;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import otusPages.AbstractPage;

public class DuckDuckGoMainPage extends AbstractPage {
    private org.apache.logging.log4j.Logger logger = LogManager.getLogger(DuckDuckGoMainPage.class);
    private By searchField = By.cssSelector("#search_form_input_homepage");
    private By searchBtn = By.cssSelector("#search_button_homepage");

    public DuckDuckGoMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public DuckDuckGoMainPage openDuckDuckGo() {
        driver.get("https://duckduckgo.com/");
        logger.info("Страница DuckDuckGo открыта");
        return this;
    }

    public DuckDuckSearchPage enterOtusInSearchField() {
        driver.findElement(searchField).sendKeys("отус");
        logger.info("В строку поиска введено значение - Отус");
        driver.findElement(searchBtn).click();
        return new DuckDuckSearchPage(driver);
    }
}
