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
public class HDPortalManageUserGroupsTest extends HDPortalBaseTest{

	@Test(priority = 1, groups = "Smoke")
	/*@Test(priority = 1,groups="Smoke")*/
	public void accessManagementMenu() throws InterruptedException{
		hdPortalManageUserGroupsPage.clickManagementMenu();
	}

	@Test(priority = 2, groups = "Smoke")
	public void accessUserGroupsMenuItem(){
		hdPortalManageUserGroupsPage.clickUserGroupsOption();
		String actualUserGroupPanelTitle = hdPortalManageUserGroupsPage.verifyUserGroupsPanelTitle();
		Assert.assertEquals(actualUserGroupPanelTitle, StringConstants.USERGROUP_PANEL_TITLE);
	}

	@Test(priority = 3, groups = "Smoke")
	public void createNewUserGroup(){
		hdPortalManageUserGroupsPage.clickAddUserGroupsButtonOnToolBar();
		hdPortalManageUserGroupsPage.createUserGroup(TestData.USERGROUP_NAME);
		String actualUserGroupName = hdPortalManageUserGroupsPage.getUserGroupName();
		Assert.assertEquals(actualUserGroupName, TestData.USERGROUP_NAME);
	}

	@Test(priority = 4, groups = "Smoke")
	public void duplicateUserGroup() throws InterruptedException{
		hdPortalManageUserGroupsPage.selectUserGroup(TestData.USERGROUP_NAME);
		hdPortalManageUserGroupsPage.createDuplicateUserGroup(TestData.DUPLICATE_USERGROUP_NAME);
		//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		String actualUserGroupName = hdPortalManageUserGroupsPage.getDuplicateUserGroupName();
		Assert.assertEquals(actualUserGroupName, TestData.DUPLICATE_USERGROUP_NAME);
	}

	@Test(priority = 5, groups = "Smoke")
	public void verifyDuplicateUserGroup(){
		hdPortalManageUserGroupsPage.clickAddUserGroupsButtonOnToolBar();
		hdPortalManageUserGroupsPage.createUserGroup(TestData.USERGROUP_NAME.toLowerCase());
		hdPortalManageUserGroupsPage.validateExistingUserGroup();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		boolean isDuplicateErrorMessageDisplayed = hdPortalManageUserGroupsPage.isErrorMessageDisplayed();
		Assert.assertEquals(isDuplicateErrorMessageDisplayed, true);
	}

	@Test(priority = 6, groups = "Smoke")
	public void deleteCreatedUserGroup() throws InterruptedException{
		hdPortalManageUserGroupsPage.selectUserGroup(TestData.USERGROUP_NAME);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		hdPortalManageUserGroupsPage.deleteUserGroup(TestData.USERGROUP_NAME);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		boolean isUGDeleted = hdPortalManageUserGroupsPage.isUserGroupDeleted();
		Assert.assertEquals(isUGDeleted, true);
	}

	@Test(priority = 7, groups = "Smoke")
	public void deleteDuplicateUserGroup() throws InterruptedException{
		hdPortalManageUserGroupsPage.selectDuplicateUserGroup(TestData.DUPLICATE_USERGROUP_NAME);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		hdPortalManageUserGroupsPage.deleteDuplicateUserGroup(TestData.DUPLICATE_USERGROUP_NAME);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		boolean isUGDeleted = hdPortalManageUserGroupsPage.isDuplicateUserGroupDeleted();
		Assert.assertEquals(isUGDeleted, true);
	}


}
