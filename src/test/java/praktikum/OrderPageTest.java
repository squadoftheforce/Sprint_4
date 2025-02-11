package praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.containsString;

@RunWith(Parameterized.class)
public class OrderPageTest {
    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final String indexOfUnderground;
    private final String phone;
    private final String indexOfDate;
    private final String indexOfRentalPeriod;
    private final String color;
    private final String commentOfCourier;

    public OrderPageTest(String name, String surname, String address, String indexOfUnderground, String phone, String indexOfDate, String indexOfRentalPeriod, String color, String commentOfCourier) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.indexOfUnderground = indexOfUnderground;
        this.phone = phone;
        this.indexOfDate = indexOfDate;
        this.indexOfRentalPeriod = indexOfRentalPeriod;
        this.color = color;
        this.commentOfCourier = commentOfCourier;
    }
    @Parameterized.Parameters
    public static Object[][] getRecipientsDetails() {
        // Имя, Фамилия, Адрес, Индекс для выбора метро, Номер телефона, Дата доставки, Срок аренды, Цвет самоката, Комментарий курьеру
        return new Object[][]{
                {"Никита","Ерлычков","Рябикова, 116","3","89510979910","24","7","серая безысходность","Класс"},
                {"Павел","Павлов","Варейкиса, 11","8","89297901889","9","1","чёрный жемчуг","Супер"}
        };
    }
    // создали драйвер для браузера Chrome
    @Before
    public void openBrowser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    //Оформление заказа через верхнюю кнопку
    @Test
    public void checkFormOrderFromTopButton() {

        //Переходим на страницу тестового приложения
        driver.get("https://qa-scooter.praktikum-services.ru/");

        HomePage objHomePage = new HomePage(driver);

        //Закрываем строку про куки
        objHomePage.clickCookiesButton();

        //Нажимаем верхнюю кнопку Заказать
        objHomePage.clickOrderButtonFromTop();

        OrderPage objOrderPage = new OrderPage(driver);

        //Вводим данные получателя
        objOrderPage.setRecipientsDetails(name,surname,address,phone,indexOfUnderground);

        //Вводим данные по аренде
        objOrderPage.setRentalDetails(indexOfDate, indexOfRentalPeriod, commentOfCourier, color);

        //нужно проверить, что заказ оформлен (проверка упадет в гугл хроме, так как модальное окно не открывается)
        MatcherAssert.assertThat(driver.findElement(objOrderPage.textOfOrder()).getText(), containsString("Заказ оформлен"));
    }

    //Оформление заказа через нижнюю кнопку
    @Test
    public void checkFormOrderFromBottomButton() {

        //Переходим на страницу тестового приложения
        driver.get("https://qa-scooter.praktikum-services.ru/");

        HomePage objHomePage = new HomePage(driver);

        //Закрываем строку про куки
        objHomePage.clickCookiesButton();

        //Нажимаем нижнюю кнопку Заказать
        objHomePage.clickOrderButtonFromBottom();

        OrderPage objOrderPage = new OrderPage(driver);

        //Вводим данные получателя
        objOrderPage.setRecipientsDetails(name,surname,address,phone,indexOfUnderground);

        //Вводим данные по аренде
        objOrderPage.setRentalDetails(indexOfDate, indexOfRentalPeriod, commentOfCourier, color);

        //нужно проверить, что заказ оформлен (проверка упадет в гугл хроме, так как модальное окно не открывается)
        MatcherAssert.assertThat(driver.findElement(objOrderPage.textOfOrder()).getText(), containsString("Заказ оформлен"));
    }

    @After
    public void tearDown() {
        // Закрой браузер
        driver.quit();
    }
}
