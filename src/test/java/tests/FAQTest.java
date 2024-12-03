package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertEquals;

public class FAQTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void checkFAQDropdown() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement faqQuestion = wait.until(ExpectedConditions.elementToBeClickable(By.id("accordion__heading-0")));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", faqQuestion);

        faqQuestion.click();
        WebElement faqAnswer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accordion__panel-0")));

        String actualAnswerText = faqAnswer.getText();

        String expectedAnswerText = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";

        assertEquals("Ответ на вопрос не совпадает с ожидаемым", expectedAnswerText, actualAnswerText);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
