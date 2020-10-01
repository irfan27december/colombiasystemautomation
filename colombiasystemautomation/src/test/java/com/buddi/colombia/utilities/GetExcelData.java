package com.buddi.colombia.utilities;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GetExcelData extends ReadExcelSheet {

	/*public static Object[][] LoginData;
	public static Object[][] ShipmentData;*/
	public static XSSFRow Row;
	public static XSSFCell cell;
	/*public static String FilePath = "C:\\Users\\admin\\Desktop\\DataSheet.xls";
	public static String SheetName1 = "Login";
	public static String SheetName2 = "Shipment";*/
	public static XSSFSheet sheet;

	public static final String testDataExcelFileName = "testdata.xlsx";	
	private static String fileSeperator = System.getProperty("file.separator");
	private static String testDataExcelFilePath = System.getProperty("user.dir") +fileSeperator+ "testdata";
	public static String testDataExcelFileLocation =  testDataExcelFilePath +fileSeperator+ testDataExcelFileName;

	/*
	@DataProvider
	public static Object[][] getLoginData() throws Exception{

		sheet = DataSheet(testDataExcelFileLocation, "Login");
		int rowCount = sheet.getLastRowNum();
		//System.out.println("Row  count:"+rowCount);
		int colCount = sheet.getRow(0).getLastCellNum();
		//System.out.println("Column   count"+colCount);

		LoginData = new Object[rowCount][colCount];

		for (int rCnt=1; rCnt<=rowCount;rCnt++){
			for (int cCnt=0; cCnt<colCount;cCnt++){
				LoginData[rCnt-1][cCnt] = getCellData(SheetName1, rCnt, cCnt);
				//System.out.println(LoginData[rCnt-1][cCnt]);
			}
		}

		return LoginData;
	}
	@DataProvider
	public static Object[][] getShipmentData() throws Exception{

		sheet = DataSheet(FilePath, SheetName2);
		int rowCount = sheet.getLastRowNum();
		System.out.println(rowCount);
		int colCount = sheet.getRow(0).getLastCellNum();
		System.out.println(colCount);

		ShipmentData = new Object[rowCount][colCount];

		for (int rCnt=1; rCnt<=rowCount;rCnt++){
			for (int cCnt=0; cCnt<colCount;cCnt++){
				ShipmentData[rCnt-1][cCnt] = getCellData(SheetName2, rCnt, cCnt);
				System.out.println(ShipmentData[rCnt-1][cCnt]);
			}
		}

		return ShipmentData;        
	}
	 */
	
	//Method to get excel cell data
	public static String getCellData(String sheet, int row, int col){
		try {
			int index = WBook.getSheetIndex(sheet);
			WSheet = WBook.getSheetAt(index);
			Row = WSheet.getRow(row);
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
				return String.valueOf(cell.getNumericCellValue());          
			default:
				return "Cell not found";        
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return "row " + row + " or column " + col+ " does not exist in xls";
		}

	}

	/*@Test(dataProvider="getLoginData")
	public void TC01_Verify_Login_Valid_Cred(String User, String Pass){
		System.out.println(User + Pass);    
	}*/

	/*@Test(dataProvider="getShipmentData")
	public void TC02_Verify_Shipment_Data(String TestID,String Weight, String Account,String DeclaredValue,String Execute,String Status){

		System.out.println(TestID+Weight+Account+DeclaredValue+Execute+Status); 
	}*/
}
