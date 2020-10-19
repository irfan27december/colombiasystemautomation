package com.buddi.hdportal.tests;

import java.awt.AWTException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.buddi.colombia.testdata.TestData;
import com.buddi.colombia.utilities.GetExcelData;
import com.buddi.colombia.utilities.Log;
import com.buddi.colombia.utilities.ReadExcelSheet;

/**
 * @author sumaiah

 **Use Cases **
 * @Login HD Portal
 * @Access Visit menu
 * @Access Scheduled Visit and verify 
 * @Open wearer visit click Action dialog and verify title
 * @Fill all required fields to cancelled visit 
 */

public class HDPortalManageCancelVisitsTest extends HDPortalBaseTest{

	public static XSSFSheet sheet;
	public static Object[][] cancelledVisitsData;

	@DataProvider(name="getCancelledVisitsData")
	public static Object[][] getData() throws Exception{
		sheet = ReadExcelSheet.DataSheet(GetExcelData.testDataExcelFileLocation, "Visits_CancelledVisits");
		int rowCount = sheet.getLastRowNum();
		int columnCount = sheet.getRow(0).getLastCellNum();
		cancelledVisitsData = new Object[rowCount][columnCount];
		for (int rowCnt = 1; rowCnt <= rowCount; rowCnt++){
			for (int columnCnt = 0; columnCnt < columnCount; columnCnt++){
				cancelledVisitsData[rowCnt-1][columnCnt] = GetExcelData.getCellData("Visits_CancelledVisits", rowCnt, columnCnt);
			}
		}
		return cancelledVisitsData;
	}

	// @Cancelled visit usecase 
	@Test(priority = 1, groups = "Smoke", dataProvider = "getCancelledVisitsData")
	public void cancelledVisit(String selectCancelledOutCome, String selectCancelledReason, String enterCancelledNotes) throws AWTException{
		Log.startTestCase("cancelledVisit");
		driver.navigate().refresh();
		// click on visits menu
		hdPortalManageCancelVisitsPage.clickVisitsMenu();
		//click scheduled visit
		hdPortalManageCancelVisitsPage.clickSheduledVisitsMenu();
		//open scheduled visit
		hdPortalManageCancelVisitsPage.clickOpenVisitInScheduledPage(TestData.ADD_VISIT_WEARER_NUI);
		//click action dialog
		hdPortalManageCancelVisitsPage.clickActionDialogButton();
		//verify the action dialog title
		hdPortalManageCancelVisitsPage.isActionDialogTitleDisplayed();
		//fill all fields( pick data from Testdata file )and  Cancelled the Visit
		//hdPortalManageCancelledVisitsPage.selectCancelledVisit(TestData.SELECT_CANCELLED_OUTCOME, TestData.SELECT_CANCELLED_REASON, TestData.ENTER_CANCELLED_NOTES);

		// Cancelled visit(Picked data from excel)
		hdPortalManageCancelVisitsPage.selectCancelledVisit(selectCancelledOutCome, selectCancelledReason, enterCancelledNotes);
		hdPortalManageCancelVisitsPage.closePanel();
		Log.info("Visit Cancelled successfully...");	
		Log.endTestCase("cancelledVisit");
	}
}
