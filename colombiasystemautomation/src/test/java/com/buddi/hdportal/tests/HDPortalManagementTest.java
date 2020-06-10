/**
 * 
 */
package com.buddi.hdportal.tests;

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
		//Thread.sleep(5000);
	}

	@Test(priority=5, groups = "Smoke")
	public void clickUserGroupsMenuItem() throws InterruptedException{
		hdPortalManagementPage.clickUserGroups();
		//Thread.sleep(5000);
		String actualUserGroupPanelTitle = hdPortalManagementPage.verifyUserGroupsPanelTitle();
		Assert.assertEquals(actualUserGroupPanelTitle, StringConstants.USERGROUP_PANEL_TITLE);
	}

	@Test(priority=6, groups = "Smoke")
	public void addUserGroup() throws InterruptedException{
		hdPortalManagementPage.clickAddUserGroupsButton();
		//Thread.sleep(5000);
		hdPortalManagementPage.createUserGroup();
		Thread.sleep(5000);
		String actualUserGroupName = hdPortalManagementPage.getUserGroupName();
		Assert.assertEquals(actualUserGroupName, TestData.USERGROUP_NAME);
	}
	
	@Test(priority=7, groups = "Smoke")
	public void deleteCreatedUserGroup() throws InterruptedException{
		hdPortalManagementPage.deleteUserGroup();
		Thread.sleep(5000);
		boolean isUGDeleted = hdPortalManagementPage.isUserGroupDeleted();
		Assert.assertEquals(isUGDeleted, true);
	}

}
