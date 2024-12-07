package pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RentPage {
    private final WebDriver driver;

    public RentPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By dateField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriodDropdown = By.xpath("//div[@class='Dropdown-placeholder']");
    private final By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private final By confirmYesButton = By.xpath("//button[text()='Да']");
    private final By modal = By.xpath("//div[@class='Order_Modal__YZ-d3']");
    private final By showStatusButton = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM') and text()='Посмотерть статус']");

    public void fillRentForm(String date, String rentalPeriod, String color, String comment) {
        WebElement dateInput = driver.findElement(dateField);
        dateInput.click();
        dateInput.clear();
        dateInput.sendKeys(date);
        dateInput.sendKeys(Keys.ENTER);

        driver.findElement(rentalPeriodDropdown).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement dropdownMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'Dropdown-menu')]")));
        WebElement periodOption = dropdownMenu.findElement(By.xpath("//div[@role='option'][contains(text(), '" + rentalPeriod + "')]"));
        periodOption.click();

        driver.findElement(By.id(color.equals("чёрный жемчуг") ? "black" : "grey")).click();

        driver.findElement(commentField).sendKeys(comment);
        driver.findElement(orderButton).click();
    }

    public void orderModalDisplayed() {
        driver.findElement(modal).isDisplayed();
        driver.findElement(confirmYesButton).click();
    }

    public void orderSuccessfullyPlaced(){
        driver.findElement(showStatusButton).click();
    }
}