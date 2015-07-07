package com.smartling.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends Page {

	public LoginPage(WebDriver webDriver) {
		super(webDriver);
	}

	@FindBy(how = How.ID, using = "edit-name")
	WebElement loginTextBox;

	@FindBy(how = How.ID, using = "edit-pass")
	WebElement passwordTextBox;

	@FindBy(how = How.ID, using = "edit-submit")
	WebElement submitButton;

	@FindBy(how = How.ID, using = "user_login_form")
	WebElement userLogInForm;

	public boolean getLogInFormDisplayed() {
		return isElementPresent(userLogInForm);
	}

	public AdminPage logInToUserPage(String username, String password) {
		loginTextBox.sendKeys(username);
		passwordTextBox.sendKeys(password);
		submitButton.click();
		return PageFactory.initElements(webDriver, AdminPage.class);
	}

}
