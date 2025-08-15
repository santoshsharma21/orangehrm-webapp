package com.orangehrm.utils;

import org.apache.logging.log4j.Logger;

import com.orangehrm.reports.ExtentLogger;

public class FrameworkLogger {
	
	private static final Logger log = LoggerManager.getLogger(FrameworkLogger.class);
	
	/**
	 * Logs informational messages in both the log file and ExtentReport.
	 * 
	 * @param actionDescription String that describe step to perform.
	 */
	public static void info(String actionDescription) {
		log.info(actionDescription);
		ExtentLogger.info(actionDescription);
	}
	
	/**
	 * Logs failure message to both files
	 * 
	 * @param failDescription A message describe failure.
	 * @param e Throwable message.
	 */
	public static void fail(String failDescription, Throwable e) {
		log.error(failDescription, e);
		ExtentLogger.fail(failDescription + " | " + e.getMessage());
	}
	
	/**
	 * Logs a phase message to both log file and Extent Report.
	 * @param message The test name, description of current test phase.
	 */
	public static void phase(String message) {
		String formatedMsg = " ******* " + message.toUpperCase() + " ******* ";
		log.info(formatedMsg);
		ExtentLogger.info(formatedMsg);
	}
}
