/**
 * 
 */
package com.orangehrm.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * 
 * 
 * @param 
 * 
 */
public class WebDriverFactory {
	
	/**
	 * Creates and Returns WebDriver instance based on the given browser name 
	 * 
	 * @param browser name of the browser ("chrome, "firefox")
	 * @return WebDriver instance
	 */
	public static WebDriver createWebDriver(String browser) {
		
		WebDriver driver;
		
		switch(browser.toLowerCase()) {
			
		case "chrome":
			driver = new ChromeDriver();
			break;
			
		case "firefox":
			driver = new FirefoxDriver();
			break;
		
		case "edge":
			driver = new EdgeDriver();
			break;
		
		default:
			throw new IllegalArgumentException("Invalid browser name: " + browser);
		}
		
		return driver;
	}

}
