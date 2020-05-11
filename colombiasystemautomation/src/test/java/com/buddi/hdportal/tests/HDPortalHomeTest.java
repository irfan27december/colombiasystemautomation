/**
 * 
 */
package com.buddi.hdportal.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.colombia.testdata.StringConstants;

/**
 * @author irfan
 *
 */
public class HDPortalHomeTest extends HDPortalBaseTest{
	@Test(priority=3)
	public void verifyHelpDeskTitle() throws InterruptedException{
		Reporter.log("Launching HD Portal home page...");
		Thread.sleep(5000);
		boolean isHelpDeskTitleDisplayed = hdPortalHomePage.verifyHelpDeskTitle();
		Assert.assertEquals(isHelpDeskTitleDisplayed, true);	
		Assert.assertEquals(hdPortalHomePage.getHelpDeskTitle(), StringConstants.HD_MAIN_TITLE);
		boolean isStatusTitleDisplayed = hdPortalHomePage.verifyStatusTitle();
		Assert.assertEquals(isStatusTitleDisplayed, true);		
		Reporter.log("Launched HD Portal home page...");	
		System.out.println("Launching HD portal home page...");
	}
	
	/*@Test(priority=4)
	public void expandManagementMenu() throws InterruptedException{
		hdPortalHomePage.clickManagementMenu();
		Thread.sleep(5000);
	}*/
	
}
