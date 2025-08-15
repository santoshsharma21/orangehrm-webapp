package com.orangehrm.utils;

import java.io.FileInputStream;

import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	// class instance
	private static Workbook workbook;
	private static Sheet sheet;
	
	// logger instance
	private final static Logger log = LoggerManager.getLogger(ExcelUtils.class);
	
	
	/**
	 * Reads the specified Excel file and return sheet data in object[][] array.
	 * 
	 * @param filePath the full path to the Excel file.
	 * @param sheetName the name of the sheet to read.
	 * @return returns 2-dimensional object array contains sheet data.
	 * @throws RunTimeExcption if unable to read data.
	 */
	public static Object[][] getSheetData(String sheetName){
		try {
			loadExcelFile(sheetName);
			int nrows = sheet.getPhysicalNumberOfRows();
			int ncols = sheet.getRow(0).getPhysicalNumberOfCells();
			
			Object[][] data = new Object [nrows - 1][ncols];
			
			for(int i = 1; i < nrows; i++) {
				Row row = sheet.getRow(i);
				for(int j = 0; j < ncols; j++) {
					Cell cell = row.getCell(j);
					data[i - 1][j] = getCellData(cell);
				}
			}
			return data;
		} catch(Exception e) {
			log.error("Failed to read data from sheet: " + e.getMessage());
			throw new RuntimeException("Failed to read data from given sheet: " + e.getMessage());
		}
	}
	
	/**
	 * Loads the Excel file and selects specified sheet.
	 * @param filePath the full path to the Excel file.
	 * @param sheetName the name of the sheet to read.
	 * @throws RunTimeException if Failed to load excel file.
	 */
	private static void loadExcelFile(String sheetName) {
		
		String filePath = System.getProperty("user.dir") + ConfigReader.getProperty("excel.test.data");
		
		try(FileInputStream fis = new FileInputStream(filePath)) {
			if(filePath.endsWith(".xlsx")) {
				workbook = new XSSFWorkbook(fis);
			} else if(filePath.endsWith(".xls")) {
				workbook = new HSSFWorkbook(fis);
			} else {
				throw new IllegalArgumentException("Invalid Excel file format: " + filePath);
			}
			sheet = workbook.getSheet(sheetName);
			if(sheet == null) {
				throw new RuntimeException("Sheet not found: " + sheetName);
			}
			log.info("Excel File Loaded successfully");
		} catch(Exception e) {
			log.error("Error loading Excel file: " + e.getMessage());
			throw new RuntimeException("Failed to load Excel file: " + e);
		}
	}
	
	/**
	 * Returns the value of given cell as an object.
	 * Supports String,Numeric and boolean type.
	 * @param cellnum The excel cell to extract data from.
	 * @return Object The value of the cell, or empty string.
	 */
	private static Object getCellData(Cell cell) {
		if(cell == null) {
			return "";
		}
		
		switch(cell.getCellType()) {
			
		case STRING:
			return cell.getStringCellValue();
		
		case NUMERIC:
			return cell.getNumericCellValue();
			
		case BOOLEAN:
			return cell.getBooleanCellValue();
		
		default:
			return "";
		} 
	}
}
