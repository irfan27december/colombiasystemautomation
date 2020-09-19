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
	public void accessManagementMenuForKB() throws InterruptedException{
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
		hdPortalManageKnowledgeBasePage.createKnowledgeBase(TestData.KNOWLEDGEBASE_ITEMNAME,TestData.KNOWLEDGEBASE_SUBJECT,TestData.KNOWLEDGEBASE_DESCRIPTION);
		String actualKnowledgeBaseItemName = hdPortalManageKnowledgeBasePage.getKnowledgeBaseItemName(TestData.KNOWLEDGEBASE_ITEMNAME);
		Assert.assertEquals(actualKnowledgeBaseItemName, TestData.KNOWLEDGEBASE_ITEMNAME);
		/*String actualKnowledgeBaseSubject = hdPortalManageKnowledgeBasePage.returnKnowledgeBaseSubject(TestData.KNOWLEDGEBASE_SUBJECT);
		Assert.assertEquals(actualKnowledgeBaseSubject, TestData.KNOWLEDGEBASE_SUBJECT);
		String actualKnowledgeBaseDescription = hdPortalManageKnowledgeBasePage.returnKnowledgeBaseDescription(TestData.KNOWLEDGEBASE_DESCRIPTION);
		Assert.assertEquals(actualKnowledgeBaseDescription, TestData.KNOWLEDGEBASE_DESCRIPTION);*/
		//hdPortalManageKnowledgeBasePage.navigateToKnowledgeBaseOption();
		hdPortalManageKnowledgeBasePage.searchKnowledgeBaseByItemName(TestData.KNOWLEDGEBASE_ITEMNAME);
		hdPortalManageKnowledgeBasePage.selectKnowledgebase(TestData.KNOWLEDGEBASE_ITEMNAME);
	}


}
