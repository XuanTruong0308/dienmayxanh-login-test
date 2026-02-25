package com.practicetestautomation.tests;

import com.practicetestautomation.pages.HomePage;
import com.practicetestautomation.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {

    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);

        homePage = new HomePage(driver);
        homePage.open();

        loginPage = new LoginPage(driver);
    }

    @Test(priority = 1)
    public void testLoginSuccess() {
        loginPage.login("student", "Password123");

        Assert.assertTrue(loginPage.isLoginSuccessful());
        Assert.assertEquals(loginPage.getSuccessMessage(), "Logged In Successfully");
    }

    @Test(priority = 2)
    public void testWrongPassword() {
        loginPage.login("student", "wrongpass");

        Assert.assertTrue(loginPage.getErrorMessage().contains("Your password is invalid!"));
    }

    @Test(priority = 3)
    public void testWrongUsername() {
        loginPage.login("wronguser", "Password123");

        Assert.assertTrue(loginPage.getErrorMessage().contains("Your username is invalid!"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}