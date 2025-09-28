package com.orangehrm.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;
import com.orangehrm.dataprovider.TestDataProvider;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utils.ConfigReader;
import com.orangehrm.utils.DriverManager;
import com.orangehrm.utils.FrameworkLogger;

public class LoginPageTest extends BaseClass {

	// page class instance
	private LoginPage loginPage;
	private HomePage homePage;
	

	@Test(dataProvider = "loginData", dataProviderClass = TestDataProvider.class, groups = {"Positve/Negative Test"})
	public void loginTest(String username, String password, String expectedResult) {
		FrameworkLogger.phase("Test started: loginTest");
		loginPage = new LoginPage(DriverManager.getDriver());
		homePage = loginPage.login(username, password);
		
		String testDataInfo = "Test Data => Username: " + username + ", Password: " + password + ", Expected Result: " + expectedResult;
		FrameworkLogger.info(testDataInfo);
       
		if(expectedResult.equalsIgnoreCase("pass")) {
			Assert.assertTrue(homePage.isLoginSuccessful(), "Login should be successful");
			FrameworkLogger.info("Login successful as expected");
		} else {
			Assert.assertTrue(loginPage.isLoginFailed(), "Login should fail with invalid credentials");
			FrameworkLogger.info("Login failed as expected");
		}
		FrameworkLogger.phase("Test ended: loginTest");
	}
	
	@Test
	public void testLoginWithEmptyUsernameField() {
		FrameworkLogger.phase("Test started: testLoginWithEmptyUsernameField");
		loginPage = new LoginPage(DriverManager.getDriver());
		String password = ConfigReader.getProperty("Password");
		loginPage.login("", password);
		
		String testDataInfo = "Test Data => Username: " + "" + ", Password: " + password;
		FrameworkLogger.info(testDataInfo);
		
		Assert.assertTrue(loginPage.isFieldRequiredMessageDisplayed(), "Login should fail with empty username field");
		FrameworkLogger.info("Login failed as expected");
		
		FrameworkLogger.phase("Test ended: testLoginWithEmptyUsernameField");
	}
	
	@Test
	public void testLoginWithEmptyPasswordField() {
		FrameworkLogger.phase("Test started: testLoginWithEmptyPasswordField");
		loginPage = new LoginPage(DriverManager.getDriver());
		String username = ConfigReader.getProperty("Username");
		loginPage.login(username, "");
		
		String testDataInfo = "Test Data => Username: " + username + ", Password: " + "";
		FrameworkLogger.info(testDataInfo);
		
		Assert.assertTrue(loginPage.isFieldRequiredMessageDisplayed(), "Login should fail with empty password field");
		FrameworkLogger.info("Login failed as expected");
		
		FrameworkLogger.phase("Test ended: testLoginWithEmptyPasswordField");
	}
}
