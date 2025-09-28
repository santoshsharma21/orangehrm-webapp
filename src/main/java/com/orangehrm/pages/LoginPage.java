/**
 * 
 */
package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * 
 */
public class LoginPage extends BasePage {
	
	// page objects
	private By userNameTextBox = By.name("username");
	private By passwordTextBox = By.name("password");
	private By loginButton = By.xpath("//button[normalize-space()='Login']");
	private By forgotPassword = By.xpath("//p[@class='oxd-text oxd-text--p orangehrm-login-forgot-header']");
	private By loginErrorIndicator = By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']");
	private By emptyfieldIndicator = By.xpath("//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']");
	
	// constructor
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	// action methods
	// perform click
	public HomePage login(String username, String password) {
		actionDriver.enterText(userNameTextBox, username);
		actionDriver.enterText(passwordTextBox, password);
		actionDriver.clickUsingJs(loginButton);
		return new HomePage(driver);
	}
	
	public void clickOnForgotPass() {
		actionDriver.clickUsingJs(forgotPassword);
	}
	
	public boolean isLoginFailed() {
		return actionDriver.isDisplayed(loginErrorIndicator);
	}
	
	public boolean isFieldRequiredMessageDisplayed() {
		return actionDriver.isDisplayed(emptyfieldIndicator);
	}
}