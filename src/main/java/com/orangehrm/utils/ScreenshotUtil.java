/**
 * 
 */
package com.orangehrm.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * 
 */
public class ScreenshotUtil {
	
	/**
	 * captures a screenshot of the current browser window
	 * @param driver WebDriver instance
	 * @param testName name of the test to be used as the screenshot filename
	 * @return Path where the screenshot is saved
	 */
	public static String captureScreenshot(WebDriver driver, String testName) {
		String timeStamp = new SimpleDateFormat("dd.MM.yyyy_hh.mm.ss").format(new Date());
		String fileName = testName + "_" + timeStamp + ".png";
		String folderPath = System.getProperty("user.dir") + "/screenshot/";
		String destPath = folderPath + fileName;
		
		try {
			// create directory if does not exist
			File screenshotDir = new File(folderPath);
			if(!screenshotDir.exists()) {
				screenshotDir.mkdirs();
			}
			// capture screeshot
			TakesScreenshot takesScrShot = (TakesScreenshot) driver;
			File sourceImg = takesScrShot.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(sourceImg, new File(destPath));
			FrameworkLogger.info("Screenshot captured for test: " + testName);
			return destPath;
		} catch(Exception e) {
			FrameworkLogger.fail("Failed to capture screenshot for: " + testName, e);
			return null;
		}
	}

}
