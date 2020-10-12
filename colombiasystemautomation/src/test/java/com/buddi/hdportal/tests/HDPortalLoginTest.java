/**
 * 
 */
package com.buddi.hdportal.tests;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.buddi.colombia.testdata.StringConstants;
import com.buddi.colombia.utilities.GetExcelData;
import com.buddi.colombia.utilities.Log;
import com.buddi.colombia.utilities.ReadExcelSheet;

/**
 * @author irfan
 *
 */
public class HDPortalLoginTest extends HDPortalBaseTest{
	public static XSSFSheet sheet;
	public static Object[][] LoginData; 

	@DataProvider(name="getLoginData")
	public static Object[][] getData() throws Exception{
		sheet = ReadExcelSheet.DataSheet(GetExcelData.testDataExcelFileLocation, "Login");
		int rowCount = sheet.getLastRowNum();
		int columnCount = sheet.getRow(0).getLastCellNum();
		LoginData = new Object[rowCount][columnCount];
		for (int rowCnt = 1; rowCnt <= rowCount; rowCnt++){
			for (int columnCnt = 0; columnCnt < columnCount; columnCnt++){
				LoginData[rowCount-1][columnCnt] = GetExcelData.getCellData("Login", rowCnt, columnCnt);
				//System.out.println(LoginData[rowCnt-1][columnCnt]);
			}
		}
		return LoginData;
	}


	//Method to launch HD portal
	@Test(priority = 0, groups = "Smoke")
	public void launchHDPortal(){		
		//HDPortalLoginPage hdPortalLoginPage = PageFactory.initElements(driver, HDPortalLoginPage.class);	
		Log.startTestCase("launchHDPortal");
		String actualTitle = hdPortalLoginPage.verifyPageTitle();
		Assert.assertEquals(actualTitle, StringConstants.HDPORTAL_PAGE_TITLE);
		Log.info("Launched HD portal...");	
		Log.endTestCase("launchHDPortal");
	}

	//Method to login HD portal
	@Test(priority = 1, groups = "Smoke",dependsOnMethods={"launchHDPortal"},dataProvider="getLoginData")
	public void loginHDPortalWithValidCredentials(String email, String password){	
		String actualTitle = hdPortalLoginPage.verifyPageTitle();
		if(actualTitle.equals(StringConstants.HDPORTAL_PAGE_TITLE)){			
			Assert.assertEquals(actualTitle, StringConstants.HDPORTAL_PAGE_TITLE);
			Log.info("HD Portal login page is displayed...");
		}else{ 
			Log.error("HD Portal login page is displayed...");
		}	
		hdPortalLoginPage.loginHDPortal(email, password);	
		Log.endTestCase("loginHDPortalWithValidCredentials");
	}
}
