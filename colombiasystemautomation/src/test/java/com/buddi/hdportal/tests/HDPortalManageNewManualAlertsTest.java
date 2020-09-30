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
	@Test(priority = 3, groups = "Smoke", dependsOnMethods={"accessNewAlertsMenuAndVerifyGridTitle"})
	public void createNewAlert() throws InterruptedException{
		hdPortalManageNewManualAlertsPage.navigateToNewAlertsOption();
		hdPortalManageNewManualAlertsPage.clickToolsBarAddAlertButton();
		String actualAddNewAlertDialogTitle = hdPortalManageNewManualAlertsPage.verifyAddNewAlertDialogTitle();
		Assert.assertEquals(actualAddNewAlertDialogTitle, StringConstants.ADD_NEW_ALERT_DIALOG_TITLE);
		hdPortalManageNewManualAlertsPage.createNewAlert(TestData.ALERT_WEARER_NAME, TestData.ALERT_SEVERITY, TestData.ALERT_START_DATE_FORMAT,
				TestData.ALERT_START_HOUR, TestData.ALERT_START_MINUTES, TestData.ALERT_NOTES);
		boolean isWearerNameAndNUIPresentInAlertsDetailPage = hdPortalManageNewManualAlertsPage.isWearerNameNUIDisplayedInAlertsDetailPage(
				TestData.ALERT_WEARER_NAME, TestData.WEARER_NUI);
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
