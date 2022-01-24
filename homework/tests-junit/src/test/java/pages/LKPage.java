package pages;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;

public class LKPage extends AbstractPage {
    private org.apache.logging.log4j.Logger logger = LogManager.getLogger(MainPage.class);
    By mainProfile = By.cssSelector(".ic-blog-default-avatar");
    By myProfileBtn = By.cssSelector(".header2-menu__dropdown_right > a:nth-child(1) > div > b");

    public LKPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private void entryLK() {
        WebDriverWait wait = new WebDriverWait(driver, ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(mainProfile));

        WebElement icon = driver.findElement(mainProfile);
        Actions actions = new Actions(driver);
        actions.moveToElement(icon).build().perform();
        driver.findElement(myProfileBtn).click();
        logger.info("Вход в личный кабинет осуществлен успешно");
    }

    private void fillInPersonalInfoInLK() {
        //ФИО и дата рождения
        driver.findElement(By.id("id_fname")).clear();
        driver.findElement(By.id("id_fname_latin")).clear();
        driver.findElement(By.id("id_lname")).clear();
        driver.findElement(By.id("id_lname_latin")).clear();
        driver.findElement(By.id("id_blog_name")).clear();
        driver.findElement(By.name("date_of_birth")).clear();

        driver.findElement(By.id("id_fname")).sendKeys("Евгения");
        driver.findElement(By.id("id_fname_latin")).sendKeys("Evgeniya");
        driver.findElement(By.id("id_lname")).sendKeys("Максаева");
        driver.findElement(By.id("id_lname_latin")).sendKeys("Maksaeva");
        driver.findElement(By.id("id_blog_name")).sendKeys("maksaymaksay");
        driver.findElement(By.name("date_of_birth")).sendKeys("21.09.1994");
        logger.info("Персональные данные введены успешно");

        //Основная информация
        //Страна
        if (!driver.findElement(By.cssSelector(".js-lk-cv-dependent-master > label:nth-child(1) > div:nth-child(2)"))
                .getText().contains("Россия")) {
            driver.findElement(By.cssSelector(".js-lk-cv-dependent-master > label:nth-child(1) > div:nth-child(2)")).click();
            driver.findElement(By.xpath("//*[contains(text(), 'Россия')]")).click();
        }

        //Город
        if (!driver.findElement(By.cssSelector(".js-lk-cv-dependent-slave-city > label:nth-child(1) > div:nth-child(2)"))
                .getText().contains("Рязань")) {
            driver.findElement(By.cssSelector(".js-lk-cv-dependent-slave-city > label:nth-child(1) > div:nth-child(2)")).click();
            driver.findElement(By.xpath("//*[contains(text(), 'Рязань')]")).click();
        }

        //Уровень английского
        if (!driver.findElement(By.cssSelector("div.container__col.container__col_9.container__col_ssm-12 > div:nth-child(3) > div.container__col.container__col_9.container__col_md-8.container__col_middle > div > label > div"))
                .getText().contains("Средний (Intermediate)")) {
            driver.findElement(By.cssSelector("div.container__col.container__col_9.container__col_ssm-12 > div:nth-child(3) > div.container__col.container__col_9.container__col_md-8.container__col_middle > div > label > div")).click();
            driver.findElement(By.xpath("//*[contains(text(), 'Средний (Intermediate)')]")).click();
        }
        logger.info("Основная информация введена успешно");

        //Контактная информация
        //Skype
//        if (!driver.findElement(By.cssSelector(".placeholder")).getText().contains("Skype")){
//            driver.findElement(By.cssSelector("div.js-formset > button")).click();
//            driver.findElement(By.cssSelector(".placeholder:not(hidden)")).click();
//            driver.findElement(By.xpath("//*[contains(text(), 'Skype')]")).click();
//            driver.findElement(By.xpath("//*[@id=\"id_contact-0-value\"]")).sendKeys("hello");
//        }

        //Facebook
//        driver.findElement(By.cssSelector("div.container__row > div.container__col.container__col_9.container__col_md-8.container__col_sm-12.container__col_border-left.lk-rightbar.print-block.print-wide > div > form > div:nth-child(2) > div.container__col.container__col_12 > div:nth-child(2) > div.js-formset > div > div:nth-child(3) > div.container__col.container__col_9.container__col_ssm-12 > div > div > div > label > div > span")).click();
//        driver.findElement(By.xpath("//*[contains(text(), 'Facebook')]")).click();
//        driver.findElement(By.xpath("//*[@id=\"id_contact-1-value\"]")).sendKeys("hello");
//        logger.info("Контактная информация введена успешно");
    }

    private void clickSaveButton() {
        By saveBtn = By.xpath("//*[contains(text(), 'Сохранить и продолжить')]");
        By successText = By.cssSelector("div.hide-sm.no-print > div > span > span");

        driver.findElement(saveBtn).click();
        WebDriverWait wait = new WebDriverWait(driver, ofSeconds(5));
        wait.until(ExpectedConditions.textToBe(successText, "Данные успешно сохранены"));
        logger.info("Нажата кнопка Сохранить");
    }
}
