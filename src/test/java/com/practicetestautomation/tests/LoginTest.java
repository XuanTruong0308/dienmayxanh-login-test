package com.practicetestautomation.tests;

import com.practicetestautomation.pages.HomePage;
import com.practicetestautomation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private HomePage homePage;
    private LoginPage loginPage;

    @BeforeMethod
    public void initPages() {
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
}