import duckDuckGoPages.DuckDuckGoMainPage;
import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import otusPages.MainPage;
import w3layoutsPages.W3layoutsMainPage;
import webDriverFactory.WDFactory;

import java.util.Set;

import static java.time.Duration.ofSeconds;
import static webDriverFactory.Browsers.getBrowserByStringName;

public class FinalProjectBasicTests {
    private org.apache.logging.log4j.Logger logger = LogManager.getLogger(FinalProjectBasicTests.class);
    protected WebDriver driver;

    public void startUp(ChromeOptions chromeOptions){
        driver = WDFactory.create(getBrowserByStringName(System.getProperty("browser")), chromeOptions);
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
    //1. Открыть Chrome в headless режиме
    //2. Перейти на https://duckduckgo.com/
    //3. В поисковую строку ввести ОТУС
    //4. Проверить что в поисковой выдаче первый результат Онлайн‑курсы для профессионалов, дистанционное обучение
    public void checkOtusTitleOnDuckDuckGo(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        startUp(chromeOptions);
        driver.manage().timeouts().implicitlyWait(ofSeconds(5));

        String titleOfFirstElement = new DuckDuckGoMainPage(driver)
                .openDuckDuckGo()
                .enterOtusInSearchField()
                .getFirstElement()
                .getText();

        Assert.assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение...", titleOfFirstElement);
        logger.info("Тест №1 успешно пройден");
    }

    @Test
    //1. Открыть Chrome в режиме киоска
    //2. Перейти на https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818
    //3. Нажать на любую картинку
    //4. Проверить, что картинка открылась в модальном окне
    public void checkPictureOpenInModalWindow(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--kiosk");
        startUp(chromeOptions);
        driver.manage().timeouts().implicitlyWait(ofSeconds(5));

        By modalWindow = new W3layoutsMainPage(driver)
                        .openW3layouts()
                        .chooseThirdPicture()
                        .getModalWindow();

        Assert.assertTrue(driver.findElement(modalWindow).isEnabled());
        logger.info("Тест №2 успешно пройден");
    }

    @Test
    //1. Открыть Chrome в режиме полного экрана
    //2. Перейти на https://otus.ru
    //3. Авторизоваться под каким-нибудь тестовым пользователем(можно создать нового)
    //4. Вывести в лог все cookie
    public void getOtusCookies(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        startUp(chromeOptions);
        driver.manage().timeouts().implicitlyWait(ofSeconds(5));

        new MainPage(driver)
                .openOtus()
                .login()
                .authOtus();

        Set<Cookie> cookies = driver.manage().getCookies();
        logger.info("cookies - " + cookies);
        logger.info("Тест №3 успешно пройден");
    }
}
