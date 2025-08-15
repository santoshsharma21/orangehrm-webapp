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
		actionDriver.click(forgotPassword);
	}
	
	public boolean isLoginFailed() {
		return actionDriver.isDisplayed(loginErrorIndicator);
	}
}