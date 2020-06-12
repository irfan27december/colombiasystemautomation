/**
 * 
 */
package com.buddi.hdportal.tests;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.colombia.testdata.StringConstants;
import com.colombia.testdata.TestData;

/**
 * @author irfan
 *
 */
public class HDPortalManagementTest extends HDPortalBaseTest{

	@Test(priority=4, groups = "Smoke")
	/*@Test(priority = 1,groups="Smoke")*/
	public void expandManagementMenu() throws InterruptedException{
		hdPortalManagementPage.clickManagementMenu();
	}

	@Test(priority=5, groups = "Smoke")
	public void clickUserGroupsMenuItem() throws InterruptedException{
		hdPortalManagementPage.clickUserGroupsOption();
		String actualUserGroupPanelTitle = hdPortalManagementPage.verifyUserGroupsPanelTitle();
		Assert.assertEquals(actualUserGroupPanelTitle, StringConstants.USERGROUP_PANEL_TITLE);
	}

	@Test(priority=6, groups = "Smoke")
	public void addUserGroup() throws InterruptedException{
		hdPortalManagementPage.clickAddUserGroupsButton();
		hdPortalManagementPage.createUserGroup();
		//hdPortalManagementPage.selectUserGroup();
		String actualUserGroupName = hdPortalManagementPage.getUserGroupName();
		Assert.assertEquals(actualUserGroupName, TestData.USERGROUP_NAME);
	}
	
	@Test(priority=7, groups = "Smoke")
	public void deleteCreatedUserGroup() throws InterruptedException{
		hdPortalManagementPage.deleteUserGroup();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		boolean isUGDeleted = hdPortalManagementPage.isUserGroupDeleted();
		//System.out.println("Is UG deleted "+isUGDeleted);
		Assert.assertEquals(isUGDeleted, true);
	}

}
