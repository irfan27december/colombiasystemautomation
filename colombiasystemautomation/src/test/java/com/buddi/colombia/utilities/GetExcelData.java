package com.buddi.colombia.utilities;

import java.math.BigDecimal;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class GetExcelData extends ReadExcelSheet {
	public static XSSFRow Row;
	public static XSSFCell cell;
	public static XSSFSheet sheet;
	public static final String testDataExcelFileName = "testdata.xlsx";	
	private static String fileSeperator = System.getProperty("file.separator");
	private static String testDataExcelFilePath = System.getProperty("user.dir") +fileSeperator+ "testdata";
	public static String testDataExcelFileLocation =  testDataExcelFilePath +fileSeperator+ testDataExcelFileName;

	//Method to get excel cell data
	public static String getCellData(String sheet, int row, int col){
		try {
			int index = workBook.getSheetIndex(sheet);
			workSheet = workBook.getSheetAt(index);
			Row = workSheet.getRow(row);
			if (Row == null)
				return "";
			cell = Row.getCell(col);
			if (cell == null)
				return "";
			switch (cell.getCellType()){
			case  STRING: 
				return cell.getStringCellValue();               
			case  BOOLEAN:
				return String.valueOf(cell.getBooleanCellValue());        
			case  BLANK:
				return "";     
			case  ERROR:
				return cell.getStringCellValue();           
			case NUMERIC: 
				return new BigDecimal(cell.getNumericCellValue()).toPlainString();
				//return String.valueOf(cell.getNumericCellValue());          
			default:
				return "Cell not found";        
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return "row " + row + " or column " + col+ " does not exist in xls...";
		}
	}
}
