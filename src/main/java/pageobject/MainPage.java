package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class MainPage {
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By topOrderButton = By.xpath("//button[text()='Заказать' and @class='Button_Button__ra12g']");
    private final By bottomOrderButton = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM') and text()='Заказать']");


    public void clickTopOrderButton() {
        driver.findElement(topOrderButton).click();
    }

    public void clickBottomOrderButton() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(bottomOrderButton));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(bottomOrderButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        button.click();
    }


    public void checkDropdownContent(String buttonId, String expectedAnswerText){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement faqQuestion = wait.until(ExpectedConditions.elementToBeClickable(By.id(buttonId)));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", faqQuestion);

        faqQuestion.click();
        WebElement faqAnswer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(buttonId.replace("heading", "panel"))));

        String actualAnswerText = faqAnswer.getText();

        assertEquals("Ответ на вопрос не совпадает с ожидаемым", expectedAnswerText, actualAnswerText);
    }
}
