package com.dienmayxanh.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.TimeoutException;

/**
 * Page Object Model for Dien May Xanh Login Page
 */
public class LoginPage extends BasePage {

    // Locators for phone number input - Updated based on actual website structure
    @FindBy(id = "txtPhoneNumber")
    private WebElement phoneNumberInput;

    @FindBy(name = "txtPhoneNumber")
    private WebElement phoneNumberInputAlt;

    // Locators for continue button - Updated based on actual website structure
    @FindBy(xpath = "//button[@type='submit' and @class='btn' and contains(text(), 'Tiếp tục')]")
    private WebElement continueButton;

    @FindBy(css = "button.btn[type='submit']")
    private WebElement continueButtonAlt;

    // Locators for error messages - Updated based on actual website structure
    @FindBy(xpath = "//label[contains(text(), 'Số điện thoại trống/không đúng định dạng')]")
    private WebElement invalidPhoneError;

    @FindBy(xpath = "//label[contains(text(), 'trống') or contains(text(), 'không đúng định dạng')]")
    private WebElement emptyPhoneError;

    @FindBy(xpath = "//label[contains(@class, '') and contains(text(), 'điện thoại')]")
    private WebElement errorMessage;
    
    @FindBy(xpath = "//label")
    private WebElement errorMessageAlt;

    // Locator for OTP screen confirmation
    @FindBy(xpath = "//*[contains(text(), 'Mã xác nhận') or contains(text(), 'OTP') or contains(text(), 'xác thực')]")
    private WebElement otpConfirmationText;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Enter phone number into the input field
     */
    public void enterPhoneNumber(String phoneNumber) {
        try {
            enterText(phoneNumberInput, phoneNumber);
        } catch (Exception e) {
            try {
                enterText(phoneNumberInputAlt, phoneNumber);
            } catch (Exception ex) {
                System.out.println("Could not find phone number input field");
            }
        }
    }

    /**
     * Click continue button
     */
    public void clickContinueButton() {
        try {
            clickElement(continueButton);
        } catch (Exception e) {
            try {
                clickElement(continueButtonAlt);
            } catch (Exception ex) {
                System.out.println("Could not find continue button");
            }
        }
    }

    /**
     * Get error message text
     */
    public String getErrorMessage() {
        try {
            if (isElementDisplayed(invalidPhoneError)) {
                return getElementText(invalidPhoneError);
            } else if (isElementDisplayed(emptyPhoneError)) {
                return getElementText(emptyPhoneError);
            } else if (isElementDisplayed(errorMessage)) {
                return getElementText(errorMessage);
            } else if (isElementDisplayed(errorMessageAlt)) {
                return getElementText(errorMessageAlt);
            }
        } catch (Exception e) {
            System.out.println("No error message found");
        }
        return "";
    }

    /**
     * Check if OTP screen is displayed
     */
    public boolean isOTPScreenDisplayed() {
        try {
            waitForElementVisible(otpConfirmationText);
            return isElementDisplayed(otpConfirmationText);
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Check if invalid phone error is displayed
     */
    public boolean isInvalidPhoneErrorDisplayed() {
        try {
            String errorMsg = getErrorMessage();
            return errorMsg.contains("không đúng định dạng") || 
                   errorMsg.contains("không hợp lệ") || 
                   errorMsg.contains("Invalid");
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if empty phone error is displayed
     */
    public boolean isEmptyPhoneErrorDisplayed() {
        try {
            String errorMsg = getErrorMessage();
            return errorMsg.contains("trống") || 
                   errorMsg.contains("Vui lòng nhập") || 
                   errorMsg.contains("nhập số điện thoại");
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Complete login flow with phone number
     */
    public void loginWithPhoneNumber(String phoneNumber) {
        enterPhoneNumber(phoneNumber);
        clickContinueButton();
    }
}
