/**
 * 
 */
package com.orangehrm.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 */
public class LoggerManager {
	
	/**
	 * @return Logger Logger instance for the provided class
	 * 
	 */
	public static Logger getLogger(Class<?> clasz) {
		return LogManager.getLogger(clasz);
	}

}
