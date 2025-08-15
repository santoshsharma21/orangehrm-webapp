package com.orangehrm.dataprovider;

import org.testng.annotations.DataProvider;

import com.orangehrm.utils.ExcelUtils;

public class TestDataProvider {
	
	@DataProvider(name = "loginData")
	public Object[][] loginTestData(){
		Object[][] testData = ExcelUtils.getSheetData("loginTestData");
		return testData;
	}
}
