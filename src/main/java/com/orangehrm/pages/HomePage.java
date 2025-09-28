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
    private By pimLinkTxt = By.xpath("//ul[@class='oxd-main-menu']//li//span[text() = 'PIM']");
		
	// constructor
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public PimPage gotoPimPage() {
		actionDriver.clickUsingJs(pimLinkTxt);
		return new PimPage(driver);
	}	
		
	public boolean isLoginSuccessful() {
		return actionDriver.isDisplayed(loginSuccessIndicator);
	}
}
