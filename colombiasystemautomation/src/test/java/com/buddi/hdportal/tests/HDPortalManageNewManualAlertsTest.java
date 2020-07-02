package com.buddi.hdportal.tests;

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
	
}
