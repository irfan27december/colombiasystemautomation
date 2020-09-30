package com.buddi.colombiaapp.samples.excel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLSXManager {
    private InputStream inputStream;
    private OutputStream opStream;
    private XSSFWorkbook workbook;
    private static XSSFSheet sheet;
    private static Row row;
    private Cell cell;
    private String filePath;
    private Logger logger = Logger.getLogger(XLSXManager.class);
    
    public static final String testDataExcelFileName = "testdata.xlsx";	
	private static String fileSeperator = System.getProperty("file.separator");
	private static String testDataExcelFilePath = System.getProperty("user.dir") +fileSeperator+ "testdata";
	private static String testDataExcelFileLocation =  testDataExcelFilePath +fileSeperator+ testDataExcelFileName;

    public XLSXManager(String filePath, String sheetName) {
        try {
            inputStream = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(inputStream);
            sheet = workbook.getSheet(sheetName);
            this.filePath = filePath;
            inputStream.close();
        } catch (Exception e) {
            logger.info("could not instantiate excel workbook", e);
        }
    }

    public String excelReadTextCell(int rowIndex, int colIndex) {
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

    public void excelWriteCell(int rowIndex, int colIndex, String valueToWrite) {
        row = sheet.getRow(rowIndex);
        if (row == null) {
            sheet.createRow(rowIndex);
            row = sheet.getRow(rowIndex);
        }
        cell = row.getCell(colIndex);
        if (cell == null) {
            row.createCell(colIndex);
            cell = row.getCell(colIndex);
        }
        cell.setCellValue(valueToWrite);
        try {
            opStream = new FileOutputStream(this.filePath);
            workbook.write(opStream);
            opStream.close();
            inputStream.close();
        } catch (Exception e) {
            logger.error("could not write the value", e);
        }
    }

    public String[][] getExcelDataFromARow(int rowNum) {
        int columns = getColumnCount();
        String[][] excelData = new String[1][columns];
        for (int j = 0; j < columns; j++) {
            excelData[0][j] = excelReadTextCell(rowNum, j);
            //System.out.println("Excel data from row number "+rowNum+ " is :"+excelData[0][j]);
        }
        return excelData;
    }

    public Object[][] getExcelData() {
        int rows = getRowCount();
        int columns = getColumnCount();
        Object[][] excelData = new Object[rows-1][columns];
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                excelData[i - 1][j] = excelReadTextCell(i, j);
                System.out.println("Data  "+excelData[i - 1][j]);
            }
        }
        return excelData;
    }

    public static int getRowCount() {
        int rowCount = sheet.getLastRowNum() + 1;
        return rowCount;
    }

    public static int getColumnCount() {
        row = sheet.getRow(0);
        int colCount = row.getLastCellNum();
        return colCount;
    }
    
    public static void main(String args[]){
    	XLSXManager data = new XLSXManager(testDataExcelFileLocation, "Alerts");
    	int rowCount = getRowCount(); 
    	System.out.println("Total rows are "+rowCount);
    	int columnCount = getColumnCount();
    	System.out.println("Total columns are "+columnCount);
    	 String[][] test = data.getExcelDataFromARow(1);
    	 for(int iRow = 0; iRow <= rowCount; iRow++){
    		 for(int iCol = 0; iCol < columnCount; iCol++){
    			 System.out.print(test[iRow][iCol]+ " ");
        	 }
    	 }
    	
    	//data.getExcelDataFromARow(1);
    	 
    	
    
    }
}