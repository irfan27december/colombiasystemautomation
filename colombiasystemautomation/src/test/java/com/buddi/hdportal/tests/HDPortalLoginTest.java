/**
 * 
 */
package com.buddi.hdportal.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * @author irfan
 *
 */
public class HDPortalLoginTest extends HDPortalBaseTest{
	
	@Test(priority=0)
	public void launchHDPortal() throws InterruptedException{
		//HDPortalLoginPage hdPortalLoginPage = PageFactory.initElements(driver, HDPortalLoginPage.class);	
		Reporter.log("Launching HD Portal...");
		Thread.sleep(5000);
		String actualTitle = hdPortalLoginPage.verifyPageTitle();
		Assert.assertEquals(actualTitle, com.colombia.testdata.StringConstants.HDPORTAL_PAGE_TITLE);
		System.out.println("Launched HD portal...");
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
		System.out.println("Successfully logged in HD portal...");
		//hdPortalLoginPage.logoutHDPortal();	
		Thread.sleep(5000);
	}
}
