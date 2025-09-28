/**
 * 
 */
package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangehrm.utils.FrameworkLogger;

/**
 * 
 */
public class PimPage extends BasePage {
	
	// page objects
	private By employeeListMenu = By.xpath("//a[normalize-space()='Employee List']");
	private By addEmployeelinkTxt = By.xpath("//nav[@aria-label='Topbar Menu']//ul//li[normalize-space()='Add Employee']");
	
	// constructor
	public PimPage(WebDriver driver) {
		super(driver);
	}
	
	// action methods
	public AddEmployeePage goToAddEmployeePage(){
		actionDriver.clickUsingJs(addEmployeelinkTxt);
		FrameworkLogger.info("Navigated to 'Add Employee Page'");
		return new AddEmployeePage(driver);
	}
	
	public EmployeeListPage goToEmployeeListPage() {
		actionDriver.click(employeeListMenu);
		FrameworkLogger.info("Navigated to 'Employee List Page'");
		return new EmployeeListPage(driver);
	}
}
