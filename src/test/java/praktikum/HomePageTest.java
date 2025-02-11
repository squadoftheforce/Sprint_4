package praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class HomePageTest {
    private WebDriver driver;
    private final String textOfInnerLine;
    private final String indexOfLine;

    public HomePageTest(String textOfInnerLine, String indexOfLine) {
        this.textOfInnerLine = textOfInnerLine;
        this.indexOfLine = indexOfLine;
    }

    @Parameterized.Parameters
    public static Object[][] getTextOfLine() {
        // 1. Название строк внутри выпадающего списка,
        // 2. Порядковый индекс строки
        return new Object[][]{
                {"Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
                        "1"},
                {"Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
                        "2"},
                {"Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
                        "3"},
                {"Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
                        "4"},
                {"Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
                        "5"},
                {"Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
                        "6"},
                {"Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
                        "7"},
                {"Да, обязательно. Всем самокатов! И Москве, и Московской области.",
                        "8"},
        };
    }

    // создаем драйвер для браузера Chrome
    @Before
    public void openBrowser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    //Проверяем, что при открытии выпадающего списка отображается соответствующий текст
    @Test
    public void checkDropDownList(){
        //Переходим на страницу тестового приложения
        driver.get("https://qa-scooter.praktikum-services.ru/");

        HomePage objHomePage = new HomePage(driver);

        //Закрываем строку про куки
        objHomePage.clickCookiesButton();

        //Скроллим до вопросов о важном
        objHomePage.scrollToQuestions();

        //Открываем строку из списка
        By newMainLine = objHomePage.mainLine(indexOfLine);
        objHomePage.clickLineOfList(newMainLine);

        //Проверяем, что открывается соответствующий текст
        By newInnerLine = objHomePage.innerLine(indexOfLine);
        assertEquals("Что-то пошло не так, возможно текст не открылся :(", driver.findElement(newInnerLine).getText(), textOfInnerLine);
    }

    @After
    public void tearDown() {
        // Закрываем браузер
        driver.quit();
    }
}