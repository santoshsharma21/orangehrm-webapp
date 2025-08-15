/**
 * 
 */
package com.orangehrm.actiondriver;

import org.openqa.selenium.By;

/**
 * 
 */
public interface IActionDriver {
	
	// click method
	void click(By locator);
	void clickUsingJs(By locator);
	
	// enter text
	void enterText(By locator, String text);
	
	// get text
	String getText(By locator);
	
	// isDisplay
	boolean isDisplayed(By locator);
	
	//
}
