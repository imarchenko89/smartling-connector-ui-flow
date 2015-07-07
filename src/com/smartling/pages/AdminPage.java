package com.smartling.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AdminPage extends Page {

	public AdminPage(WebDriver webDriver) {
		super(webDriver);
	}

	@FindBy(how = How.XPATH, using = "//h1[contains(text(), 'Hello, admin')]")
	WebElement helloAdminMessage;

	public SmartlingSettings goToSmartlingSettings(String settingsUrl) {
		webDriver.get(settingsUrl);
		return PageFactory.initElements(webDriver, SmartlingSettings.class);	
	}
	
	public boolean isLoggedOK() {
		return isElementPresent(helloAdminMessage);	
	}
}
