/**
 * 
 */
package com.orangehrm.utils;



import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * 
 */
public class DriverManager {
	
	// logger instance
	private static final Logger log = LoggerManager.getLogger(DriverManager.class);
		
	// threadlocal variable
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	/**
	 * 
	 */
	public static WebDriver getDriver() {
		if(driver.get() == null) {
			log.info("webdriver is not initialized");
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
				log.info("webdriver closed successfully");
			}
		}
	}
}
