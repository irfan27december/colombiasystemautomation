/**
 * 
 */
package com.buddi.hdportal.tests;

import java.util.Random;
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
public class HDPortalManageKnowledgeBaseTest extends HDPortalBaseTest{
	public static XSSFSheet sheet;
	public static Object[][] KnowledgeBaseData;
	// create instance of Random class 
	static Random random = new Random(); 
	// Generate random integers in range 0 to 999 
	static int randomInt = random.nextInt(1000);

	@DataProvider(name="getKnowledgeBaseData")
	public static Object[][] getData() throws Exception{
		sheet = ReadExcelSheet.DataSheet(GetExcelData.testDataExcelFileLocation, "KnowledgeBase");
		int rowCount = sheet.getLastRowNum();
		//System.out.println("Row  count:"+rowCount);
		int columnCount = sheet.getRow(0).getLastCellNum();
		//System.out.println("Column   count"+columnCount);
		KnowledgeBaseData = new Object[rowCount][columnCount];
		for (int rowCnt = 1; rowCnt <= rowCount; rowCnt++){
			for (int columnCnt = 0; columnCnt < columnCount; columnCnt++){
				KnowledgeBaseData[rowCnt-1][columnCnt] = GetExcelData.getCellData("KnowledgeBase", rowCnt, columnCnt);
				//System.out.println(KnowledgeBaseData[rowCnt-1][columnCnt]);
			}
		}
		return KnowledgeBaseData;
	}
	@Test(priority = 1, groups = "Smoke")
	public void accessManagementMenuForKnowledgeBase() throws InterruptedException{
		//hdPortalLoginPage.loginHDPortal(TestData.HDPORTAL_EMAIL, TestData.HDPORTAL_PASSWORD);	
		driver.navigate().refresh();
		hdPortalManageKnowledgeBasePage.clickManagementMenu();
	}

	@Test(priority = 2, groups = "Smoke",dependsOnMethods = {"accessManagementMenuForKnowledgeBase"})
	public void accessKnowledgeBaseMenuItem(){
		hdPortalManageKnowledgeBasePage.clickKnowledgeBaseOption();
		String actualKnowledgeBasePanelTitle = hdPortalManageKnowledgeBasePage.verifyKnowledgeBasePanelTitle();
		Assert.assertEquals(actualKnowledgeBasePanelTitle, StringConstants.KNOWLEDGEBASE_PANEL_TITLE);
	}

	@Test(priority = 3, groups = "Smoke",dependsOnMethods = {"accessKnowledgeBaseMenuItem"},dataProvider = "getKnowledgeBaseData")
	public void createNewKnowledgeBase(String knowledgeBaseItemName, String subject, String description){
		//hdPortalManageKnowledgeBasePage.navigateToKnowledgeBaseOption();
		hdPortalManageKnowledgeBasePage.clickKnowledgeBaseButtonOnToolBar();
		knowledgeBaseItemName = knowledgeBaseItemName + randomInt;
		hdPortalManageKnowledgeBasePage.createKnowledgeBase(knowledgeBaseItemName,subject,description);
		String actualKnowledgeBaseItemName = hdPortalManageKnowledgeBasePage.getKnowledgeBaseItemName(knowledgeBaseItemName);
		Assert.assertEquals(actualKnowledgeBaseItemName, knowledgeBaseItemName);
		hdPortalManageKnowledgeBasePage.navigateToKnowledgeBaseOption();
		hdPortalManageKnowledgeBasePage.searchKnowledgeBaseByItemName(knowledgeBaseItemName);
		hdPortalManageKnowledgeBasePage.selectKnowledgebase(knowledgeBaseItemName);
		driver.navigate().refresh();
	}

	/*@Test(priority = 4, groups = "Smoke",dependsOnMethods = {"createNewKnowledgeBase"})
	public void verifyCreatedKnowledgeBase(String knowledgeBaseItemName){
		knowledgeBaseItemName = GetExcelData.getCellData("KnowledgeBase", 1, 0)+randomInt;
		hdPortalManageKnowledgeBasePage.navigateToKnowledgeBaseOption();
		hdPortalManageKnowledgeBasePage.searchKnowledgeBaseByItemName(knowledgeBaseItemName);
		hdPortalManageKnowledgeBasePage.selectKnowledgebase(knowledgeBaseItemName);
		driver.navigate().refresh();
	}*/
}
