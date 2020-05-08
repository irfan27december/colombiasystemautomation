/**
 * 
 */
package com.buddi.hdportal.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.colombia.testdata.StringConstants;
import com.colombia.utilities.ReadProperties;

/**
 * @author irfan
 *
 */
public class HDPortalHomeTest extends HDPortalBaseTest{
	
	ReadProperties properties = new ReadProperties();
	@Test(priority=3)
	public void verifyHelpDeskTitle() throws InterruptedException{
		System.out.println("verifyHelpDeskTitle .... method");
		//HDPortalHomePage hdPortalHomePage = PageFactory.initElements(driver, HDPortalHomePage.class);	
		Reporter.log("Launching HD Portal home page...");
		Thread.sleep(5000);
		boolean isHelpDeskTitleDisplayed = hdPortalHomePage.verifyHelpDeskTitle();
		Assert.assertEquals(isHelpDeskTitleDisplayed, true);
		
		boolean isStatusTitleDisplayed = hdPortalHomePage.verifyStatusTitle();
		System.out.println("isStatusTitleDisplayed   "+isStatusTitleDisplayed);
		Assert.assertEquals(isStatusTitleDisplayed, true);
		
		Reporter.log("Launched HD Portal home page...");				
	}
	
	

}
