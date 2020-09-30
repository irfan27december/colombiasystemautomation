/**
 * 
 */
package com.buddi.hdportal.tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.buddi.colombia.common.CommonActions;
import com.buddi.colombia.testdata.StringConstants;
import com.buddi.colombia.testdata.TestData;
import com.buddi.colombia.utilities.XLSXManager;
import com.buddi.colombiaapp.samples.excel.XSSFExcelReader;

/**
 * @author irfan
 *
 */
public class HDPortalLoginTest extends HDPortalBaseTest{
	private static XLSXManager xlsxManager;
	/*public static final String testDataExcelFileName = "testdata.xlsx";	
	private static String fileSeperator = System.getProperty("file.separator");
	private static String testDataExcelFilePath = System.getProperty("user.dir") +fileSeperator+ "testdata";
	private static String testDataExcelFileLocation =  testDataExcelFilePath +fileSeperator+ testDataExcelFileName;*/

	@DataProvider(name="testdata")
	public static Object[][] loginTestData() throws IOException{
		xlsxManager = new XLSXManager(xlsxManager.testDataExcelFileLocation);
		int rowCount = xlsxManager.getRowCount("Login");
		System.out.println("Total rows: "+rowCount);
		int columnCount = xlsxManager.getColumnCount();
		System.out.println("Total columns: "+columnCount);
		Object[][] signin_credentials = new Object[rowCount][columnCount];
		for(int iRows = 1; iRows < rowCount; iRows++){
			if(xlsxManager.getData(0, iRows, 0)!=null)
			signin_credentials[iRows][0] = xlsxManager.getData(0, iRows, 0);
			if(xlsxManager.getData(0, iRows, 1)!=null)
			signin_credentials[iRows][1] = xlsxManager.getData(0, iRows, 1);			
		}
		return signin_credentials;
	}


	//Method to launch HD portal
	@Test(priority = 0, groups = "Smoke")
	public void launchHDPortal() throws InterruptedException{		
		//HDPortalLoginPage hdPortalLoginPage = PageFactory.initElements(driver, HDPortalLoginPage.class);	
		Reporter.log("Launching HD Portal...");
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		String actualTitle = hdPortalLoginPage.verifyPageTitle();
		Assert.assertEquals(actualTitle, StringConstants.HDPORTAL_PAGE_TITLE);
		System.out.println("Launched HD portal...");
		Reporter.log("Launched HD Portal...");				
	}

	//Method to login HD portal  , dataProvider="testdata"
	@Test(priority = 1, groups = "Smoke",dependsOnMethods={"launchHDPortal"})
	public void loginHDPortalWithValidCredentials() throws IOException{	
		String actualTitle = hdPortalLoginPage.verifyPageTitle();
		if(actualTitle.equals(StringConstants.HDPORTAL_PAGE_TITLE)){			
			Assert.assertEquals(actualTitle, StringConstants.HDPORTAL_PAGE_TITLE);
			System.out.println("HD Portal login page is displayed...");		
		}else{ 
			System.out.println("HD Portal login page is not displayed...");
		}		
		//hdPortalLoginPage.loginHDPortal(TestData.HDPORTAL_EMAIL, TestData.HDPORTAL_PASSWORD);	
		xlsxManager = new XLSXManager(xlsxManager.testDataExcelFileLocation);
		for(int iRows = 1; iRows < 2; iRows++){		
			hdPortalLoginPage.loginHDPortal(xlsxManager.getData(0, iRows, 0), xlsxManager.getData(0, iRows, 1));	
		}
	}
}
