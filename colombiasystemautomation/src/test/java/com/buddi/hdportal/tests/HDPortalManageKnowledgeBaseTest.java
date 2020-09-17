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
public class HDPortalManageKnowledgeBaseTest extends HDPortalBaseTest{

	@Test(priority = 1, groups = "Smoke")
	public void accessManagementMenu() throws InterruptedException{
		hdPortalManageKnowledgeBasePage.clickManagementMenu();
	}

	@Test(priority = 2, groups = "Smoke")
	public void accessKnowledgeBaseMenuItem(){
		hdPortalManageKnowledgeBasePage.clickKnowledgeBaseOption();
		String actualKnowledgeBasePanelTitle = hdPortalManageKnowledgeBasePage.verifyKnowledgeBasePanelTitle();
		Assert.assertEquals(actualKnowledgeBasePanelTitle, StringConstants.KNOWLEDGEBASE_PANEL_TITLE);
	}

	@Test(priority = 3, groups = "Smoke")
	public void createNewKnowledgeBase(){
		//hdPortalManageKnowledgeBasePage.navigateToKnowledgeBaseOption();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		hdPortalManageKnowledgeBasePage.clickKnowledgeBaseButtonOnToolBar();
		hdPortalManageKnowledgeBasePage.createKnowledgeBase(TestData.KNOWLEDGEBASE_NAME,TestData.KNOWLEDGEBASE_SUBJECT,TestData.KNOWLEDGEBASE_DESCRIPTION);
		String actualKnowledgeBaseName = hdPortalManageKnowledgeBasePage.getKnowledgeBaseName(TestData.KNOWLEDGEBASE_NAME);
		Assert.assertEquals(actualKnowledgeBaseName, TestData.KNOWLEDGEBASE_NAME);
	}
	
/*
	@Test(priority = 4, groups = "Smoke")
	public void duplicateUserGroup() throws InterruptedException{
		hdPortalManageUserGroupsPage.selectUserGroup(TestData.USERGROUP_NAME);
		hdPortalManageUserGroupsPage.createDuplicateUserGroup(TestData.DUPLICATE_USERGROUP_NAME);
		String actualUserGroupName = hdPortalManageUserGroupsPage.getDuplicateUserGroupName(TestData.DUPLICATE_USERGROUP_NAME);
		Assert.assertEquals(actualUserGroupName, TestData.DUPLICATE_USERGROUP_NAME);
	}

	@Test(priority = 5, groups = "Smoke")
	public void createDuplicateUserGroupInLowerCase(){
		hdPortalManageUserGroupsPage.clickAddUserGroupsButtonOnToolBar();
		hdPortalManageUserGroupsPage.createUserGroup(TestData.USERGROUP_NAME.toLowerCase());
		hdPortalManageUserGroupsPage.validateExistingUserGroup();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		boolean isDuplicateErrorMessageDisplayed = hdPortalManageUserGroupsPage.isErrorMessageDisplayed();
		Assert.assertEquals(isDuplicateErrorMessageDisplayed, true);
	}
	
	
	@Test(priority = 6, groups = "Smoke")
	public void createDuplicateUserGroupPrefixedWithSpaces(){
		hdPortalManageUserGroupsPage.navigateToUserGroupsOption();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		hdPortalManageUserGroupsPage.clickAddUserGroupsButtonOnToolBar();
		hdPortalManageUserGroupsPage.createUserGroup("            "+TestData.USERGROUP_NAME.toLowerCase());
		hdPortalManageUserGroupsPage.validateExistingUserGroup();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		boolean isDuplicateErrorMessageDisplayed = hdPortalManageUserGroupsPage.isErrorMessageDisplayed();
		Assert.assertEquals(isDuplicateErrorMessageDisplayed, true);
	}
	
	
	@Test(priority = 7, groups = "Smoke")
	public void duplicateAnExistingUserGroupInLowerCase() throws InterruptedException{
		hdPortalManageUserGroupsPage.navigateToUserGroupsOption();
		hdPortalManageUserGroupsPage.selectUserGroup(TestData.USERGROUP_NAME);
		hdPortalManageUserGroupsPage.createDuplicateUserGroup(TestData.DUPLICATE_USERGROUP_NAME.toLowerCase());
		hdPortalManageUserGroupsPage.validateExistingUserGroup();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		boolean isDuplicateErrorMessageDisplayed = hdPortalManageUserGroupsPage.isErrorMessageDisplayed();
		Assert.assertEquals(isDuplicateErrorMessageDisplayed, true);
	}
	
	@Test(priority = 8, groups = "Smoke")
	public void duplicateAnExistingUserGroupPrefixedWithSpaces() throws InterruptedException{
		hdPortalManageUserGroupsPage.navigateToUserGroupsOption();
		hdPortalManageUserGroupsPage.selectUserGroup(TestData.USERGROUP_NAME);
		hdPortalManageUserGroupsPage.createDuplicateUserGroup("          "+TestData.DUPLICATE_USERGROUP_NAME);
		hdPortalManageUserGroupsPage.validateExistingUserGroup();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		boolean isDuplicateErrorMessageDisplayed = hdPortalManageUserGroupsPage.isErrorMessageDisplayed();
		Assert.assertEquals(isDuplicateErrorMessageDisplayed, true);
	}
	

	@Test(priority = 9, groups = "Smoke")
	public void deleteCreatedUserGroup() throws InterruptedException{
		hdPortalManageUserGroupsPage.selectUserGroup(TestData.USERGROUP_NAME);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		hdPortalManageUserGroupsPage.deleteUserGroup(TestData.USERGROUP_NAME);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		boolean isUGDeleted = hdPortalManageUserGroupsPage.isUserGroupDeleted(TestData.USERGROUP_NAME);
		Assert.assertEquals(isUGDeleted, true);
	}

	@Test(priority = 10, groups = "Smoke")
	public void deleteDuplicateUserGroup() throws InterruptedException{
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);		
		hdPortalManageUserGroupsPage.selectDuplicateUserGroup(TestData.DUPLICATE_USERGROUP_NAME);
		hdPortalManageUserGroupsPage.deleteDuplicateUserGroup(TestData.DUPLICATE_USERGROUP_NAME);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		boolean isUGDeleted = hdPortalManageUserGroupsPage.isDuplicateUserGroupDeleted(TestData.DUPLICATE_USERGROUP_NAME);
		Assert.assertEquals(isUGDeleted, true);
	}*/


}
