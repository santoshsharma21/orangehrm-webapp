/**
 * 
 */
package com.orangehrm.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * @author Santosh Sharma
 * 
 */
public class RetryAnalyzer implements IRetryAnalyzer {
	
	private int counter = 0;
	private static final int maxRetry = 2;
	
	@Override
	public boolean retry(ITestResult result) {
		if(counter < maxRetry) {
			counter ++;
			return true;
		}
		return false;
	}

}
