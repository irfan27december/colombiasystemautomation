package com.buddi.hdportal.tests;


import java.awt.AWTException;

import org.testng.annotations.Test;
import com.buddi.colombia.testdata.TestData;

/**
 * @author sumaiah

 **Use Cases **
 * @Login HD Portal
 * @Access Visit menu and verify
 * @Access Add Button for Add new visit dailouge and verify Add new visit dailouge
 * @Verify 'Add New Visit' dailouge window title
 * @Fill all required fields in Add New Visit dailouge and Create add new visit for wearer 
 * @Verify Add New Visit creation
 * @Verify created add new visit listed in scheduled page    
 *
 */
//Irfan correct the spelling of dailouge, it should be dialog. Change it in the entire classes

public class HDPortalManageAddNewVisitsTest extends HDPortalBaseTest{
	// @click and expand the Visits usecase 
	@Test(priority = 1, groups = "Smoke")
	public void accessVisitsMenu(){
		hdPortalLoginPage.loginHDPortal(TestData.HDPORTAL_EMAIL, TestData.HDPORTAL_PASSWORD);//Remove this line
		// click on visits menu
		hdPortalManageAddNewVisitPage.clickVisitsMenu();
		// Expanding the visits Menu
		hdPortalManageAddNewVisitPage.clickPendingOption();
		// Verify the Pending visit title
		hdPortalManageAddNewVisitPage.verifyPendingVisitsGridTitle();
	}
	//@Click Add button and verify the Add new Visit dailouge window usecase
	@Test(priority = 2, groups = "Smoke", dependsOnMethods = { "accessVisitsMenu" })
	public void accessAddButton() throws InterruptedException, AWTException{
		//click 'Add' Button from pending Visits
		hdPortalManageAddNewVisitPage.clickAddNewVisitToolBarButton();
		// Verify the Add new visit dailouge
		hdPortalManageAddNewVisitPage.verifyAddNewVisitDialogTitle();
	}
	//@Fill all required fields in Add New Visit dailouge 
	//@Verify the created add new visit  
	@Test(priority = 3, groups = "Smoke", dependsOnMethods = { "accessAddButton" }) 
	public void createAddNewVisit(){
		// @Create the add new visit
		hdPortalManageAddNewVisitPage.createNewAddVisit(TestData.SELECT_JOBTYPE, TestData.SELECT_WEARERGROUP,TestData.ADD_VISIT_WEARER_NUI,
				TestData.ADD_NEW_VISIT_WEARER, TestData.SELECT_FIELD_OFFICER,TestData.VISIT_START_DATE_FORMAT, TestData.VISIT_START_HOURS, 
				TestData.VISIT_START_MINUTES,TestData.VISIT_END_DATE_FORMAT, TestData.VISIT_END_HOURS, TestData.VISIT_EMD_MINUTES,
				TestData.ADD_VISIT_NOTES);
		hdPortalManageAddNewVisitPage.verifyCreatedAddNewVisit();
	}
	//* @Verify created add new visit listed in scheduled page    
	@Test(priority = 4, groups = "Smoke", dependsOnMethods = { "createAddNewVisit"}) 
	public void verifyAddNewVisitInScheduledPage() {
		driver.navigate().refresh();
		hdPortalManageAddNewVisitPage.clickVisitsMenu();
		// Navigating the shedulded visit
		hdPortalManageAddNewVisitPage.navigateToSheduledVisitsOption();
		// Verifying the created Add New visit with wearer NUI
		hdPortalManageAddNewVisitPage.verifyAddedNewVisitWithWearerNUIInSheduldedListPage(TestData.ADD_VISIT_WEARER_NUI);
		System.out.println("verifyed the created Add New Visit listed in Shedulded Page..!");////Irfan: Correct spelling of verifyed it should be Verified

	}
}
