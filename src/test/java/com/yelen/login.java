package com.yelen;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class login {
    private WebDriver driver;

    public void setup() {
        driver = new ChromeDriver();
    }

    public void loginToSystem(String username, String password) {
        driver.get("https://yelenv2.dev02.ovh.smile.ci");

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            
            // Close popup if it appears
            WebElement popUpButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
                "body > div.ui-dialog.ui-corner-all.ui-widget.ui-widget-content.ui-front.ui-dialog-buttons > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > div > button"
            )));
            popUpButton.click();

            // Input credentials
            WebElement inputUsername = driver.findElement(By.cssSelector("#edit-name"));
            WebElement inputPassword = driver.findElement(By.cssSelector("#edit-pass"));
            inputUsername.sendKeys(username);
            inputPassword.sendKeys(password);
            inputPassword.sendKeys(Keys.RETURN);
        } catch (Exception e) {
            System.err.println("An error occurred during login: " + e.getMessage());
        }
    }

    public void testGoodCredentials() {
        loginToSystem("admin", "''ZX5tgqxq\"2pF#");
        System.out.println("Login successful with good credentials.");
    }

    public void testBadCredentials() {
        loginToSystem("admin", "admin");
        System.out.println("Login failed with bad credentials.");
    }

    public void testMissingCredentials() {
        loginToSystem("", "");
        System.out.println("Login failed with missing credentials.");
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static void main(String[] args) {
        login test = new login();

        try {
            test.setup();
            test.testMissingCredentials();
            test.testBadCredentials();
            test.testGoodCredentials();
        } finally {
            test.tearDown();
        }
    }
}
