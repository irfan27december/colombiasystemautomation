/**
 * 
 */
package com.buddi.hdportal.tests;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.buddi.colombia.testdata.StringConstants;

/**
 * @author irfan
 *
 */
public class HDPortalHomeTest extends HDPortalBaseTest{
	
	//Method to verify help desk title in home page
	@Test(priority = 1, groups = "Smoke")
	public void verifyHelpDeskTitleInHomePage() throws InterruptedException{
		Reporter.log("Launching HD Portal home page...");
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		boolean isHelpDeskTitleDisplayed = hdPortalHomePage.verifyHelpDeskMainTitle();
		Assert.assertEquals(isHelpDeskTitleDisplayed, true);	
		Assert.assertEquals(hdPortalHomePage.getHelpDeskTitle(), StringConstants.HD_MAIN_TITLE);
		boolean isStatusTitleDisplayed = hdPortalHomePage.verifyStatusTitle();
		Assert.assertEquals(isStatusTitleDisplayed, true);		
		Reporter.log("Launched HD Portal home page...");	
		System.out.println("Login to HD portal is successfull and HD portal home page has appeared...");
	}

	
}
