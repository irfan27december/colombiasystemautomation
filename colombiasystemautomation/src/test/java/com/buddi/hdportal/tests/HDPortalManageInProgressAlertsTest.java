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

public class HDPortalManageInProgressAlertsTest extends HDPortalBaseTest{

	//Access In Progress menu and 
	@Test(priority = 1, groups = "Smoke")
	public void accessInProgressAlertsMenuAndVerifyGridTitle(){
		driver.navigate().refresh();
		hdPortalManageInProgressAlertsPage.clickInProgressAlertsMenu();
		//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		String actualInProgressAlertsGridTitle = hdPortalManageInProgressAlertsPage.verifyInProgressAlertsGridTitle();
		Assert.assertEquals(actualInProgressAlertsGridTitle, StringConstants.IN_PROGRESS_ALERTS_GRID_TITLE);
	}

	//Open and Wearer column names are not validated
	@Test(priority = 2, groups = "Smoke",dependsOnMethods={"accessInProgressAlertsMenuAndVerifyGridTitle"})
	public void verifyColumnNamesInInProgressAlertsPageGrid(){
		hdPortalManageInProgressAlertsPage.navigateToInProgressAlertsOption();
		//LinkedList<String> list = hdPortalManageInProgressAlertsPage.returnNewAlertsPageColumns();		
		//Iterating LinkedList
		Iterator<String> iterator = hdPortalManageInProgressAlertsPage.returnAlertsPageColumns().iterator();
		while(iterator.hasNext()){
			String expectedColumnName = iterator.next();
			String actualColumnName = hdPortalManageInProgressAlertsPage.returnColumnNameElement(expectedColumnName).getText();
			//System.out.println("Actual column name    "+actualColumnName + " = " + "Expected column name   "+expectedColumnName );
			Assert.assertEquals(actualColumnName, expectedColumnName);
		}
	}

	//Open alert detail page
	@Test(priority = 3, groups = "Smoke",dependsOnMethods={"accessInProgressAlertsMenuAndVerifyGridTitle"})
	public void openAlertDetailPage() throws InterruptedException{
		//hdPortalManageInProgressAlertsPage.navigateToInProgressAlertsOption();
		hdPortalManageInProgressAlertsPage.openAlert(TestData.ALERT_WEARER_NAME, TestData.ALERT_START_DATE_FORMAT,TestData.WEARER_NUI);
	}
	
	//Confirm alert detail page
	@Test(priority = 4, groups = "Smoke",dependsOnMethods={"openAlertDetailPage"})
	public void confirmAlertFromDetailsPage() throws InterruptedException{
		hdPortalManageInProgressAlertsPage.confirmAlert(TestData.CLOSE_ALERT_NOTES);
		hdPortalManageInProgressAlertsPage.closeAlertDetailPage();
	}
	
	/*
	//Verify reopen alert button in detail page
	@Test(priority = 5, groups = "Smoke",dependsOnMethods={"confirmAlertFromDetailsPage"})
	public void verifyReopenAlertButtonInAlertDetailPage() throws InterruptedException{
		hdPortalManageInProgressAlertsPage.openAlert(TestData.ALERT_WEARER_NAME, TestData.ALERT_START_DATE_FORMAT,TestData.WEARER_NUI);
		hdPortalManageInProgressAlertsPage.verifyReopenAlertButton();
		hdPortalManageInProgressAlertsPage.verifyClosedAlertPanelHeading();
	}*/

}
