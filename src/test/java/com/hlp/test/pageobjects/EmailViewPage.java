package com.hlp.test.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EmailViewPage {

	public String getEmailSubjectText(WebDriver driver) {
		
		WebElement subjectArea = driver.findElement(By.cssSelector("h2[class='hP']"));
		String subjectText = subjectArea.getText();
		
		return subjectText;
	}

	public String getEmailBodyText(WebDriver driver) {
		WebElement bodyArea = driver.findElement(By.cssSelector("div[class='nH aHU'] div[dir='ltr']"));
		String bodyText = bodyArea.getText();
		return bodyText;
	}

}
