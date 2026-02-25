package com.practicetestautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "submit")
    private WebElement submitButton;

    @FindBy(className = "post-title")
    private WebElement successMessage;

    @FindBy(id = "error")
    private WebElement errorMessage;

    public void login(String username, String password) {
        enterText(usernameInput, username);
        enterText(passwordInput, password);
        clickElement(submitButton);
    }

    public String getSuccessMessage() {
        return getElementText(successMessage);
    }

    public String getErrorMessage() {
        return getElementText(errorMessage);
    }

    public boolean isLoginSuccessful() {
        return driver.getCurrentUrl().contains("logged-in-successfully");
    }
}