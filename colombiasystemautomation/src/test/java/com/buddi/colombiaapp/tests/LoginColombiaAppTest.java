package com.buddi.colombiaapp.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

/**
 * @author irfan
 *
 */

import org.testng.annotations.Test;

import com.buddi.colombia.testdata.StringConstants;
import com.buddi.colombiapp.pages.LoginColombiaAppPage;

public class LoginColombiaAppTest extends AppiumBaseTest{
	
	@Test(priority = 0)
	public void getAppHomeScreenMainTitle() throws InterruptedException{
		LoginColombiaAppPage loginColombiaAppPage = PageFactory.initElements(driver, LoginColombiaAppPage.class);
		boolean isAppHomeScreenMainTitlePresent = true;
		Assert.assertEquals(isAppHomeScreenMainTitlePresent, loginColombiaAppPage.isAppHomeScreenMainTitlePresent());
		Assert.assertEquals(loginColombiaAppPage.getAppHomeScreenTitle(), StringConstants.APP_HOMESCREEN_TITLE);
	}
	
	@Test(priority = 1)
	public void getAppHomeScreenSubTitle() throws InterruptedException{
		LoginColombiaAppPage loginColombiaAppPage = PageFactory.initElements(driver, LoginColombiaAppPage.class);
		boolean isAppHomeScreenSubTitlePresent = true;
		Assert.assertEquals(isAppHomeScreenSubTitlePresent, loginColombiaAppPage.isAppHomeScreenSubTitlePresent());
		Assert.assertEquals(loginColombiaAppPage.getAppHomeScreenSubTitle(), StringConstants.APP_HOMESCREEN_SUBTITLE);
	}

	@Test(priority = 2)
	public void loginColombiaApp() throws InterruptedException{
		LoginColombiaAppPage loginColombiaAppPage = PageFactory.initElements(driver, LoginColombiaAppPage.class);
		loginColombiaAppPage.getPhoneID();
		Reporter.log("Launching app...");
		loginColombiaAppPage.loginColombiaApp();
		Reporter.log("Launched app...");
		Thread.sleep(10000);
		boolean isVisitsScreenTitlePresent = true;
		Assert.assertEquals(isVisitsScreenTitlePresent, loginColombiaAppPage.isVisitsScreenTitlePresent());
	}

	/*@Test(priority = 3)
	public void verifyNoJobsScheduledText() throws InterruptedException{
		LoginColombiaAppPage loginColombiaAppPage = PageFactory.initElements(driver, LoginColombiaAppPage.class);
		Thread.sleep(5000);
		String actualNoJobsText = loginColombiaAppPage.getNoJobsText();
		Assert.assertEquals(actualNoJobsText, StringConstants.APP_NO_JOBS_TXT);
	}*/
	
	@Test(priority = 4)
	public void logOutColombiaApp() throws InterruptedException{
		LoginColombiaAppPage loginColombiaAppPage = PageFactory.initElements(driver, LoginColombiaAppPage.class);		
		loginColombiaAppPage.logOutColombiaApp();
		Reporter.log("Logged out app...");
		Thread.sleep(5000);
	}


}
