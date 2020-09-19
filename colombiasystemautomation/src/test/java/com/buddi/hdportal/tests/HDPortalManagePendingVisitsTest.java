/**
 * 
 */
package com.buddi.hdportal.tests;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.buddi.colombia.testdata.StringConstants;
import com.buddi.colombia.testdata.TestData;

/**
 * @author irfan
 *
 */
public class HDPortalManagePendingVisitsTest extends HDPortalBaseTest{

	@Test(priority = 1, groups = "Smoke")
	public void accessVisitsMenu() throws InterruptedException{
		hdPortalManagePendingVisitsPage.clickVisitsMenu();
	}

	@Test(priority = 2, groups = "Smoke")
	public void accessPendingMenuItem(){
		hdPortalManagePendingVisitsPage.clickPendingOption();
		String actualPendingVisitsGridTitle = hdPortalManagePendingVisitsPage.verifyPendingVisitsGridTitle();
		Assert.assertEquals(actualPendingVisitsGridTitle, StringConstants.PENDING_VISITS_GRID_TITLE);
	}

	


}
