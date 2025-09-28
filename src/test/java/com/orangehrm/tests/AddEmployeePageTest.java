/**
 * 
 */
package com.orangehrm.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;
import com.orangehrm.enums.RandomDataConstants;
import com.orangehrm.pages.AddEmployeePage;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.pages.PimPage;
import com.orangehrm.utils.ConfigReader;
import com.orangehrm.utils.DriverManager;
import com.orangehrm.utils.FakerUtils;
import com.orangehrm.utils.FrameworkLogger;

/**
 * 
 */
public class AddEmployeePageTest extends BaseClass {
	
	// page class instance
	private LoginPage loginPage;
	private HomePage homePage;
	private PimPage pimPage;
	private AddEmployeePage addEmployeePage;
	
	@Test()
	public void testAddEmployee(){
		FrameworkLogger.phase("testAddEmployee");
		loginPage = new LoginPage(DriverManager.getDriver());
		homePage = loginPage.login(ConfigReader.getProperty("Username"), ConfigReader.getProperty("Password"));
		pimPage = homePage.gotoPimPage();
		addEmployeePage = pimPage.goToAddEmployeePage();
		addEmployeePage.enterFirstName(FakerUtils.getRandomData(RandomDataConstants.FIRSTNAME));
		addEmployeePage.enterMiddleName(FakerUtils.getRandomData(RandomDataConstants.MIDDLENAME));
		addEmployeePage.enterLastName(FakerUtils.getRandomData(RandomDataConstants.LASTNAME));
		addEmployeePage.clickSaveButton();
		boolean flag = addEmployeePage.isEmployeeCreatedSuccessfully("Successfully");
		Assert.assertTrue(flag);
	}
}
