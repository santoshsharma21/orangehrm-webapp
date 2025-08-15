/**
 * 
 */
package com.orangehrm.actiondriver;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.orangehrm.utils.ConfigReader;
import com.orangehrm.utils.FrameworkLogger;
import com.orangehrm.utils.LoggerManager;
import com.orangehrm.utils.WaitUtils;

/**
 * 
 */
public class ActionDriver implements IActionDriver {

	// webdriver instance
	private WebDriver driver;

	// webdriver wait
	private WaitUtils waitUtils;
	
	// logger
	private static final Logger log = LoggerManager.getLogger(ActionDriver.class);
	
	// constructor
	public ActionDriver(WebDriver driver) {
		this.driver = driver;
		waitUtils = new WaitUtils(driver);
	}
	
	
	/**
	 * Performs click action on the web element.
	 * 
	 * @param By locator
	 */
	@Override
	public void click(By locator) {
		try {
			WebElement ele = waitUtils.waitForElementToBeClickable(locator);
			String eleDesc = getElementDescription(ele, locator);
			ele.click();
			highlightElement(locator, "green");
			FrameworkLogger.info("Clicked on: " + eleDesc);
		} catch (Exception e) {
			highlightElement(locator, "red");
			FrameworkLogger.fail("Error clicking on element: " + locator, e);
		} 
	}
	
	/**
	 * Performs click action on the web element.
	 * 
	 * @param By locator
	 */
	@Override
	public void clickUsingJs(By locator) {
		try {
			WebElement ele = waitUtils.waitForElementToBeClickable(locator);
			String eleDesc = getElementDescription(ele, locator);
			highlightElement(locator, "green");
			String jsClickScript = "arguments[0].click();";
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript(jsClickScript, ele);
			FrameworkLogger.info("Clicked on: " + eleDesc);
		} catch(Exception e) {
			highlightElement(locator, "red");
			FrameworkLogger.fail("Error clicking on: " + locator, e);
		}
	}

	
	/**
	 * Method inputs text into input filed
	 * 
	 * @param By locator of input filed
	 * @param text to enter
	 */
	@Override
	public void enterText(By locator, String text) {
		try {
			WebElement ele = waitUtils.waitForElementToBeVisible(locator);
			String eleDesc = getElementDescription(ele, locator);
			ele.clear();
			ele.sendKeys(text);
			highlightElement(locator, "green");
			FrameworkLogger.info("Typed into: " + eleDesc + " | Text: " + text);
		} catch(Exception e) {
			highlightElement(locator, "red");
			FrameworkLogger.fail("Error typing into element: " + locator, e);
		} 
	}
	
	/**
	 * @param locator the By locator of the target web element
	 * @return the inner text of the element  or an empty string
	 */
	@Override
	public String getText(By locator) {
		try {
			WebElement ele = waitUtils.waitForElementToBeVisible(locator);
			highlightElement(locator, "green");
			String eleDesc = getElementDescription(ele, locator);
			String txt = ele.getText();
			FrameworkLogger.info("Extracted Text from: " + eleDesc + " | " +  "Text: " +  txt);
			return txt;
		} catch(Exception e) {
			highlightElement(locator, "red");
			FrameworkLogger.fail("Error getting text from element: " + locator, e);
			return "";
		}
	}
	
	/**
	 * checks wether the web element is displayed on the page.
	 * 
	 * @param locator The locator of the web element.
	 * @return true if the element is displayed, false if not.
	 */
	@Override
	public boolean isDisplayed(By locator) {
		try {
			WebElement ele = waitUtils.waitForElementToBeVisible(locator);
			highlightElement(locator, "green");
			String eleDesc = getElementDescription(ele, locator);
			FrameworkLogger.info("Element is visible on the page: " + eleDesc);
			return ele.isDisplayed();
		} catch(Exception e) {
			highlightElement(locator, "red");
			FrameworkLogger.fail("Element is not visible on the page: " + locator, e);
			return false;
		}
	}
	

	/**
	 * Returns a descriptive name of the given WebElement
	 * This method extract attribute such as 'id', 'name', 'class',
	 * 'classname' etc.
	 * 
	 * @param web-element whose identifier is to be retrieved
	 * @return a string representing the element
	 */
	public String getElementDescription(WebElement element, By locator) {
		
		if(element == null || locator == null) {
			return "element or locator is null";
		}

		try {
			// get element attributes
			String id = element.getDomProperty("id");
			String name = element.getDomProperty("name");
			String text = element.getText();
			if(isEmptyOrNull(text)) {
				text = element.getAttribute("value");
			}
			String className = element.getDomProperty("class");
			String placeHolder = element.getDomProperty("placeholder");

			// return description based on available attributes
			if (!isEmptyOrNull(id)) {
				return "element with id: '" + id + "'";
			} else if(!isEmptyOrNull(name)) {
				return "element with name: '" + name + "'";
			} else if(!isEmptyOrNull(text)) {
				return "element with text: '" + text + "'";
			} else if(!isEmptyOrNull(className)) {
				return "element with class name: '" + className + "'";
			} else if(!isEmptyOrNull(placeHolder)) {
				return "element with placeholder: '" + placeHolder + "'";
			} else {
				return "element located by: " + locator.toString();
			}

		} catch (Exception e) {
			return "unknown element ["+ locator +"]: " + e.getMessage();
		}
	}

	/**
	 * 
	 * @param string to check for empty or null
	 * @return boolean, 'true' if empty or null else 'false'
	 */
	public static boolean isEmptyOrNull(String string) {
		return string == null || string.trim().isEmpty();
	}
	
	/**
	 * Higlights the given element in green or red colour using JavaScript.
	 * 
	 * @param WebElement webelemet to highlight.
	 * @param colour colour to highlight element.
	 */
	public void highlightElement(By locator, String colour) {
		boolean flag = Boolean.parseBoolean(ConfigReader.getProperty("highlight.elements"));
		WebElement ele = driver.findElement(locator);
		if(flag && ele != null) {
			try {
				String jscript = "arguments[0].style.border = '3px solid "+ colour + "'";
				JavascriptExecutor javaExe = (JavascriptExecutor) driver;
				javaExe.executeScript(jscript, ele);
			} catch(Exception e) {
				log.warn("Could not highlight element : " + getElementDescription(ele, locator), e);
			}
		}
	}
}
