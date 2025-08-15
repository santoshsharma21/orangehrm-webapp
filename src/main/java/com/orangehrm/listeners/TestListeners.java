package com.orangehrm.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.apache.logging.log4j.Logger;
import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import com.orangehrm.reports.ExtentLogger;
import com.orangehrm.reports.ExtentReportManager;
import com.orangehrm.utils.LoggerManager;

public class TestListeners implements ITestListener, IAnnotationTransformer {
	
	// Logger instance
	private static final Logger log = LoggerManager.getLogger(TestListeners.class);


	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(RetryAnalyzer.class);
	}

	@Override
	public void onTestStart(ITestResult result) {
		String[] groups = result.getMethod().getGroups();
		String testName = result.getName();
		ExtentReportManager.createTest(testName, groups);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentLogger.pass(result);
		ExtentReportManager.unloadExtentTest();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ExtentLogger.fail(result);
		ExtentReportManager.unloadExtentTest();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentLogger.skip(result);
		ExtentReportManager.unloadExtentTest();
	}

	@Override
	public void onFinish(ITestContext context) {
		ExtentReportManager.flushReport();
		File reportPath = new File(ExtentReportManager.reportPath);
		
		if(reportPath.exists()) {
			log.info("Extent Report generated: " + reportPath.getAbsolutePath());
		} else {
			log.error("Failed to generate Extent Report: " + reportPath.getAbsolutePath());
		}
		
		try {
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().browse(reportPath.toURI());
			} else {
				log.info("Desktop not supported. Report path: " + reportPath.getAbsolutePath());;
			}
		} catch (IOException e) {
			log.error("Could not open report in browser.", e);
		}
	}
}
