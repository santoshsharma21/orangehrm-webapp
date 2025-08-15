/**
 * 
 */
package com.orangehrm.pages;

import org.openqa.selenium.WebDriver;

import com.orangehrm.actiondriver.ActionDriver;

/**
 * 
 */
public class BasePage {
	
	// webdriver instance
	protected WebDriver driver;
	
	// action driver instance
	protected ActionDriver actionDriver;
	
	// constructor
	public BasePage(WebDriver driver) {
		this.driver = driver;
		actionDriver = new ActionDriver(driver);
	}
}