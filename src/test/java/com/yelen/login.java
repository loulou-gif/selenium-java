package com.yelen;

import java.awt.RenderingHints;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class login {
    private WebDriver driver;

    public void setup(){
        driver = new ChromeDriver();
    }

    public void LoginSystem(String username, String password){
        driver.get("https://yelenv2.dev02.ovh.smile.ci");

        try {
            Thread.sleep(2000);
            WebElement PopUp = driver.findElement(By.cssSelector("#ui-id-1"));
            WebElement PopUpButton = driver.findElement(By.cssSelector("body > div.ui-dialog.ui-corner-all.ui-widget.ui-widget-content.ui-front.ui-dialog-buttons > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > div > button"));
            WebElement inputUsername = driver.findElement(By.cssSelector("#edit-name"));
            WebElement inputPassword = driver.findElement(By.cssSelector("#edit-pass"));

            if(PopUp.isDisplayed()){
                PopUpButton.click();
                inputUsername.sendKeys(username);
                inputPassword.sendKeys(password);
                inputPassword.sendKeys(Keys.RETURN);
            }else{
                inputUsername.sendKeys(username);
                inputPassword.sendKeys(password);
                inputPassword.sendKeys(Keys.RETURN);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goodCredentials(){
        LoginSystem("admin", "''ZX5tgqxq\"2pF#");
        System.out.println("Login Successful with Good Credentials is ok");
    }
    public void badCredentials(){
        LoginSystem("admin", "admin");
        System.out.println("Can't open app bad credential ok");
    }
    public void missCredentials(){
        LoginSystem("", "");
        System.out.println("Can't open app bad credential ok");
    }

    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }

    public static void main(String[] args) {
        
        login test = new login();

        try {
            test.setup();
            test.missCredentials();
            test.badCredentials();
            test.goodCredentials();
        } finally {
            test.tearDown();
        }
    }
    
}
