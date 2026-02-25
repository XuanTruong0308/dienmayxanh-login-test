package com.practicetestautomation.pages;

import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private final String URL = "https://practicetestautomation.com/practice-test-login/";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(URL);
    }
}