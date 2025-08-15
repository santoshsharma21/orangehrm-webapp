package com.orangehrm.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;
import com.orangehrm.dataprovider.TestDataProvider;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utils.DriverManager;
import com.orangehrm.utils.FrameworkLogger;

public class LoginPageTest extends BaseClass {

	// page class instance
	private LoginPage loginPage;
	private HomePage homePage;

	@Test(dataProvider = "loginData", dataProviderClass = TestDataProvider.class)
	public void loginTest(String username, String password, String expectedResult) {
		FrameworkLogger.phase("Test started: " + "loginTest");
		loginPage = new LoginPage(DriverManager.getDriver());
		homePage = loginPage.login(username, password);
		
		FrameworkLogger.info("Test Data => Username: " + username + ", Password: " + password + ", Expected Result: " + expectedResult);
       
		if(expectedResult.equalsIgnoreCase("pass")) {
			Assert.assertTrue(homePage.isLoginSuccessful(), "Login should be successful");
			FrameworkLogger.info("Login successful as expected");
		} else {
			Assert.assertTrue(loginPage.isLoginFailed(), "Login should fail with invalid credentials");
			FrameworkLogger.info("Login failed as expected");
		}
		FrameworkLogger.phase("Test ended: " + "loginTest");
	}
}
