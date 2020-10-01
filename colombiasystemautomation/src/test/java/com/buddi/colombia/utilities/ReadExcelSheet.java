package com.buddi.colombia.utilities;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelSheet {
    public static XSSFWorkbook WBook = null;
    public static XSSFSheet WSheet= null;
    

    public static XSSFSheet DataSheet(String filePath, String sheetName){
        File file = new File(filePath);
        try {
            FileInputStream fis = new FileInputStream(file);
            WBook = new XSSFWorkbook(fis);
            WSheet = WBook.getSheet(sheetName);         
        } catch (Exception e) {         
            e.printStackTrace();
        }
        return WSheet;      
    }       

}
