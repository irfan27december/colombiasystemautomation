package com.buddi.hdportal.tests;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.buddi.colombia.testdata.StringConstants;
import com.buddi.colombia.testdata.TestData;
import com.buddi.colombia.utilities.GetExcelData;
import com.buddi.colombia.utilities.ReadExcelSheet;

/**
 * @author Ganesh
 *
 */

// Method to Access HD portal Login
public class HDPortalManageCreditNoteTest extends HDPortalBaseTest      
{


	public static XSSFSheet sheet;
	public static Object[][] RecordCreditNoteData;
	// create instance of Random class 
	static Random random = new Random(); 
	// Generate random integers in range 0 to 999 
	static int randomInt = random.nextInt(1000);

	@DataProvider(name="getRecordCreditNoteData")
	public static Object[][] getData() throws Exception{
		sheet = ReadExcelSheet.DataSheet(GetExcelData.testDataExcelFileLocation, "RecordCreditNote");
		int rowCount = sheet.getLastRowNum();
		//System.out.println("Row  count:"+rowCount);
		int columnCount = sheet.getRow(0).getLastCellNum();
		//System.out.println("Column   count"+columnCount);
		RecordCreditNoteData = new Object[rowCount][columnCount];
		for (int rowCnt = 1; rowCnt <= rowCount; rowCnt++){
			for (int columnCnt = 0; columnCnt < columnCount; columnCnt++){
				RecordCreditNoteData[rowCnt-1][columnCnt] = GetExcelData.getCellData("RecordCreditNote", rowCnt, columnCnt);
				//System.out.println(KnowledgeBaseData[rowCnt-1][columnCnt]);
			}
		}
		return RecordCreditNoteData;
	}





	@Test(priority = 1, groups = "Smoke")
	public void accessManagementMenuForCR() throws InterruptedException
	{
		//hdPortalLoginPage.loginHDPortal("ganesh.chilakamarthi@buddi.co.uk", "Shankar@18");
		driver.navigate().refresh();
		hdPortalManageCreditNotePage.clickInventoryMenu();
	}


	// Method to Access Inventory menu for Credit note
	@Test(priority = 2, groups = "Smoke",dependsOnMethods={"accessManagementMenuForCR"})
	public void accessRecordCreditNoteMenuItem()
	{
		hdPortalManageCreditNotePage.clickCreditNoteOption();
		String actualCreditNoteTitle = hdPortalManageCreditNotePage.verifyAddNewRecordDialogTitle();
		Assert.assertEquals(actualCreditNoteTitle, StringConstants.CREDITNOTE_PANEL_TITLE);
	}	


	@Test(priority = 3, groups = "Smoke",dependsOnMethods = {"accessManagementMenuForCR"},dataProvider = "getRecordCreditNoteData")
	public void createNewCreditNote(String UbinNumber, String wearer, String Notes, String Observation, String RecordStartDateFormat, String RecordEndDateFormat) throws InterruptedException
	{
		//hdPortalManageKnowledgeBasePage.navigateToKnowledgeBaseOption();
		hdPortalManageCreditNotePage.clickToolsBarAddRecordCreditNotesButton();
		UbinNumber = UbinNumber + randomInt;
		hdPortalManageCreditNotePage.createNewRecordCreditNote(UbinNumber,wearer,Notes,Observation,RecordStartDateFormat,RecordEndDateFormat);
		hdPortalManageCreditNotePage.navigateToNewRecordOption();
		driver.navigate().refresh();
	}



	/* // Method to enter details in Credit note window
	     @Test(priority = 3, groups = "Smoke", dependsOnMethods={"accessRecordCreditNoteMenuItem"},dataProvider = "getRecordCreditNoteData")
	     public void createNewCreditNote() throws InterruptedException
	     {
		  	driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		    hdPortalManageCreditNotePage.clickToolsBarAddRecordCreditNotesButton();
		    hdPortalManageCreditNotePage.createNewRecordCreditNote(TestData.RECORDCREDITNOTE_UBIN_ITEMNUMBER,TestData.RECORDCREDITNOTE_WEARER_NAME,TestData.RECORDCREDITNOTE_NOTES,TestData.RECORDCREDITNOTE_OBSERVATION,TestData.RECORDCREDITNOTE_START_DATE_FORMAT,TestData.RECORDCREDITNOTE_END_DATE_FORMAT);
		    driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		    driver.navigate().refresh();
		    driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

		 } */




}
