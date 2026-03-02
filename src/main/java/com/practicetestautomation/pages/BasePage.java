package com.practicetestautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    protected void waitForElementVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    protected void clickElement(WebElement element) {
        waitForElementClickable(element);
        element.click();
        delay(3000);  // Delay 3 giây sau khi click
    }

    protected void enterText(WebElement element, String text) {
        waitForElementVisible(element);
        element.clear();
        delay(1000);  // Delay 1 giây sau khi clear
        element.sendKeys(text);
        delay(2000);  // Delay 2 giây sau khi nhập text
    }

    protected String getElementText(WebElement element) {
        waitForElementVisible(element);
        return element.getText();
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
