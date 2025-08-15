/**
 * 
 */
package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * 
 */
public class HomePage extends BasePage {
	
	// page objects
	private By loginSuccessIndicator = By.xpath("//h6[text()='Dashboard']");
	
	
	// constructor
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
		
	public boolean isLoginSuccessful() {
		return actionDriver.isDisplayed(loginSuccessIndicator);
	}
}
