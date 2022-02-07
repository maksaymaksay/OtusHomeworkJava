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
    private org.apache.logging.log4j.Logger logger = LogManager.getLogger(LKPage.class);

    public LKPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public LKPage fillInPersonalInfoInLK() {
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
        final String VK_CONTACT = "id8666983";
        final String TG_CONTACT = "+79209801565";

        if (driver.findElements(By.xpath("//input[@value='" + VK_CONTACT + "']")).isEmpty()) {
            driver.findElement(By.xpath("//button[text()='Добавить']")).click();
            driver.findElement(By.xpath("//span[text()='Способ связи']/..")).click();
            driver.findElement(By.xpath("//span[text()='Способ связи']/../../following-sibling::div/descendant::button[@title='VK']")).click();
            driver.findElement(By.xpath("//div[text()='VK']/../../following-sibling::input")).clear();
            driver.findElement(By.xpath("//div[text()='VK']/../../following-sibling::input")).sendKeys(VK_CONTACT);
        }

        if (driver.findElements(By.xpath("//input[@value='" + TG_CONTACT + "']")).isEmpty()) {
            driver.findElement(By.xpath("//button[text()='Добавить']")).click();
            driver.findElement(By.xpath("//span[text()='Способ связи']/..")).click();
            driver.findElement(By.xpath("//span[text()='Способ связи']/../../following-sibling::div/descendant::button[@title='Тelegram']")).click();
            driver.findElement(By.xpath("//div[text()='Тelegram']/../../following-sibling::input")).clear();
            driver.findElement(By.xpath("//div[text()='Тelegram']/../../following-sibling::input")).sendKeys(TG_CONTACT);
        }
        return this;
    }

    public LKPage clickSaveButton() {
        By saveBtn = By.xpath("//*[contains(text(), 'Сохранить и продолжить')]");
        By successText = By.cssSelector("div.hide-sm.no-print > div > span > span");

        driver.findElement(saveBtn).click();
        WebDriverWait wait = new WebDriverWait(driver, ofSeconds(5));
        wait.until(ExpectedConditions.textToBe(successText, "Данные успешно сохранены"));
        logger.info("Нажата кнопка Сохранить");
        return this;
    }

    public MainPage goMainPage(){
        By otusLogo = By.cssSelector("div > div.header2__logo > a");
        driver.findElement(otusLogo);
        WebDriverWait wait = new WebDriverWait(driver, ofSeconds(2));
        logger.info("Осуществлен переход на главную страницу");
        return new MainPage(driver);
    }
}
