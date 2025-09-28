/**
 * 
 */
package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * 
 */
public class AddEmployeePage extends BasePage {
	
	// page objects
	private By firstNameTxtBox = By.name("firstName");   
	private By midleNameTxtBox = By.name("middleName");
	private By lastNameTxtBox = By.name("lastName");
	private By saveBtn = By.xpath("//button[normalize-space()='Save']");
	private By adminMenu = By.xpath("//span[normalize-space()='Admin']");
	private By addEmpSuccessMsg = By.xpath("//p[@class='oxd-text oxd-text--p oxd-text--toast-message oxd-toast-content-text']");
	
	//constructor
	public AddEmployeePage(WebDriver driver) {
		super(driver);
	}
	
	
	// action methods
	public void enterFirstName(String firstName) {
		actionDriver.enterText(firstNameTxtBox, firstName);
	}
	
	public void enterMiddleName(String middleName) {
		actionDriver.enterText(midleNameTxtBox, middleName);
	}
	
	public void enterLastName(String lastName) {
		actionDriver.enterText(lastNameTxtBox, lastName);
	}
	
	public void clickSaveButton() {
		actionDriver.clickUsingJs(saveBtn);
	}
	
	public void goToAdminPage() {
		actionDriver.clickUsingJs(adminMenu);
	}
	
	public boolean isEmployeeCreatedSuccessfully(String expectedMessage) {
		String actualSuccessMsg = actionDriver.getText(addEmpSuccessMsg);
		return actualSuccessMsg.contains(expectedMessage);
	}
}
