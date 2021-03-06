package com.buddi.hdportal.tests;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.buddi.colombia.testdata.StringConstants;
import com.buddi.colombia.testdata.TestData;
import com.buddi.colombia.utilities.GetExcelData;

/**
 * @author irfan
 *
 */

public class HDPortalManageAlertHistoryTest extends HDPortalBaseTest{
	static String sheetName = "Alerts";

	//Access Alert History and verify grid title
	@Test(priority = 1, groups = "Smoke")
	public void accessAlertHistoryMenuAndVerifyGridTitle(){
		SoftAssert verify = new SoftAssert();
		//hdPortalLoginPage.loginHDPortal(TestData.HDPORTAL_EMAIL, TestData.HDPORTAL_PASSWORD);
		driver.navigate().refresh();
		hdPortalManageAlertHistoryPage.clickAlertHistoryMenu();
		String actualAlertHistoryGridTitle = hdPortalManageAlertHistoryPage.verifyAlertHistoryGridTitle();
		Assert.assertEquals(actualAlertHistoryGridTitle, StringConstants.ALERT_HISTORY_GRID_TITLE);
		verify.assertAll();
	}

	/*//Open and Wearer column names are not validated
	@Test(priority = 2, groups = "Smoke",dependsOnMethods={"accessAlertHistoryMenuAndVerifyGridTitle"})
	public void verifyColumnNamesInAlertHistoryPageGrid(){
		hdPortalManageAlertHistoryPage.navigateToAlertHistoryOption();
		LinkedList<String> list = hdPortalManageAlertHistoryPage.returnAlertsPageColumns();		
		//Iterating LinkedList
		Iterator<String> iterator = hdPortalManageAlertHistoryPage.returnAlertsPageColumns().iterator();
		while(iterator.hasNext()){
			String expectedColumnName = iterator.next();
			String actualColumnName = hdPortalManageAlertHistoryPage.returnColumnNameElement(expectedColumnName).getText();
			System.out.println("Actual column name    "+actualColumnName + " = " + "Expected column name   "+expectedColumnName );
			Assert.assertEquals(actualColumnName, expectedColumnName);
		}
	}*/

	//Open alert detail page
	@Test(priority = 3, groups = "Smoke",dependsOnMethods={"accessAlertHistoryMenuAndVerifyGridTitle"})
	public void openAlertDetailPage(){
		SoftAssert verify = new SoftAssert();
		//hdPortalManageAlertHistoryPage.navigateToAlertHistoryOption();
		String wearerName =  GetExcelData.getCellData(sheetName, 1, 0);
		String alertStartDateFormat =  GetExcelData.getCellData(sheetName, 1, 2);
		String wearerNUI =  GetExcelData.getCellData(sheetName, 1, 6);
		wearerNUI = String.valueOf(wearerNUI);
		/*hdPortalManageAlertHistoryPage.openAlert(TestData.ALERT_WEARER_NAME, TestData.ALERT_START_DATE_FORMAT,TestData.WEARER_NUI);*/
		hdPortalManageAlertHistoryPage.openAlert(wearerName, alertStartDateFormat,wearerNUI);
		verify.assertAll();
	}
 
	
	//Verify reopen alert button in detail page
	@Test(priority = 4, groups = "Smoke",dependsOnMethods={"openAlertDetailPage"})
	public void verifyReopenAlertButtonInAlertDetailPage(){
		SoftAssert verify = new SoftAssert();
		//hdPortalManageAlertHistoryPage.openAlert(TestData.ALERT_WEARER_NAME, TestData.ALERT_START_DATE_FORMAT,TestData.WEARER_NUI);
		hdPortalManageAlertHistoryPage.verifyReopenAlertButton();
		//hdPortalManageAlertHistoryPage.verifyClosedAlertPanelHeading();
		verify.assertAll();
	}

}
