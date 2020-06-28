/**
 * 
 */
package com.buddi.hdportal.tests;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.buddi.colombia.testdata.StringConstants;
import com.buddi.colombia.testdata.TestData;

/**
 * @author irfan
 *
 */
public class HDPortalLoginTest extends HDPortalBaseTest{
	
	//Method to launch HD portal
	@Test(priority = 0, groups = "Smoke")
	public void launchHDPortal() throws InterruptedException{
		//HDPortalLoginPage hdPortalLoginPage = PageFactory.initElements(driver, HDPortalLoginPage.class);	
		Reporter.log("Launching HD Portal...");
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		String actualTitle = hdPortalLoginPage.verifyPageTitle();
		Assert.assertEquals(actualTitle, StringConstants.HDPORTAL_PAGE_TITLE);
		System.out.println("Launched HD portal...");
		Reporter.log("Launched HD Portal...");				
	}

	//Method to login HD portal
	@Test(priority = 1, groups = "Smoke")
	public void loginHDPortal(){
		//HDPortalLoginPage hdPortalLoginPage = PageFactory.initElements(driver, HDPortalLoginPage.class);	
		hdPortalLoginPage.loginHDPortal(TestData.HDPORTAL_EMAIL, TestData.HDPORTAL_PASSWORD);
		/*boolean isBuddiLogoPresent = true;
		Assert.assertEquals(isBuddiLogoPresent, hdPortalLoginPage.verifybuddiLogo());*/
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		String actualTitle = hdPortalLoginPage.verifyPageTitle();
		Assert.assertEquals(actualTitle, com.buddi.colombia.testdata.StringConstants.HDPORTAL_PAGE_TITLE);
		System.out.println("Successfully logged in HD portal...");
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
	}
}
