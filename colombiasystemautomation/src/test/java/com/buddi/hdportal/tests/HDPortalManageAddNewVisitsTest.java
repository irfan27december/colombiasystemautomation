package com.buddi.hdportal.tests;
import java.awt.AWTException;
import java.util.concurrent.TimeUnit;
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
 * @Access Visit menu and verify
 * @Access Add Button for Add new visit dialog and verify Add new visit dialog
 * @Verify 'Add New Visit' dialog window title
 * @Fill all required fields in Add New Visit dialog and Create add new visit for wearer 
 * @Verify Add New Visit creation
 * @Verify created add new visit listed in scheduled page    
 *
 */

public class HDPortalManageAddNewVisitsTest extends HDPortalBaseTest{

	public static XSSFSheet sheet;
	public static Object[][] addNewVisitsData;

	@DataProvider(name="getVisitsData")
	public static Object[][] getData() throws Exception{
		sheet = ReadExcelSheet.DataSheet(GetExcelData.testDataExcelFileLocation, "Visits_AddNewVisit");
		int rowCount = sheet.getLastRowNum();
		int columnCount = sheet.getRow(0).getLastCellNum();
		addNewVisitsData = new Object[rowCount][columnCount];
		for (int rowCnt = 1; rowCnt <= rowCount; rowCnt++){
			for (int columnCnt = 0; columnCnt < columnCount; columnCnt++){
				addNewVisitsData[rowCnt-1][columnCnt] = GetExcelData.getCellData("Visits_AddNewVisit", rowCnt, columnCnt);
			}
		}
		return addNewVisitsData;
	}

	// @click and expand the Visits usecase 
	@Test(priority = 1, groups = "Smoke")
	public void accessVisitsMenu() throws AWTException{
		Log.startTestCase("accessVisitsMenu");
		// click on visits menu
		hdPortalManageAddNewVisitPage.clickVisitsMenu();
		// Expanding the visits Menu
		hdPortalManageAddNewVisitPage.clickPendingOption();
		// Verify the Pending visit title
		hdPortalManageAddNewVisitPage.isPendingVisitsGridTitleDisplayed();
	}
	//@Click Add button and verify the Add new Visit dialog window usecase
	@Test(priority = 2, groups = "Smoke", dependsOnMethods = { "accessVisitsMenu" })
	public void accessAddButton() throws InterruptedException, AWTException{
		Log.startTestCase("accessAddButton");
		//click 'Add' Button from pending Visits
		hdPortalManageAddNewVisitPage.clickAddNewVisitToolBarButton();
		// Verify the Add new visit dialog
		hdPortalManageAddNewVisitPage.isAddNewVisitDialogTitleDisplayed();
	}
	/*//@Fill all required fields in Add New Visit dialog 
	//@Create add new visit  
	@Test(priority = 3, groups = "Smoke", dependsOnMethods = { "accessAddButton" }) 
	public void createAddNewVisit() throws AWTException{
		Log.startTestCase("accessVisitsMenu");
		// @Create the add new visit
		hdPortalManageAddNewVisitPage.createAddNewVisit(TestData.SELECT_JOBTYPE, TestData.SELECT_WEARERGROUP,TestData.ADD_VISIT_WEARER_NUI,
				TestData.ADD_NEW_VISIT_WEARER, TestData.SELECT_FIELD_OFFICER,TestData.VISIT_START_DATE_FORMAT, TestData.VISIT_START_HOURS, 
				TestData.VISIT_START_MINUTES,TestData.VISIT_END_DATE_FORMAT, TestData.VISIT_END_HOURS, TestData.VISIT_EMD_MINUTES,
				TestData.ADD_VISIT_NOTES);
		hdPortalManageAddNewVisitPage.isScheduledVisitGridTitleDisplayed();
		hdPortalManageAddNewVisitPage.closePanel();
	}*/

	//@Fill all required fields in Add New Visit dialog 
	//@Create add new visit  
	@Test(priority = 3, groups = "Smoke", dependsOnMethods = { "accessAddButton" },dataProvider = "getVisitsData")
	public void createAddNewVisit(String selectJobType, String selectWearerGroup, String addNewVisitWearerNUI, String addNewVisitWearer,
			String assignedFieldOfficer, String visitStartDateFormat, String visitStartHours, String visitStartMinutes, 
			String visitEndDateFormat, String visitEndHours, String visitEndMinutes, String addVisitNotes) throws AWTException{
		Log.startTestCase("createAddNewVisit");
		hdPortalManageAddNewVisitPage.createAddNewVisit(selectJobType, selectWearerGroup, addNewVisitWearerNUI, addNewVisitWearer,
				assignedFieldOfficer, visitStartDateFormat, visitStartHours, visitStartMinutes, visitEndDateFormat, visitEndHours, 
				visitEndMinutes, addVisitNotes);
		hdPortalManageAddNewVisitPage.isScheduledVisitGridTitleDisplayed();
		hdPortalManageAddNewVisitPage.closePanel();
		Log.info(" Visit created successfully..!");
	}

	//@Verify created add new visit wearer details in scheduled page    
	@Test(priority = 4, groups = "Smoke", dependsOnMethods = { "createAddNewVisit"}) 
	public void verifyAddNewVisitInScheduledPage() throws AWTException {
		Log.startTestCase("verifyAddNewVisitInScheduledPage");
		driver.navigate().refresh();
		driver.manage().timeouts().pageLoadTimeout(80, TimeUnit.SECONDS);
		hdPortalManageAddNewVisitPage.clickVisitsMenu();
		// Navigating the scheduled visit
		hdPortalManageAddNewVisitPage.clickSheduledVisitsMenu();
		// Verifying the created Add New visit with wearer NUI in Scheduled list 
		hdPortalManageAddNewVisitPage.clickOpenVisitInScheduledPage(TestData.ADD_VISIT_WEARER_NUI);
		//Open scheduled visit details and verify the wearer
		hdPortalManageAddNewVisitPage.isScheduledVisitGridTitleDisplayed();
		Log.info("Created Add New Visit is verified successfully...");	
		Log.endTestCase("verifyAddNewVisitInScheduledPage");
	}
}


