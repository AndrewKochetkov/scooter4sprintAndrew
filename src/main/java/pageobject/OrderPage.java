package pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private final WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Поля формы заказа
    private final By firstNameInput = By.xpath("//input[@placeholder='* Имя']");
    private final By lastNameInput = By.xpath("//input[@placeholder='* Фамилия']");
    private final By addressInput = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroStationInput = By.xpath("//input[@placeholder='* Станция метро']");
    private final By phoneInput = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath("//button[contains(text(),'Далее')]");

    // Методы для заполнения формы
    public void fillOrderForm(String firstName, String lastName, String address, String metro, String phone) {
        // Ввод имени
        driver.findElement(firstNameInput).sendKeys(firstName);
        // Ввод фамилии
        driver.findElement(lastNameInput).sendKeys(lastName);
        // Ввод адреса
        driver.findElement(addressInput).sendKeys(address);

        // Ввод станции метро и выбор из выпадающего списка
        WebElement metroInput = driver.findElement(metroStationInput);
        metroInput.sendKeys(metro); // Ввод названия станции
        metroInput.sendKeys(Keys.ARROW_DOWN); // Перемещение вниз в списке
        metroInput.sendKeys(Keys.ENTER); // Подтверждение выбора

        // Ввод номера телефона
        driver.findElement(phoneInput).sendKeys(phone);

        // Клик по кнопке "Далее"
        driver.findElement(nextButton).click();
    }
}