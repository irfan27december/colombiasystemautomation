package com.buddi.hdportal.tests;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.buddi.colombia.testdata.StringConstants;
import com.buddi.colombia.testdata.TestData;

/**
 * @author irfan
 *
 */

public class HDPortalManageNewManualAlertsTest extends HDPortalBaseTest{

	@Test(priority = 1, groups = "Smoke")
	public void accessNewAlertsMenuAndVerifyPanelTitle(){
		hdPortalManageNewManualAlertsPage.clickNewAlertstMenu();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		String actualNewAlertsPanelTitle = hdPortalManageNewManualAlertsPage.verifyNewAlertsPanelTitle();
		Assert.assertEquals(actualNewAlertsPanelTitle, StringConstants.ALERTS_PANEL_TITLE);
	}


	@Test(priority = 2, groups = "Smoke")
	//Open and Wearer column names are not validated
	public void verifyColumnNamesInNewAlertsPageGrid(){
		hdPortalManageNewManualAlertsPage.navigateToNewAlertsOption();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		LinkedList<String> list = hdPortalManageNewManualAlertsPage.returnNewAlertsPageColumns();		
		//Iterating LinkedList
		Iterator<String> iterator = hdPortalManageNewManualAlertsPage.returnNewAlertsPageColumns().iterator();
		System.out.println("List size is "+list.size());
		while(iterator.hasNext()){
			String expectedColumnName = iterator.next();
			String actualColumnName = hdPortalManageNewManualAlertsPage.returnColumnNameElement(expectedColumnName).getText();
			System.out.println("Actual column name    "+actualColumnName + " = " + "Expected column name   "+expectedColumnName );
			Assert.assertEquals(actualColumnName, expectedColumnName);
		}
	}

	@Test(priority = 3, groups = "Smoke")
	public void createNewAlert(){
		hdPortalManageNewManualAlertsPage.navigateToNewAlertsOption();
		hdPortalManageNewManualAlertsPage.clickToolsBarAddAlertButton();
		String actualAddNewAlertDialogTitle = hdPortalManageNewManualAlertsPage.verifyAddNewAlertDialogTitle();
		Assert.assertEquals(actualAddNewAlertDialogTitle, StringConstants.ADD_NEW_ALERT_DIALOG_TITLE);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		hdPortalManageNewManualAlertsPage.createNewAlert(TestData.ALERT_WEARER_NAME, TestData.ALERT_SEVERITY, TestData.ALERT_START_DATE_FORMAT,
				TestData.ALERT_START_HOUR, TestData.ALERT_START_MINUTES, TestData.ALERT_NOTES);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.navigate().refresh();
		hdPortalManageInProgressAlertsPage.clickInProgressAlertstMenu();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		String actualInProgressAlertsGridTitle = hdPortalManageInProgressAlertsPage.verifyInProgressAlertsPanelTitle();
		Assert.assertEquals(actualInProgressAlertsGridTitle, StringConstants.IN_PROGRESS_ALERTS_PANEL_TITLE);
		boolean isWearerNUIDisplayInInProgressListPage = hdPortalManageNewManualAlertsPage.isWearerNUIDisplayedInInProgressListPage(TestData.WEARER_NUI);
		Assert.assertEquals(isWearerNUIDisplayInInProgressListPage, true);
	}

}
