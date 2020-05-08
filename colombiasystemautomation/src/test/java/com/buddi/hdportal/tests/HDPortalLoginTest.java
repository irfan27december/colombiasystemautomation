/**
 * 
 */
package com.buddi.hdportal.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.colombia.utilities.ReadProperties;

/**
 * @author irfan
 *
 */
public class HDPortalLoginTest extends HDPortalBaseTest{

	ReadProperties properties = new ReadProperties();
	@Test(priority=0)
	public void launchHDPortal() throws InterruptedException{
		//HDPortalLoginPage hdPortalLoginPage = PageFactory.initElements(driver, HDPortalLoginPage.class);	
		Reporter.log("Launching HD Portal...");
		Thread.sleep(5000);
		String actualTitle = hdPortalLoginPage.verifyPageTitle();
		Assert.assertEquals(actualTitle, com.colombia.testdata.StringConstants.HDPORTAL_PAGE_TITLE);
		Reporter.log("Launched HD Portal...");				
	}


	@Test(priority=1)
	public void loginHDPortal() throws InterruptedException{
		//HDPortalLoginPage hdPortalLoginPage = PageFactory.initElements(driver, HDPortalLoginPage.class);	
		hdPortalLoginPage.loginHDPortal();
		/*boolean isBuddiLogoPresent = true;
		Assert.assertEquals(isBuddiLogoPresent, hdPortalLoginPage.verifybuddiLogo());*/
		Thread.sleep(5000);
		String actualTitle = hdPortalLoginPage.verifyPageTitle();
		Assert.assertEquals(actualTitle, com.colombia.testdata.StringConstants.HDPORTAL_PAGE_TITLE);
		//hdPortalLoginPage.logoutHDPortal();	
		Thread.sleep(5000);
	}
	
	/*
	@Test(priority=3)
	public void verifyHelpDeskTitle() throws InterruptedException{
		System.out.println("verifyHelpDeskTitle .... method");
		//HDPortalHomePage hdPortalHomePage = PageFactory.initElements(driver, HDPortalHomePage.class);	
		Reporter.log("Launching HD Portal home page...");
		Thread.sleep(10000);
		//boolean isHelpDeskTitleDisplayed = hdPortalHomePage.verifyHelpDeskTitle();
		//Assert.assertEquals(isHelpDeskTitleDisplayed, true);




		boolean isStatusTitleDisplayed = hdPortalHomePage.verifyStatusTitle();
		System.out.println("isStatusTitleDisplayed   "+isStatusTitleDisplayed);
		Assert.assertEquals(isStatusTitleDisplayed, true);

		Reporter.log("Launched HD Portal home page...");				
	}*/

}
