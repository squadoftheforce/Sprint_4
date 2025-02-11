package praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class OrderPage {
    private final WebDriver driver;

    //Поле ввода имени
    private final By name = By.xpath(".//input[@placeholder='* Имя']");

    //Поле ввода фамилии
    private final By surname = By.xpath(".//input[@placeholder='* Фамилия']");

    //Поле ввода адреса
    private final By address = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    //Поле ввода станции метро
    private final By undergroundStation = By.xpath(".//input[@placeholder='* Станция метро']");

    //Поле ввода телефона
    private final By phone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    //Кнопка Далее
    private final By nextButton = By.xpath(".//button[contains(text(),'Далее')]");

    //Поле ввода даты
    private final By dateOfOrder = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    //Поле ввода срока аренды
    private final By rentalPeriod = By.xpath(".//div[@class='Dropdown-placeholder']");

    //Поле ввода комментария курьеру
    private final By commentToCourier = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    //Кнопка Заказать
    private final By orderyButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    //Кнопка Да
    private final By yesButton = By.xpath(".//button[contains(text(),'Да')]");

    //Заказ оформлен
    private final By orderIsPlaced = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //Вводим имя
    public void setName(String name){
        driver.findElement(this.name).sendKeys(name);
    }

    //Вводим фамилию
    public void setSurname(String surname) {
        driver.findElement(this.surname).sendKeys(surname);
    }

    //Вводим адрес
    public void setAddress(String address) {
        driver.findElement(this.address).sendKeys(address);
    }

    //Выбираем станцию метро из списка
    public void setUndergroundStation(String indexOfUnderground) {
        driver.findElement(this.undergroundStation).click();
        driver.findElement(By.xpath(".//button[@value='" + indexOfUnderground + "']//div[1]")).click();
    }

    //Вводим телефон
    public void setPhone(String phone) {
        driver.findElement(this.phone).sendKeys(phone);
    }

    //Нажимаем кнопку Далее
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    //Заполняем данные на странице ввода получателя и нажимаем Далее
    public void setRecipientsDetails(String name, String surname, String address, String phone, String indexOfUnderground){
        setName(name);
        setSurname(surname);
        setAddress(address);
        setPhone(phone);
        setUndergroundStation(indexOfUnderground);
        clickNextButton();
    }

    //Вводим дату доставки
    public void setDateOfOrder(String dateOfOrder) {
        driver.findElement(this.dateOfOrder).click();
        driver.findElement(By.xpath(".//div[contains(@class,'react-datepicker__day') and text()='"+ dateOfOrder + "']")).click();
    }

    //Вводим срок аренды
    public void setRentalPeriod(String rentalPeriod) {
        driver.findElement(this.rentalPeriod).click();
        driver.findElement(By.xpath(".//div[@class='Dropdown-menu']//div[" + rentalPeriod + "]")).click();
    }

   //Выбираем цвет самоката
    public void setColorOfScooter(String color) {
        driver.findElement(By.xpath(".//label[contains(text(),'" + color + "')]")).click();
    }

    //Вводим комментарий курьеру
    public void setCommentToCourier(String commentToCourier) {
        driver.findElement(this.commentToCourier).sendKeys(commentToCourier);
    }

    //Нажимаем кнопку "Заказать"
    public void clickOrderButton() {
        driver.findElement(orderyButton).click();
    }

    //Нажимаем кнопку "Да"
    public void clickYesButton() {
        driver.findElement(yesButton).click();
    }

    //Заполняем данные по аренде и нажимаем "Заказать" и "Да"
    public void setRentalDetails(String dateOfOrder, String rentalPeriod, String commentToCourier, String color){
        setDateOfOrder(dateOfOrder);
        setRentalPeriod(rentalPeriod);
        setCommentToCourier(commentToCourier);
        setColorOfScooter(color);
        clickOrderButton();
        clickYesButton();
    }

    public By textOfOrder(){
        return orderIsPlaced;
    }
}
