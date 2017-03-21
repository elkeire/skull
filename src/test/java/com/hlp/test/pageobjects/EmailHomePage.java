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

	public void clickComposeButton(WebDriver driver) {
		WebElement composeButtom = driver.findElement(By.cssSelector("div[role='button'][gh='cm']"));
        composeButtom.click();
		
	}

	public void fillInRecipient(WebDriver driver, String recipient) {
		WebElement toTextBox = driver.findElement(By.cssSelector("textArea[name='to']"));
        toTextBox.clear();
        toTextBox.sendKeys("iamanothergod@gmail.com");
		
	}

	public void fillInSubject(WebDriver driver, String subject) {
		WebElement subjectTextBox = driver.findElement(By.name("subjectbox"));
        subjectTextBox.clear();
        subjectTextBox.sendKeys(subject);
		
	}

	public void fillInEmailBody(WebDriver driver, String body) {
		WebElement emailBodyTextArea = driver.findElement(By.cssSelector("div[aria-label='Message Body']"));
        emailBodyTextArea.clear();
        emailBodyTextArea.sendKeys(body);
		
	}

	public void clickSendEmail(WebDriver driver) {
		WebElement sendButton = driver.findElement(By.cssSelector("div[aria-label*=\"Send\"]"));
        sendButton.click();
		
	}

	public void clickEmailLink(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Inbox (")));
        WebElement inboxlinkage = driver.findElement(By.partialLinkText("Inbox ("));
        inboxlinkage.click();
	}

	public EmailViewPage clickNewEmail(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='xS'][role='link']")));
        WebElement newEmail = driver.findElement(By.cssSelector("div[class='xS'][role='link']"));
        newEmail.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2[class='hP']")));
        return PageFactory.initElements(driver, EmailViewPage.class);
		
	}
}
