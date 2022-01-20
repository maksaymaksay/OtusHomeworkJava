import config.ServerConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;

public class Tests {
    private org.apache.logging.log4j.Logger logger = LogManager.getLogger(Tests.class);
    protected static WebDriver driver;
    private ServerConfig serverConfig = ConfigFactory.create(ServerConfig.class);

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(ofSeconds(5));
        logger.info("Драйвер поднят");
    }

    @After
    public void end() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void fillOtusPageAboutYourself() {
        //1. Открыть сайт Otus
        openOtus();

        //2. Авторизоваться на сайте Отус
        authOtus();

        //3. Перейти в личный кабинет
        entryLK();

        //4. В разделе "О себе" заполнить все поля "Личные данные" и добавить не менее двух контактов
        fillInPersonalInfoInLK();

        //5. Нажать Сохранить
        clickSaveButton();

        //6. Открыть сайт Отуса в "чистом" браузере
        openOtusInClearBrowser();

        //7.Авторизоваться на сайте Отус
        authOtus();

        //8. Войти в Личный кабинет
        entryLK();

        //9. Проверить, что в разделе О себе отображаются ранее введенные данные
        Assert.assertEquals("Евгения", driver.findElement(By.id("id_fname")).getAttribute("value"));
        Assert.assertEquals("Evgeniya", driver.findElement(By.id("id_fname_latin")).getAttribute("value"));
        Assert.assertEquals("Максаева", driver.findElement(By.id("id_lname")).getAttribute("value"));
        Assert.assertEquals("Maksaeva", driver.findElement(By.id("id_lname_latin")).getAttribute("value"));
        Assert.assertEquals("maksaymaksay", driver.findElement(By.id("id_blog_name")).getAttribute("value"));
        Assert.assertEquals("21.09.1994", driver.findElement(By.name("date_of_birth")).getAttribute("value"));

        Assert.assertEquals("Россия", driver.findElement(By.cssSelector(".js-lk-cv-dependent-master > label:nth-child(1) > div:nth-child(2)")).getText());
        Assert.assertEquals("Рязань", driver.findElement(By.cssSelector(".js-lk-cv-dependent-slave-city > label:nth-child(1) > div:nth-child(2)")).getText());
        Assert.assertEquals("Средний (Intermediate)", driver.findElement(By.cssSelector("div.container__col.container__col_9.container__col_ssm-12 > div:nth-child(3) > div.container__col.container__col_9.container__col_md-8.container__col_middle > div > label > div")).getText());

        // TODO: 20.01.2022 После того, как разберусь с контактной информацией - дописать ассерты на контактную информацию

        logger.info("Персональные данные проверены");
    }

    private void openOtus() {
        driver.get(serverConfig.url());
        logger.info("Страница Отуса открыта");
    }

    private void openOtusInClearBrowser() {
        driver.quit();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(ofSeconds(5));
        logger.info("Драйвер поднят");
        driver.get(serverConfig.url());
    }

    private void authOtus() {
        String login = System.getProperty("login");
        String password = System.getProperty("password");
        String loginAndRegistrationButton = "button.header2__auth.js-open-modal";
        String loginField = "div.new-input-line_slim:nth-child(3) > input:nth-child(1)";
        String passwordField = ".js-psw-input";
        String loginButton = "div.new-input-line_last:nth-child(5) > button:nth-child(1)";

        driver.findElement(By.cssSelector(loginAndRegistrationButton)).click();
        driver.findElement(By.cssSelector(loginField)).sendKeys(login);
        driver.findElement(By.cssSelector(passwordField)).sendKeys(password);
        driver.findElement(By.cssSelector(loginButton)).submit();
        logger.info("Авторизация прошла успешно");
    }

    private void entryLK() {
        By mainProfile = By.cssSelector(".ic-blog-default-avatar");
        By myProfileBtn = By.cssSelector(".header2-menu__dropdown_right > a:nth-child(1) > div > b");

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
