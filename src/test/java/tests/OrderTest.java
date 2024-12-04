package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject.MainPage;
import pageobject.OrderPage;
import pageobject.RentPage;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;

    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String rentalPeriod;
    private final String color;
    private final String comment;

    public OrderTest(String firstName, String lastName, String address, String metro, String phone,
                     String date, String rentalPeriod, String color, String comment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][]{
                {"Андрей", "Кочетков", "ул. Веселая, д. 1", "Китай-город", "89998888688",
                        "04.11.2024", "сутки", "чёрный жемчуг", "Привезите его в сохранности"},
                {"Ирина", "Иванцова", "ул. Садовая, д. 11", "Красносельская", "87771119900",
                        "12.11.2024", "трое суток", "серая безысходность", "Тест работает!"}
        };
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://qa-scooter.praktikum-services.ru/");

    }

    @Test
    public void checkTopOrderFlow() {
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);
        RentPage rentPage = new RentPage(driver);

        mainPage.clickTopOrderButton();
        orderPage.fillOrderForm(firstName, lastName, address, metro, phone);
        rentPage.fillRentForm(date, rentalPeriod, color, comment);
        rentPage.orderModalDisplayed();
        rentPage.orderSuccessfullyPlaced();
    }

    @Test
    public void checkBottomOrderFlow() {
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);
        RentPage rentPage = new RentPage(driver);

        mainPage.clickBottomOrderButton();
        orderPage.fillOrderForm(firstName, lastName, address, metro, phone);
        rentPage.fillRentForm(date, rentalPeriod, color, comment);
        rentPage.orderModalDisplayed();
        rentPage.orderSuccessfullyPlaced();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}