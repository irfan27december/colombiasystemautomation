package com.buddi.hdportal.tests;
import java.awt.AWTException;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;

import com.buddi.colombia.common.CommonActions;
import com.buddi.colombia.testdata.TestData;

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
	// @click and expand the Visits usecase 
	@Test(priority = 1, groups = "Smoke")
	public void accessVisitsMenu() throws AWTException{
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
		//click 'Add' Button from pending Visits
		hdPortalManageAddNewVisitPage.clickAddNewVisitToolBarButton();
		// Verify the Add new visit dialog
		hdPortalManageAddNewVisitPage.isAddNewVisitDialogTitleDisplayed();
	}
	//@Fill all required fields in Add New Visit dialog 
	//@Create add new visit  
	@Test(priority = 3, groups = "Smoke", dependsOnMethods = { "accessAddButton" }) 
	public void createAddNewVisit() throws AWTException{
		// @Create the add new visit
		hdPortalManageAddNewVisitPage.createAddNewVisit(TestData.SELECT_JOBTYPE, TestData.SELECT_WEARERGROUP,TestData.ADD_VISIT_WEARER_NUI,
				TestData.ADD_NEW_VISIT_WEARER, TestData.SELECT_FIELD_OFFICER,TestData.VISIT_START_DATE_FORMAT, TestData.VISIT_START_HOURS, 
				TestData.VISIT_START_MINUTES,TestData.VISIT_END_DATE_FORMAT, TestData.VISIT_END_HOURS, TestData.VISIT_EMD_MINUTES,
				TestData.ADD_VISIT_NOTES);
	}

	/* @Verify created add new visit listed in scheduled page    
	@Test(priority = 4, groups = "Smoke", dependsOnMethods = { "createAddNewVisit"}) 
	public void verifyAddNewVisitInScheduledPage() throws AWTException {
		driver.manage().timeouts().pageLoadTimeout(80, TimeUnit.SECONDS);
		hdPortalManageAddNewVisitPage.isScheduledVisitGridTitleDisplayed();
		driver.navigate().refresh();
		hdPortalManageAddNewVisitPage.clickVisitsMenu();
		// Navigating the scheduled visit
		hdPortalManageAddNewVisitPage.clickSheduledVisitsMenu();
		// Verifying the created Add New visit with wearer NUI in Scheduled list 
		hdPortalManageAddNewVisitPage.isWearerNUIDisplayedInScheduledListPage(TestData.ADD_VISIT_WEARER_NUI);
	}

	 */
	// @Verify created add new visit   
	@Test(priority = 4, groups = "Smoke", dependsOnMethods = { "createAddNewVisit" }) 
	public void verifyAddNewVisit() throws AWTException {
		//Verify scheduled grid title
		hdPortalManageAddNewVisitPage.isScheduledVisitGridTitleDisplayed();

	}

	// @Fill all required fields and cancelled visit usecase
	@Test(priority = 5, groups = "Smoke", dependsOnMethods = { "verifyAddNewVisit" }) 
	public void vcancelledAddNewVisit() throws AWTException {
		//click action dialog
		hdPortalManageAddNewVisitPage.clickActionDialogButton();
		//verify the action dialog title
		hdPortalManageAddNewVisitPage.isActionDialogTitleDisplayed();
		//fill all fields and Cancelled the Visit
		hdPortalManageAddNewVisitPage.selectCancelledVisit(TestData.SELECT_OUTCOME, TestData.SELECT_REASON, TestData.ENTER_NOTES);
	}




}


