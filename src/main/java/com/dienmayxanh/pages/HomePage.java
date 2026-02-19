package com.dienmayxanh.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object Model for Dien May Xanh Home Page
 */
public class HomePage extends BasePage {

    // Locators for login button - Updated based on actual website structure
    @FindBy(xpath = "//a[contains(@class, 'name-order') and contains(text(), 'Đăng nhập')]")
    private WebElement loginButton;

    @FindBy(css = "a.name-order.active")
    private WebElement loginButtonAlt;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Navigate to Dien May Xanh website
     */
    public void navigateToHomePage() {
        driver.get("https://www.dienmayxanh.com/");
    }

    /**
     * Click on login button to open login modal
     */
    public void clickLoginButton() {
        try {
            clickElement(loginButton);
        } catch (Exception e) {
            try {
                clickElement(loginButtonAlt);
            } catch (Exception ex) {
                System.out.println("Could not find login button, trying alternative methods");
            }
        }
    }
}
