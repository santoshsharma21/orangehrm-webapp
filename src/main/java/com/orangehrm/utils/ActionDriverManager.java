/**
 * 
 */
package com.orangehrm.utils;

import org.apache.logging.log4j.Logger;

import com.orangehrm.actiondriver.ActionDriver;

/**
 * 
 */
public class ActionDriverManager {
	
	private static ThreadLocal<ActionDriver> actionDriver = new ThreadLocal<>();
	
	// Logger instance
	private static final Logger log = LoggerManager.getLogger(ActionDriverManager.class);
	
	/**
	 * 
	 * @return thread safe ActionDriver instance
	 */
	public static ActionDriver getActionDriver() {
		if(actionDriver.get() == null) {
			log.info("ActionDriver is not initialized");
			throw new IllegalStateException("ActionDriver is not initialized");
		} 
		return actionDriver.get();
	}
	
	/**
	 * 
	 */
	public static void setActionDriver(ActionDriver actionDriverInstance) {
		actionDriver.set(actionDriverInstance);
	}
	
	/**
	 * 
	 */
	public static void unloadActionDriver() {
		actionDriver.remove();
	}
}
