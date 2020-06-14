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
	public void duplicateUserGroup() throws InterruptedException{
		hdPortalManagementPage.saveDuplicateUserGroup();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		String actualUserGroupName = hdPortalManagementPage.getDuplicateUserGroupName();
		Assert.assertEquals(actualUserGroupName, TestData.DUPLICATE_USERGROUP_NAME);
	}
	
	@Test(priority=8, groups = "Smoke")
	public void deleteCreatedUserGroup() throws InterruptedException{
		hdPortalManagementPage.deleteUserGroup();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		boolean isUGDeleted = hdPortalManagementPage.isUserGroupDeleted();
		//System.out.println("Is UG deleted "+isUGDeleted);
		Assert.assertEquals(isUGDeleted, true);
	}
	
	@Test(priority=9, groups = "Smoke")
	public void deleteDuplicateUserGroup() throws InterruptedException{
		hdPortalManagementPage.deleteDuplicateUserGroup();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		boolean isUGDeleted = hdPortalManagementPage.isDuplicateUserGroupDeleted();
		//System.out.println("Is UG deleted "+isUGDeleted);
		Assert.assertEquals(isUGDeleted, true);
	}

	

}
