package com.buddi.hdportal.tests;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.buddi.colombia.testdata.StringConstants;
import com.buddi.colombia.testdata.TestData;
import com.buddi.colombia.utilities.GetExcelData;
import com.buddi.colombia.utilities.ReadExcelSheet;

/**
 * @author irfan
 *
 */

public class HDPortalManageNewManualAlertsTest extends HDPortalBaseTest{

	public static XSSFSheet sheet;
	public static Object[][] AlertsData;
	static String sheetName = "Alerts";

	@DataProvider(name="getAlertsData")
	public static Object[][] getData() throws Exception{
		sheet = ReadExcelSheet.DataSheet(GetExcelData.testDataExcelFileLocation, sheetName);
		int rowCount = sheet.getLastRowNum();
		//System.out.println("Row  count:"+rowCount);
		int columnCount = sheet.getRow(0).getLastCellNum() - 1;
		//System.out.println("Column   count: "+columnCount);
		AlertsData = new Object[rowCount][columnCount];
		for (int rowCnt = 1; rowCnt <= rowCount; rowCnt++){
			for (int columnCnt = 0; columnCnt < columnCount; columnCnt++){
				AlertsData[rowCnt-1][columnCnt] = GetExcelData.getCellData(sheetName, rowCnt, columnCnt);
				//System.out.println(AlertsData[rowCnt-1][columnCnt]);
			}
		}
		return AlertsData;
	}

	//Access New Alerts menu and verify grid title
	@Test(priority = 1, groups = "Smoke")
	public void accessNewAlertsMenuAndVerifyGridTitle(){
		hdPortalManageNewManualAlertsPage.clickNewAlertstMenu();
		String actualNewAlertsGridTitle = hdPortalManageNewManualAlertsPage.verifyNewAlertsGridTitle();
		Assert.assertEquals(actualNewAlertsGridTitle, StringConstants.ALERTS_GRID_TITLE);
	}

	//Open and Wearer column names are not validated
	@Test(priority = 2, groups = "Smoke", dependsOnMethods={"accessNewAlertsMenuAndVerifyGridTitle"})
	public void verifyColumnNamesInNewAlertsPageGrid(){
		hdPortalManageNewManualAlertsPage.navigateToNewAlertsOption();
		LinkedList<String> list = hdPortalManageNewManualAlertsPage.returnNewAlertsPageColumns();		
		//Iterating LinkedList
		Iterator<String> iterator = hdPortalManageNewManualAlertsPage.returnNewAlertsPageColumns().iterator();
		while(iterator.hasNext()){
			String expectedColumnName = iterator.next();
			String actualColumnName = hdPortalManageNewManualAlertsPage.returnColumnNameElement(expectedColumnName).getText();
			//System.out.println("Actual column name    "+actualColumnName + " = " + "Expected column name   "+expectedColumnName );
			Assert.assertEquals(actualColumnName, expectedColumnName);
		}
	}

	//Method to create new alert
	@Test(priority = 3, groups = "Smoke", dependsOnMethods={"accessNewAlertsMenuAndVerifyGridTitle"},dataProvider="getAlertsData")
	public void createNewAlert(String alertWearerName, String alertSeverity, String alertStartDateFormat, String alertStartHours, 
			String alertStartMinutes, String alertNotes) throws InterruptedException{
		hdPortalManageNewManualAlertsPage.navigateToNewAlertsOption();
		hdPortalManageNewManualAlertsPage.clickToolsBarAddAlertButton();
		String actualAddNewAlertDialogTitle = hdPortalManageNewManualAlertsPage.verifyAddNewAlertDialogTitle();
		Assert.assertEquals(actualAddNewAlertDialogTitle, StringConstants.ADD_NEW_ALERT_DIALOG_TITLE);
		/*hdPortalManageNewManualAlertsPage.createNewAlert(TestData.ALERT_WEARER_NAME, TestData.ALERT_SEVERITY, TestData.ALERT_START_DATE_FORMAT,
				TestData.ALERT_START_HOUR, TestData.ALERT_START_MINUTES, TestData.ALERT_NOTES);*/
		hdPortalManageNewManualAlertsPage.createNewAlert(alertWearerName, alertSeverity, alertStartDateFormat, alertStartHours, 
								alertStartMinutes, alertNotes);

		/*boolean isWearerNameAndNUIPresentInAlertsDetailPage = hdPortalManageNewManualAlertsPage.isWearerNameNUIDisplayedInAlertsDetailPage(
				TestData.ALERT_WEARER_NAME, TestData.WEARER_NUI);*/
		String wearerName =  GetExcelData.getCellData(sheetName, 1, 0);
		String wearerNUI =  GetExcelData.getCellData(sheetName, 1, 6);
		wearerNUI = String.valueOf(wearerNUI);
		
		System.out.println("Wearer name is:"+wearerName + "  =====    "+" wearer NUI is: "+wearerNUI);
		boolean isWearerNameAndNUIPresentInAlertsDetailPage = hdPortalManageNewManualAlertsPage.isWearerNameNUIDisplayedInAlertsDetailPage(
				wearerName, wearerNUI);
		Assert.assertEquals(isWearerNameAndNUIPresentInAlertsDetailPage, true);

	}

	//Method to verify the newly created alert
	@Test(priority = 4, groups = "Smoke",dependsOnMethods={"createNewAlert"})
	public void verifyNewlyCreatedAlert(){
		driver.navigate().refresh();
		hdPortalManageInProgressAlertsPage.clickInProgressAlertsMenu();
		driver.manage().timeouts().implicitlyWait(50000, TimeUnit.MILLISECONDS);
		String actualInProgressAlertsGridTitle = hdPortalManageInProgressAlertsPage.verifyInProgressAlertsGridTitle();
		Assert.assertEquals(actualInProgressAlertsGridTitle, StringConstants.IN_PROGRESS_ALERTS_GRID_TITLE);
		boolean isWearerNUIDisplayedInInProgressListPage = hdPortalManageNewManualAlertsPage.isWearerNUIDisplayedInInProgressListPage(TestData.WEARER_NUI);
		Assert.assertEquals(isWearerNUIDisplayedInInProgressListPage, true);
		boolean isWearerNameDisplayedInInProgressListPage = hdPortalManageNewManualAlertsPage.isWearerNameDisplayedInInProgressListPage(TestData.ALERT_WEARER_NAME);
		Assert.assertEquals(isWearerNameDisplayedInInProgressListPage, true);
	}

}
