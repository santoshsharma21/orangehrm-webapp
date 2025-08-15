/**
 * 
 */
package com.orangehrm.base;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.orangehrm.actiondriver.ActionDriver;
import com.orangehrm.utils.ActionDriverManager;
import com.orangehrm.utils.ConfigReader;
import com.orangehrm.utils.DriverManager;
import com.orangehrm.utils.LoggerManager;
import com.orangehrm.utils.WebDriverFactory;

/**
 * 
 */
public class BaseClass {
	
	// Logger object
	private static final Logger log = LoggerManager.getLogger(BaseClass.class);

	/**
	 * Sets WebDriver, ActionDriver for current thread,
	 * Also configure the browser.
	 * 
	 * @param browser the name of the browser.
	 */
	@BeforeMethod
	@Parameters("browser")
	public void setupBrowser(String browser) {
		
		log.info("Setting up WebDriver for: " + getClass().getSimpleName());
		
		// Intialize and set webdriver using WebDriverFactory and DriverManager.
		DriverManager.setDriver(WebDriverFactory.createWebDriver(browser));
		log.info("Sets WebDriver for current thread: " + Thread.currentThread().getId());
		
		// Intialize and set action driver using AtionDriver and ActionDriverManager.
		ActionDriverManager.setActionDriver(new ActionDriver(DriverManager.getDriver()));
		log.info("Sets ActionDriver for current thread - " + Thread.currentThread().getId());
		
		// browser configuration
		configureBrowser();
		staticWait(Integer.parseInt(ConfigReader.getProperty("static.wait")));
	}

	/**
	 * Configures the browser.
	 */
	public void configureBrowser() {
		// get driver
		WebDriver driver = DriverManager.getDriver();

		// maximize the window
		driver.manage().window().maximize();

		// launch url
		try {
			driver.get(ConfigReader.getProperty("base.url"));
			log.info("Url launched successfully.");
		} catch (Exception e) {
			log.error("failed to launch url - " + e);
		}
	}

	/**
	 * Quits the browser and unloads the driver instance. Also unloads the
	 * ActionDriver instance.
	 */
	@AfterMethod
	public void tearDown() {
		DriverManager.quitDriver();
		log.info("WebDriver session closed successfully.");
		DriverManager.unloadDriver();
		ActionDriverManager.unloadActionDriver();
	}

	/**
	 * Introduces static wait for the given time.
	 * 
	 * @param seconds Time to wait in seconds.
	 */
	public void staticWait(int seconds) {
		LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(seconds));
	}
}
