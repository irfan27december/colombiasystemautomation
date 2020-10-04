package com.buddi.colombia.utilities;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelSheet {
	public static XSSFWorkbook workBook = null;
	public static XSSFSheet workSheet = null;

	//Method excel sheet name
	public static XSSFSheet DataSheet(String filePath, String sheetName){
		//Import excel sheet
		File file = new File(filePath);
		try {
			// Load the file
			FileInputStream fis = new FileInputStream(file);
			// Load the workbook
			workBook = new XSSFWorkbook(fis);
			// Load the sheet in which data is stored
			workSheet = workBook.getSheet(sheetName);         
		} catch (Exception e) {         
			e.printStackTrace();
		}
		return workSheet;      
	}       

}
