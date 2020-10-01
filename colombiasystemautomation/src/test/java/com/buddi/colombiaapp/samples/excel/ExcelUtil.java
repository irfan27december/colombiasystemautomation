package com.buddi.colombiaapp.samples.excel;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Platform;

import com.buddi.colombiaapp.tests.AppiumBaseTest;

//import static com.buddi.colombiaapp.tests.AppiumBaseTest.testDataExcelFileName;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Excel utility for reading data from excel file
 * 
 */
public class ExcelUtil {
	public static final String testDataExcelFileName = "testdata.xlsx";	
	private static String fileSeperator = System.getProperty("file.separator");
	private static String testDataExcelFilePath = System.getProperty("user.dir") +fileSeperator+ "testdata";
	private static String testDataExcelFileLocation =  testDataExcelFilePath +fileSeperator+ testDataExcelFileName;


	//Main Directory of the project
	public static final String currentDir = System.getProperty("user.dir");

	//Location of Test data excel file
	//public static String testDataExcelPath = null;

	//Excel WorkBook
	private static XSSFWorkbook excelWBook;

	//Excel Sheet
	private static XSSFSheet excelWSheet;

	//Excel cell
	private static XSSFCell cell;

	//Excel row
	private static XSSFRow row;

	//Row Number
	public static int rowNumber;

	//Column Number
	public static int columnNumber;

	//Setter and Getters of row and columns
	public static void setRowNumber(int pRowNumber) {
		rowNumber = pRowNumber;
	}

	public static int getRowNumber() {
		return rowNumber;
	}

	public static void setColumnNumber(int pColumnNumber) {
		columnNumber = pColumnNumber;
	}

	public static int getColumnNumber() {
		return columnNumber;
	}

	// This method has two parameters: "Test data excel file name" and "Excel sheet name"
	// It creates FileInputStream and set excel file and excel sheet to excelWBook and excelWSheet variables.
	public static void setExcelFileSheet(String sheetName) throws Exception {
		//MAC or Windows Selection for excel path
		if (Platform.getCurrent().toString().equalsIgnoreCase("MAC")) {
			testDataExcelFilePath = currentDir + "//testdata//";
		} else if (Platform.getCurrent().toString().contains("WIN")) {
			testDataExcelFilePath = currentDir + "\\testdata\\";
		}
		try {
			// Open the Excel file
			FileInputStream excelFile = new FileInputStream(testDataExcelFilePath + testDataExcelFileName);
			excelWBook = new XSSFWorkbook(excelFile);
			excelWSheet = excelWBook.getSheet(sheetName);
			System.out.println("Sheet name  "+excelWSheet.getSheetName());
		} catch (Exception e) {
			try {
				throw (e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	//This method reads the test data from the Excel cell.
	//We are passing row number and column number as parameters.
	public static String getCellData(int rowNum, int colNum) throws Exception {
		try {
			cell = excelWSheet.getRow(rowNum).getCell(colNum);
			DataFormatter formatter = new DataFormatter();
			String cellData = formatter.formatCellValue(cell);
			return cellData;
		} catch (Exception e) {
			throw (e);
		}
	}

	//This method takes row number as a parameter and returns the data of given row number.
	public static XSSFRow getRowData(int rowNum) throws Exception {
		try {
			row = excelWSheet.getRow(rowNum);
			System.out.println("Row number is: "+rowNum+"  "+row);
			return row;
		} catch (Exception e) {
			throw (e);
		}
	}

	//This method gets excel file, row and column number and set a value to the that cell.
	public static void setCellData(String value, int rowNum, int colNum) throws Exception {
		try {
			row = excelWSheet.getRow(rowNum);
			cell = row.getCell(colNum);
			if (cell == null) {
				cell = row.createCell(colNum);
				cell.setCellValue(value);
			} else {
				cell.setCellValue(value);
			}
			// Constant variables Test Data path and Test Data file name
			FileOutputStream fileOut = new FileOutputStream(testDataExcelFilePath + testDataExcelFileName);
			excelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			try {
				throw (e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public static void main(String args[]) throws Exception{
		System.out.println("Test data file path is "+ testDataExcelFilePath);
		System.out.println("Test data file name is "+ testDataExcelFileName);
		setExcelFileSheet("Alerts");
		setExcelFileSheet("Visits");
		getRowData(0);
	}
}