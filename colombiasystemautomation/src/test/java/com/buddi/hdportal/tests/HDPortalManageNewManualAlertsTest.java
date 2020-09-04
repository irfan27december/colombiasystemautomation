package com.buddi.hdportal.tests;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.buddi.colombia.testdata.StringConstants;

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
		Assert.assertEquals(actualNewAlertsPanelTitle, StringConstants.NEWALERTS_PANEL_TITLE);
	}


	@Test(priority = 2, groups = "Smoke")
	//Open and Wearer column names are not validated
	public void verifyColumnNamesInNewAlertsPageGrid(){
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

}
