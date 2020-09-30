/**
 * 
 */
package com.buddi.hdportal.tests;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.buddi.colombia.common.CommonActions;
import com.buddi.colombia.testdata.StringConstants;
import com.buddi.colombia.testdata.TestData;

/**
 * @author irfan
 *
 */
public class HDPortalManageUserGroupsTest extends HDPortalBaseTest{

	@Test(priority = 1, groups = "Smoke")
	public void accessManagementMenu() throws InterruptedException{
		driver.navigate().refresh();
		hdPortalManageUserGroupsPage.clickManagementMenu();
	}

	@Test(priority = 2, groups = "Smoke",dependsOnMethods={"accessManagementMenu"})
	public void accessUserGroupsMenuItem(){
		hdPortalManageUserGroupsPage.clickUserGroupsOption();
		String actualUserGroupPanelTitle = hdPortalManageUserGroupsPage.verifyUserGroupsPanelTitle();
		Assert.assertEquals(actualUserGroupPanelTitle, StringConstants.USERGROUP_PANEL_TITLE);
	}

	@Test(priority = 3, groups = "Smoke",dependsOnMethods={"accessManagementMenu", "accessUserGroupsMenuItem"})
	public void createNewUserGroup(){		
		hdPortalManageUserGroupsPage.navigateToUserGroupsOption();
		//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		hdPortalManageUserGroupsPage.clickAddUserGroupsButtonOnToolBar();
		hdPortalManageUserGroupsPage.createUserGroup(TestData.USERGROUP_NAME);
		String actualUserGroupName = hdPortalManageUserGroupsPage.getUserGroupName(TestData.USERGROUP_NAME);
		Assert.assertEquals(actualUserGroupName, TestData.USERGROUP_NAME);
	}

	@Test(priority = 4, groups = "Smoke",dependsOnMethods={"accessManagementMenu", "accessUserGroupsMenuItem", "createNewUserGroup"})
	public void duplicateUserGroup() throws InterruptedException{
		hdPortalManageUserGroupsPage.selectUserGroup(TestData.USERGROUP_NAME);
		hdPortalManageUserGroupsPage.createDuplicateUserGroup(TestData.DUPLICATE_USERGROUP_NAME);
		String actualUserGroupName = hdPortalManageUserGroupsPage.getDuplicateUserGroupName(TestData.DUPLICATE_USERGROUP_NAME);
		Assert.assertEquals(actualUserGroupName, TestData.DUPLICATE_USERGROUP_NAME);
	}

	@Test(priority = 5, groups = "Smoke",dependsOnMethods={"createNewUserGroup", "duplicateUserGroup"})
	public void createDuplicateUserGroupInLowerCase(){
		hdPortalManageUserGroupsPage.clickAddUserGroupsButtonOnToolBar();
		hdPortalManageUserGroupsPage.createUserGroup(TestData.USERGROUP_NAME.toLowerCase());
		hdPortalManageUserGroupsPage.validateExistingUserGroup();
		//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		boolean isDuplicateErrorMessageDisplayed = hdPortalManageUserGroupsPage.isErrorMessageDisplayed();
		Assert.assertEquals(isDuplicateErrorMessageDisplayed, true);
	}
	
	
	@Test(priority = 6, groups = "Smoke",dependsOnMethods={"createNewUserGroup", "duplicateUserGroup"})
	public void createDuplicateUserGroupPrefixedWithSpaces(){
		hdPortalManageUserGroupsPage.navigateToUserGroupsOption();
		//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		hdPortalManageUserGroupsPage.clickAddUserGroupsButtonOnToolBar();
		hdPortalManageUserGroupsPage.createUserGroup("            "+TestData.USERGROUP_NAME.toLowerCase());
		hdPortalManageUserGroupsPage.validateExistingUserGroup();
		//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		boolean isDuplicateErrorMessageDisplayed = hdPortalManageUserGroupsPage.isErrorMessageDisplayed();
		Assert.assertEquals(isDuplicateErrorMessageDisplayed, true);
	}
	
	
	@Test(priority = 7, groups = "Smoke",dependsOnMethods={"createNewUserGroup", "duplicateUserGroup"})
	public void duplicateAnExistingUserGroupInLowerCase() throws InterruptedException{
		hdPortalManageUserGroupsPage.navigateToUserGroupsOption();
		hdPortalManageUserGroupsPage.selectUserGroup(TestData.USERGROUP_NAME);
		hdPortalManageUserGroupsPage.createDuplicateUserGroup(TestData.DUPLICATE_USERGROUP_NAME.toLowerCase());
		hdPortalManageUserGroupsPage.validateExistingUserGroup();
		//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		boolean isDuplicateErrorMessageDisplayed = hdPortalManageUserGroupsPage.isErrorMessageDisplayed();
		Assert.assertEquals(isDuplicateErrorMessageDisplayed, true);
	}
	
	@Test(priority = 8, groups = "Smoke",dependsOnMethods={"createNewUserGroup", "duplicateUserGroup"})
	public void duplicateAnExistingUserGroupPrefixedWithSpaces() throws InterruptedException{
		hdPortalManageUserGroupsPage.navigateToUserGroupsOption();
		hdPortalManageUserGroupsPage.selectUserGroup(TestData.USERGROUP_NAME);
		hdPortalManageUserGroupsPage.createDuplicateUserGroup("          "+TestData.DUPLICATE_USERGROUP_NAME);
		hdPortalManageUserGroupsPage.validateExistingUserGroup();
		//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		boolean isDuplicateErrorMessageDisplayed = hdPortalManageUserGroupsPage.isErrorMessageDisplayed();
		Assert.assertEquals(isDuplicateErrorMessageDisplayed, true);
	}
	

	@Test(priority = 9, groups = "Smoke",dependsOnMethods={"createNewUserGroup"})
	public void deleteCreatedUserGroup() throws InterruptedException{
		hdPortalManageUserGroupsPage.selectUserGroup(TestData.USERGROUP_NAME);
		//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		hdPortalManageUserGroupsPage.deleteUserGroup(TestData.USERGROUP_NAME);
		//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		boolean isUGDeleted = hdPortalManageUserGroupsPage.isUserGroupDeleted(TestData.USERGROUP_NAME);
		Assert.assertEquals(isUGDeleted, true);
	}

	@Test(priority = 10, groups = "Smoke",dependsOnMethods={"duplicateUserGroup"})
	public void deleteDuplicateUserGroup() throws InterruptedException{
		//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);		
		hdPortalManageUserGroupsPage.selectDuplicateUserGroup(TestData.DUPLICATE_USERGROUP_NAME);
		hdPortalManageUserGroupsPage.deleteDuplicateUserGroup(TestData.DUPLICATE_USERGROUP_NAME);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		boolean isUGDeleted = hdPortalManageUserGroupsPage.isDuplicateUserGroupDeleted(TestData.DUPLICATE_USERGROUP_NAME);
		Assert.assertEquals(isUGDeleted, true);
	}


}
