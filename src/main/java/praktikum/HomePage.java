package praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private final WebDriver driver;

    //Кнопка закрытия кук
    private final By cookiesButton = By.xpath(".//button[@id='rcc-confirm-button']");

    //Верхняя кнопка "Заказать"
    private final By orderButtonFromTop = By.xpath(".//button[@class='Button_Button__ra12g']");

    //Нижняя кнопка "Заказать"
    private final By orderButtonFromBottom = By.xpath(".//div[starts-with(@class, 'Home_RoadMap')]//button[starts-with(@class, 'Button_Button')]");

    //Вопросы о важном
    private final By questionsOfImportant = By.className("Home_FAQ__3uVm4");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    //Строка из выпадающего списка по порядковому номеру
    public By mainLine(String indexOfLine){
        return  By.xpath("(.//div[@class='accordion__item'])[" + indexOfLine + "]");
    }

    //Строка при открытии выпадающего списка по порядковому номеру
    public By innerLine(String indexOfLine){
        return By.xpath("(.//div[@class='accordion__panel'])[" + indexOfLine + "]");
    }

    //Закрыть куки
    public void clickCookiesButton() {
        driver.findElement(cookiesButton).click();
    }

    //Открыть строку из выпадающего списка
    public void clickLineOfList(By mainLine) {
        driver.findElement(mainLine).click();
    }

    //Нажать верхнюю кнопку "Заказать"
    public void clickOrderButtonFromTop() {
        driver.findElement(orderButtonFromTop).click();
    }

    //Нажать нижнюю кнопку "Заказать"
    public void clickOrderButtonFromBottom() {
        driver.findElement(orderButtonFromBottom).click();
    }

    //Скроллим до вопросов о важном
    public void scrollToQuestions() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(questionsOfImportant));
    }
}