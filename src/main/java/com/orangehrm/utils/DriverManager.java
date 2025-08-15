/**
 * 
 */
package com.orangehrm.utils;



import org.openqa.selenium.WebDriver;

/**
 * 
 */
public class DriverManager {
		
	// threadlocal variable
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	/**
	 * 
	 */
	public static WebDriver getDriver() {
		if(driver.get() == null) {
			System.out.println("webdriver is not initialized");
			throw new IllegalStateException("webdriver is not initialized");
		}
		return driver.get();
	}
	
	/**
	 * 
	 */
	public static void setDriver(WebDriver driverInstance) {
		driver.set(driverInstance);
	}
	
	/**
	 * 
	 */
	public static void unloadDriver() {
		driver.remove();
	}
	
	
	/**
	 * 
	 */
	public static void quitDriver() {
		if(getDriver() != null) {
			try {
				getDriver().quit();
			} catch(Exception e) {
			}
		}
	}
}
