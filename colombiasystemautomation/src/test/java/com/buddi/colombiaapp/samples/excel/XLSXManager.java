package com.buddi.colombia.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLSXManager{
	public String path;
	public static FileInputStream fis;
	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;
	private static Row row;
	private Cell cell;

	public static final String testDataExcelFileName = "testdata.xlsx";	
	private static String fileSeperator = System.getProperty("file.separator");
	private static String testDataExcelFilePath = System.getProperty("user.dir") +fileSeperator+ "testdata";
	public static String testDataExcelFileLocation =  testDataExcelFilePath +fileSeperator+ testDataExcelFileName;


	public XLSXManager(){
		//System.out.println("hello");
	}

	public XLSXManager(String path) throws IOException{
		this.path = path;
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		//name of the sheet
		/*sheet=workbook.getSheet("Alerts");
		System.out.println("Sheet name is:  "+sheet.getSheetName());
		System.out.println(sheet.getLastRowNum());
		System.out.println(sheet.getRow(2).getCell(3));*/
		fis.close();
	}

	public static int getRowCount(String sheetName){
		sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum() + 1;
		//System.out.println("Total rows present in the sheet with name "+sheetName+ " is: "+rowCount);
		return rowCount;
	}

	public static int getColumnCount() {
		row = sheet.getRow(0);
		int columnCount = row.getLastCellNum();
		//System.out.println("Total columns present in the row are: " +columnCount);
		return columnCount;
	}


	public String getData(int sheetnumber, int row, int column){
		sheet = workbook.getSheetAt(sheetnumber);
		String data = sheet.getRow(row).getCell(column).getStringCellValue();
		return data;
	} 

	public static String excelReadTextCell(int rowIndex, int colIndex) {
		String cellValue="";
		row = sheet.getRow(rowIndex);
		if(row.getCell(colIndex) != null){
			if ("NUMERIC".equals(row.getCell(colIndex).getCellTypeEnum().toString())) {
				cellValue = NumberToTextConverter.toText(row.getCell(colIndex).getNumericCellValue());
			} else {
				cellValue = row.getCell(colIndex).getStringCellValue();
			}
		}
		return cellValue;
	}

	public static Object[][] getExcelData(String sheetName) {
		int rows = getRowCount(sheetName); 
		int columns = getColumnCount();
		Object[][] excelData = new Object[rows-1][columns];
		for (int i = 1; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				excelData[i - 1][j] = excelReadTextCell(i, j);
				System.out.print(excelData[i - 1][j]+" ");
			}
			System.out.println("");
		}
		return excelData;
	}

	public static void readData(String sheetName){
		int rowCount = getRowCount(sheetName); 
		int columnCount = getColumnCount();
		Object[][] test = getExcelData(sheetName);
		for(int iRow = 0; iRow < rowCount; iRow++){
			for(int iCol = 0; iCol < columnCount; iCol++){
				System.out.print(test[iRow][iCol]+ " ");
			}
			System.out.println(" ");
		}
	}

	public static void main(String[] args) throws IOException {
		XLSXManager excel = new XLSXManager(testDataExcelFileLocation);
		int rowCount = getRowCount("Alerts");  
		int columnCount = getColumnCount(); 
		//readData("Alerts");
		getExcelData("Alerts");
	}
}