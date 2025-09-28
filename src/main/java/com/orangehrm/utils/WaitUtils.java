/**
 * 
 */
package com.orangehrm.utils;

import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 
 */
public class WaitUtils {
	
	// Webdriverwait instance
	private WebDriverWait wait;
	
	// Logger instance
	private static final Logger log = LoggerManager.getLogger(WaitUtils.class);
	
	// constructor
	public WaitUtils(WebDriver driver) {
		int waitTime = Integer.parseInt(ConfigReader.getProperty("explicit.wait"));
		wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
	}
	
	/**
	 * Waits until the given locator is clickable on the page. 
	 * 
	 * @param locator The locator of the target element to wait for.
	 * @return The clickable WebElement after wait.
	 */
	public WebElement waitForElementToBeClickable(By locator) {
		try {
			WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(locator));
			log.info("Element is clickable: " + locator);
			return ele;
		} catch(Exception e) {
			log.error("Timeout: Element not clickable within wait time: " + locator,e);
			throw new RuntimeException("Element not clickable within wait time: " + locator, e);
		}
	}
	
	/**
	 * Waits until the given WebElement is visible on the page. 
	 * 
	 * @param web-element to wait for
	 * @return visible element after wait
	 */
	public WebElement waitForElementToBeVisible(By locator) {
		try {
			WebElement ele =  wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			log.info("Element is visible: " + locator);
			return ele;
		} catch (Exception e) {
			log.error("Timeout: Element not visible within wait time: " + locator, e);
			throw new RuntimeException("Element not visible within wait time: " + locator, e);
		}
	}
}
