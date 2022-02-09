import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import otusPages.LKPage;
import otusPages.MainPage;

public class NewTests extends BaseTest {
    //1. Открыть сайт Otus
    //2. Авторизоваться на сайте Отус
    //3. Перейти в личный кабинет
    //4. В разделе "О себе" заполнить все поля "Личные данные" и добавить не менее двух контактов
    //5. Нажать Сохранить
    //6. Открыть сайт Отуса в "чистом" браузере
    //7. Авторизоваться на сайте Отус
    //8. Войти в Личный кабинет
    //9. Проверить, что в разделе О себе отображаются ранее введенные данные

    @Test
    public void homeworkFive() {
        org.apache.logging.log4j.Logger logger = LogManager.getLogger(LKPage.class);
        LKPage lkPage = new MainPage(driver)
                .openOtus()
                .login()
                .authOtus()
                .entryLK()
                .fillInPersonalInfoInLK()
                .clickSaveButton()
                .goMainPage()
                .openOtusInClearBrowser()
                .login()
                .authOtus()
                .entryLK();

        this.driver = lkPage.getDriver();

        Assert.assertEquals("Евгения", driver.findElement(By.id("id_fname")).getAttribute("value"));
        Assert.assertEquals("Evgeniya", driver.findElement(By.id("id_fname_latin")).getAttribute("value"));
        Assert.assertEquals("Максаева", driver.findElement(By.id("id_lname")).getAttribute("value"));
        Assert.assertEquals("Maksaeva", driver.findElement(By.id("id_lname_latin")).getAttribute("value"));
        Assert.assertEquals("maksaymaksay", driver.findElement(By.id("id_blog_name")).getAttribute("value"));
        Assert.assertEquals("21.09.1994", driver.findElement(By.name("date_of_birth")).getAttribute("value"));
        logger.info("ФИО и дата рождения проверены");

        Assert.assertEquals("Россия", driver.findElement(By.cssSelector(".js-lk-cv-dependent-master > label:nth-child(1) > div:nth-child(2)")).getText());
        Assert.assertEquals("Рязань", driver.findElement(By.cssSelector(".js-lk-cv-dependent-slave-city > label:nth-child(1) > div:nth-child(2)")).getText());
        Assert.assertEquals("Средний (Intermediate)", driver.findElement(By.cssSelector("div.container__col.container__col_9.container__col_ssm-12 > div:nth-child(3) > div.container__col.container__col_9.container__col_md-8.container__col_middle > div > label > div")).getText());
        logger.info("Основная информация проверена");

        Assert.assertEquals("id8666983", driver.findElement(By.xpath("//input[@value='id8666983']")).getAttribute("value"));
        Assert.assertEquals("+7 920 980-15-65", driver.findElement(By.xpath("//input[@value='+79209801565']")).getAttribute("value"));
        logger.info("Контактная информация проверена");
        logger.info("Все персональные данные успешно проверены");
    }
}
