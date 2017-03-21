package com.hlp.test.pageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Sean on 16-Mar-17.
 */
public class SignInPage {


    public void fillInUsername(WebDriver driver, String s) {
        WebElement element =  driver.findElement(By.id("Email"));
        element.clear();
        element.sendKeys(s);
    }

    public void fillInPassword(WebDriver driver, String s) {
        WebElement element = driver.findElement(By.id("Passwd"));
        element.clear();
        element.sendKeys(s);
    }

    public void clickStayLoggedIn(WebDriver driver) {
        WebElement element = driver.findElement(By.id("PersistentCookie"));
        element.click();// UNCHECK Stay signed in
    }

    // ...returning seperate pageobj
    public EmailHomePage clickSignIn(WebDriver driver) {
        WebElement element = driver.findElement(By.id("signIn"));
        element.click();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Inbox")));

        return PageFactory.initElements(driver, EmailHomePage.class);

    }

    public void clickNxt(WebDriver driver) {
        WebElement element = driver.findElement(By.id("next"));
        element.click();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Passwd")));
    }

    public boolean isNextButton(WebDriver driver) {
        return driver.findElements(By.id("next")).size() > 0;
    }

}
