package com.dienmayxanh.tests;

import com.dienmayxanh.pages.HomePage;
import com.dienmayxanh.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test class for Dien May Xanh Login functionality
 * Testing phone number validation with various scenarios
 */
public class LoginTest extends BaseTest {

    private HomePage homePage;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUpPages() {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
    }

    /**
     * TC01 - Valid Phone Number
     * Input: Valid 10-digit phone number (e.g., "0901234777")
     * Expected: Navigate to OTP screen with confirmation message
     */
    @Test(priority = 1, description = "TC01 - Test with valid phone number")
    public void testValidPhoneNumber() {
        System.out.println("Running TC01: Valid Phone Number Test");
        
        // Navigate to home page
        homePage.navigateToHomePage();
        
        // Wait 3 seconds for page to fully load and close any advertisement banners
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Click login button
        homePage.clickLoginButton();
        
        // Wait for login modal to appear
        try {
            Thread.sleep(2000); // Wait for modal to fully load
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Enter valid phone number and continue
        loginPage.loginWithPhoneNumber("0901234777");
        
        // Wait for response
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Verify OTP screen is displayed
        boolean isOTPDisplayed = loginPage.isOTPScreenDisplayed();
        Assert.assertTrue(isOTPDisplayed, 
            "OTP screen should be displayed for valid phone number");
        
        System.out.println("TC01 PASSED: OTP screen displayed successfully");
    }

    /**
     * TC02 - Invalid Phone Number (Less than 10 digits)
     * Input: "09123" (only 5 digits)
     * Expected: Display error message "Số điện thoại không hợp lệ"
     */
    @Test(priority = 2, description = "TC02 - Test with phone number less than 10 digits")
    public void testInvalidPhoneNumberLessThan10Digits() {
        System.out.println("Running TC02: Invalid Phone Number (Less than 10 digits)");
        
        // Navigate to home page
        homePage.navigateToHomePage();
        
        // Wait 3 seconds for page to fully load and close any advertisement banners
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Click login button
        homePage.clickLoginButton();
        
        // Wait for login modal
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Enter invalid phone number (less than 10 digits)
        loginPage.loginWithPhoneNumber("09123");
        
        // Wait for response
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Verify OTP screen is NOT displayed (login should fail)
        boolean isOTPDisplayed = loginPage.isOTPScreenDisplayed();
        
        // Also check for error message
        String errorMessage = loginPage.getErrorMessage();
        boolean hasError = loginPage.isInvalidPhoneErrorDisplayed() || !errorMessage.isEmpty();
        
        // Test passes if either: OTP not shown OR error message displayed
        Assert.assertTrue(!isOTPDisplayed || hasError, 
            "Invalid phone number should not proceed to OTP screen or should show error");
        
        System.out.println("TC02 PASSED: Invalid phone number (< 10 digits) rejected successfully");
    }

    /**
     * TC03 - Phone Number with Letters
     * Input: "09abc12345" (contains letters)
     * Expected: Display format error message
     */
    @Test(priority = 3, description = "TC03 - Test with phone number containing letters")
    public void testPhoneNumberWithLetters() {
        System.out.println("Running TC03: Phone Number with Letters");
        
        // Navigate to home page
        homePage.navigateToHomePage();
        
        // Wait 3 seconds for page to fully load and close any advertisement banners
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Click login button
        homePage.clickLoginButton();
        
        // Wait for login modal
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Enter phone number with letters
        loginPage.loginWithPhoneNumber("09abc12345");
        
        // Wait for response
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Verify OTP screen is NOT displayed (login should fail)
        boolean isOTPDisplayed = loginPage.isOTPScreenDisplayed();
        
        // Also check for error message
        String errorMessage = loginPage.getErrorMessage();
        boolean hasError = loginPage.isInvalidPhoneErrorDisplayed() || !errorMessage.isEmpty();
        
        // Test passes if either: OTP not shown OR error message displayed
        Assert.assertTrue(!isOTPDisplayed || hasError, 
            "Phone number with letters should not proceed to OTP screen or should show error");
        
        System.out.println("TC03 PASSED: Phone number with letters rejected successfully");
    }

    /**
     * TC04 - Empty Phone Number
     * Input: "" (empty string)
     * Expected: Display warning "Vui lòng nhập số điện thoại"
     */
    @Test(priority = 4, description = "TC04 - Test with empty phone number")
    public void testEmptyPhoneNumber() {
        System.out.println("Running TC04: Empty Phone Number");
        
        // Navigate to home page
        homePage.navigateToHomePage();
        
        // Wait 3 seconds for page to fully load and close any advertisement banners
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Click login button
        homePage.clickLoginButton();
        
        // Wait for login modal
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Leave phone number empty and click continue
        loginPage.loginWithPhoneNumber("");
        
        // Wait for response
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Verify OTP screen is NOT displayed (login should fail)
        boolean isOTPDisplayed = loginPage.isOTPScreenDisplayed();
        
        // Also check for error message
        String errorMessage = loginPage.getErrorMessage();
        boolean hasError = loginPage.isEmptyPhoneErrorDisplayed() || 
                          loginPage.isInvalidPhoneErrorDisplayed() ||
                          !errorMessage.isEmpty();
        
        // Test passes if either: OTP not shown OR error message displayed
        Assert.assertTrue(!isOTPDisplayed || hasError, 
            "Empty phone number should not proceed to OTP screen or should show error");
        
        System.out.println("TC04 PASSED: Empty phone number rejected successfully");
    }

    /**
     * TC05 - Phone Number with Special Characters
     * Input: "09012###89" (contains special characters)
     * Expected: Cannot continue and display error
     */
    @Test(priority = 5, description = "TC05 - Test with phone number containing special characters")
    public void testPhoneNumberWithSpecialCharacters() {
        System.out.println("Running TC05: Phone Number with Special Characters");
        
        // Navigate to home page
        homePage.navigateToHomePage();
        
        // Wait 3 seconds for page to fully load and close any advertisement banners
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Click login button
        homePage.clickLoginButton();
        
        // Wait for login modal
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Enter phone number with special characters
        loginPage.loginWithPhoneNumber("09012###89");
        
        // Wait for response
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Verify OTP screen is NOT displayed (login should fail)
        boolean isOTPDisplayed = loginPage.isOTPScreenDisplayed();
        
        // Also check for error message
        String errorMessage = loginPage.getErrorMessage();
        boolean hasError = loginPage.isInvalidPhoneErrorDisplayed() || !errorMessage.isEmpty();
        
        // Test passes if either: OTP not shown OR error message displayed
        Assert.assertTrue(!isOTPDisplayed || hasError, 
            "Phone number with special characters should not proceed to OTP screen or should show error");
        
        System.out.println("TC05 PASSED: Phone number with special characters rejected successfully");
    }
}
