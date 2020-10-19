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
 * @Fill all required fields to complete visit 
 */


public class HDPortalManageCompleteVisitsTest extends HDPortalBaseTest{

	public static XSSFSheet sheet;
	public static Object[][] completedVisits;

	@DataProvider(name="getVisitsData")
	public static Object[][] getData() throws Exception{
		sheet = ReadExcelSheet.DataSheet(GetExcelData.testDataExcelFileLocation, "Visits_CompletedVisits");
		int rowCount = sheet.getLastRowNum();
		int columnCount = sheet.getRow(0).getLastCellNum();
		completedVisits = new Object[rowCount][columnCount];
		for (int rowCnt = 1; rowCnt <= rowCount; rowCnt++){
			for (int columnCnt = 0; columnCnt < columnCount; columnCnt++){
				completedVisits[rowCnt-1][columnCnt] = GetExcelData.getCellData("Visits_CompletedVisits", rowCnt, columnCnt);
			}
		}
		return completedVisits;
	}

	// @Complete visit usecase
	@Test(priority = 1, groups = "Smoke", dataProvider = "getVisitsData")
	public void completedVisit(String selectCompletedOutCome, String selectCompletedReason, String visitStartDateFormat,
			String visitStartHours, String visitStartMinutes, String visitEndDateFormat, String visitEndHours, String visitEndMinutes, String enterCompletedNotes) throws AWTException{
		Log.startTestCase("completedVisit");
		driver.navigate().refresh();
		// click on visits menu
		hdPortalManageCompleteVisitsPage.clickVisitsMenu();
		//click scheduled visit
		hdPortalManageCompleteVisitsPage.clickSheduledVisitsMenu();
		//open scheduled visit
		hdPortalManageCompleteVisitsPage.clickOpenVisitInScheduledPage(TestData.ADD_VISIT_WEARER_NUI);
		//click action dialog
		hdPortalManageCompleteVisitsPage.clickActionDialogButton();
		//verify the action dialog title
		hdPortalManageCompleteVisitsPage.isActionDialogTitleDisplayed();
		//fill all fields and Cancelled the Visit
		/*hdPortalManageCompletedVisitsPage.selectCompletedVisit(TestData.SELECT_COMPLETED_OUTCOME,TestData.SELECT_COMPLETED_REASON, TestData.VISIT_START_DATE_FORMAT, 
				TestData.VISIT_START_HOURS, TestData.VISIT_START_MINUTES,TestData.VISIT_END_DATE_FORMAT, TestData.VISIT_END_HOURS, TestData.VISIT_EMD_MINUTES,
				TestData.ENTER_COMPLETED_NOTES);*/
		hdPortalManageCompleteVisitsPage.selectCompletedVisit(selectCompletedOutCome, selectCompletedReason, visitStartDateFormat,
				visitStartHours, visitStartMinutes, visitEndDateFormat, visitEndHours, visitEndMinutes, enterCompletedNotes);
		hdPortalManageCompleteVisitsPage.closePanel();
		Log.info("Visit Completed successfully..!");
		Log.endTestCase("completedVisit");
	}

}
