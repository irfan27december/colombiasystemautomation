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
		//hdPortalLoginPage.loginHDPortal(TestData.HDPORTAL_EMAIL, TestData.HDPORTAL_PASSWORD);
		driver.navigate().refresh();
		hdPortalManagePendingVisitsPage.clickVisitsMenu();
	}

	@Test(priority = 2, groups = "Smoke")
	public void accessPendingMenuItem() throws InterruptedException{
		hdPortalManagePendingVisitsPage.clickPendingOption();
		String actualPendingVisitsGridTitle = hdPortalManagePendingVisitsPage.verifyPendingVisitsGridTitle();
		Assert.assertEquals(actualPendingVisitsGridTitle, StringConstants.PENDING_VISITS_GRID_TITLE);
		Thread.sleep(5000);
	}
	
	@Test(priority = 3, groups = "Smoke")
	public void clickAddNewVisitToolBarButton() throws InterruptedException{
		hdPortalManagePendingVisitsPage.clickAddNewVisitToolBarButton(driver);
		Thread.sleep(5000);
		
	}

	


}
