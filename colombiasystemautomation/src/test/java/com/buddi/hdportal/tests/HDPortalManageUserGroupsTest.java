/**
 * 
 */
package com.buddi.hdportal.tests;

import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.buddi.colombia.testdata.StringConstants;
import com.buddi.colombia.testdata.TestData;
import com.buddi.colombia.utilities.GetExcelData;
import com.buddi.colombia.utilities.ReadExcelSheet;

/**
 * @author irfan
 *
 */
public class HDPortalManageUserGroupsTest extends HDPortalBaseTest{
	public static XSSFSheet sheet;
	public static Object[][] UserGroupsData;

	@DataProvider(name="getUserGroupsData")
	public static Object[][] getData() throws Exception{
		sheet = ReadExcelSheet.DataSheet(GetExcelData.testDataExcelFileLocation, "UserGroup");
		int rowCount = sheet.getLastRowNum();
		//System.out.println("Row  count:"+rowCount);
		int columnCount = sheet.getRow(0).getLastCellNum();
		//System.out.println("Column   count"+columnCount);
		UserGroupsData = new Object[rowCount][columnCount];
		for (int rowCnt = 1; rowCnt <= rowCount; rowCnt++){
			for (int columnCnt = 0; columnCnt < columnCount; columnCnt++){
				UserGroupsData[rowCnt-1][columnCnt] = GetExcelData.getCellData("UserGroup", rowCnt, columnCnt);
				//System.out.println(UserGroupsData[rowCnt-1][columnCnt]);
			}
		}
		return UserGroupsData;
	}

	@Test(priority = 1, groups = "Smoke")
	public void accessManagementMenu(){
		driver.navigate().refresh();
		hdPortalManageUserGroupsPage.clickManagementMenu();
	}

	@Test(priority = 2, groups = "Smoke",dependsOnMethods={"accessManagementMenu"})
	public void accessUserGroupsMenuItem(){
		hdPortalManageUserGroupsPage.clickUserGroupsOption();
		String actualUserGroupPanelTitle = hdPortalManageUserGroupsPage.verifyUserGroupsPanelTitle();
		Assert.assertEquals(actualUserGroupPanelTitle, StringConstants.USERGROUP_PANEL_TITLE);
	}

	@Test(priority = 3, groups = "Smoke",dependsOnMethods={"accessManagementMenu", "accessUserGroupsMenuItem"},dataProvider="getUserGroupsData")
	public void createNewUserGroup(String userGroupName){		
		hdPortalManageUserGroupsPage.navigateToUserGroupsOption();
		hdPortalManageUserGroupsPage.clickAddUserGroupsButtonOnToolBar();
		hdPortalManageUserGroupsPage.createUserGroup(userGroupName);
		String actualUserGroupName = hdPortalManageUserGroupsPage.getUserGroupName(userGroupName);
		Assert.assertEquals(actualUserGroupName, userGroupName);
	}

	@Test(priority = 4, groups = "Smoke",dependsOnMethods={"accessManagementMenu", "accessUserGroupsMenuItem", "createNewUserGroup"},
			dataProvider="getUserGroupsData")
	public void duplicateUserGroup(String userGroupName){
		hdPortalManageUserGroupsPage.selectUserGroup(userGroupName);
		userGroupName = "DUPLICATE_"+userGroupName;
		hdPortalManageUserGroupsPage.createDuplicateUserGroup(userGroupName);
		String actualUserGroupName = hdPortalManageUserGroupsPage.getDuplicateUserGroupName(userGroupName);
		Assert.assertEquals(actualUserGroupName, userGroupName);
	}

	@Test(priority = 5, groups = "Smoke",dependsOnMethods={"createNewUserGroup", "duplicateUserGroup"},dataProvider="getUserGroupsData")
	public void createDuplicateUserGroupInLowerCase(String userGroupName){
		hdPortalManageUserGroupsPage.clickAddUserGroupsButtonOnToolBar();
		hdPortalManageUserGroupsPage.createUserGroup(userGroupName.toLowerCase());
		hdPortalManageUserGroupsPage.validateExistingUserGroup();
		boolean isDuplicateErrorMessageDisplayed = hdPortalManageUserGroupsPage.isErrorMessageDisplayed();
		Assert.assertEquals(isDuplicateErrorMessageDisplayed, true);
	}


	@Test(priority = 6, groups = "Smoke",dependsOnMethods={"createNewUserGroup", "duplicateUserGroup"},dataProvider="getUserGroupsData")
	public void createDuplicateUserGroupPrefixedWithSpaces(String userGroupName){
		hdPortalManageUserGroupsPage.navigateToUserGroupsOption();
		hdPortalManageUserGroupsPage.clickAddUserGroupsButtonOnToolBar();
		hdPortalManageUserGroupsPage.createUserGroup("            "+userGroupName.toLowerCase());
		hdPortalManageUserGroupsPage.validateExistingUserGroup();
		boolean isDuplicateErrorMessageDisplayed = hdPortalManageUserGroupsPage.isErrorMessageDisplayed();
		Assert.assertEquals(isDuplicateErrorMessageDisplayed, true);
	}


	@Test(priority = 7, groups = "Smoke",dependsOnMethods={"createNewUserGroup", "duplicateUserGroup"},dataProvider="getUserGroupsData")
	public void duplicateAnExistingUserGroupInLowerCase(String userGroupName){
		hdPortalManageUserGroupsPage.navigateToUserGroupsOption();		
		hdPortalManageUserGroupsPage.selectUserGroup(userGroupName);
		userGroupName = "DUPLICATE_"+userGroupName;
		hdPortalManageUserGroupsPage.createDuplicateUserGroup(userGroupName.toLowerCase());
		hdPortalManageUserGroupsPage.validateExistingUserGroup();
		boolean isDuplicateErrorMessageDisplayed = hdPortalManageUserGroupsPage.isErrorMessageDisplayed();
		Assert.assertEquals(isDuplicateErrorMessageDisplayed, true);
	}

	@Test(priority = 8, groups = "Smoke",dependsOnMethods={"createNewUserGroup", "duplicateUserGroup"},dataProvider="getUserGroupsData")
	public void duplicateAnExistingUserGroupPrefixedWithSpaces(String userGroupName){
		hdPortalManageUserGroupsPage.navigateToUserGroupsOption();
		hdPortalManageUserGroupsPage.selectUserGroup(userGroupName);
		userGroupName = "DUPLICATE_"+userGroupName;
		hdPortalManageUserGroupsPage.createDuplicateUserGroup("          "+userGroupName);
		hdPortalManageUserGroupsPage.validateExistingUserGroup();
		boolean isDuplicateErrorMessageDisplayed = hdPortalManageUserGroupsPage.isErrorMessageDisplayed();
		Assert.assertEquals(isDuplicateErrorMessageDisplayed, true);
	}


	@Test(priority = 9, groups = "Smoke",dependsOnMethods={"createNewUserGroup"},dataProvider="getUserGroupsData")
	public void deleteCreatedUserGroup(String userGroupName){
		hdPortalManageUserGroupsPage.selectUserGroup(userGroupName);
		hdPortalManageUserGroupsPage.deleteUserGroup(userGroupName);
		boolean isUGDeleted = hdPortalManageUserGroupsPage.isUserGroupDeleted(userGroupName);
		Assert.assertEquals(isUGDeleted, true);
	}

	@Test(priority = 10, groups = "Smoke",dependsOnMethods={"duplicateUserGroup"},dataProvider="getUserGroupsData")
	public void deleteDuplicateUserGroup(String userGroupName){
		userGroupName = "DUPLICATE_"+userGroupName;
		hdPortalManageUserGroupsPage.selectDuplicateUserGroup(userGroupName);
		hdPortalManageUserGroupsPage.deleteDuplicateUserGroup(userGroupName);
		boolean isUGDeleted = hdPortalManageUserGroupsPage.isDuplicateUserGroupDeleted(userGroupName);
		Assert.assertEquals(isUGDeleted, true);
	}


}
