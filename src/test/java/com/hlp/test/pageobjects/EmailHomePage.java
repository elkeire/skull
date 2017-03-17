package com.hlp.test.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Sean on 17-Mar-17.
 */
public class EmailHomePage {


    public SignInPage signOut(WebDriver driver) {
        WebElement profileButtonLinkage = driver.findElement(By.cssSelector("span[class='gb_9a gbii']"));
        profileButtonLinkage.click();
        WebElement signOutButton = driver.findElement(By.id("gb_71"));
        signOutButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("next")));

        return PageFactory.initElements(driver, SignInPage.class);
    }

    public boolean isInboxExist(WebDriver driver) {
        return driver.findElements(By.partialLinkText("Inbox")).size() > 0;
    }
}
