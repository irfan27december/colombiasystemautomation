package com.buddi.hdportal.tests;
import java.awt.AWTException;
import org.testng.annotations.Test;
import com.buddi.colombia.testdata.TestData;
import com.buddi.colombia.utilities.Log;

/**
 * @author sumaiah

 **Use Cases **
 * @Login HD Portal
 * @Access Visit menu and verify
 * @Access History Page
 * @Open and Verify Complete and cancelled visits in history
 */

public class HDPortalManageVisitHistoryTests extends HDPortalBaseTest{
	// @click and expand the Visits usecase 
	@Test(priority = 1, groups = "Smoke")
	public void verifyCompletedVisitsInHistory() throws AWTException{
		Log.startTestCase("verifyCompletedVisitsInHistory");
		driver.navigate().refresh();
		// click on visits menu
		hdPortalManageVisitHistoryPage.clickVisitsMenu();
		//click on History menu
		hdPortalManageVisitHistoryPage.clickHistoryMenu();
		// verify history grid title
		hdPortalManageVisitHistoryPage.isVisitHistoryGridTitleDisplayed();
		//visits descending order list 
		hdPortalManageVisitHistoryPage.visitsSortDescendingClick();
		//open Completed visit 
		hdPortalManageVisitHistoryPage.openCompletedVisit(TestData.ADD_VISIT_WEARER_NUI);
		//verify the status 
		hdPortalManageVisitHistoryPage.isCompletedStatusDisplayed();
		//close panel
		hdPortalManageVisitHistoryPage.closePanel();
		Log.endTestCase("verifyCompletedVisitsInHistory");
	}

	// @click and expand the Visits usecase 
	@Test(priority = 2, groups = "Smoke")
	public void verifyCancelledVisitsInHistory() throws AWTException{
		Log.startTestCase("verifyCancelledVisitsInHistory");
		driver.navigate().refresh();
		// click on visits menu
		hdPortalManageVisitHistoryPage.clickVisitsMenu();
		//click on History menu
		hdPortalManageVisitHistoryPage.clickHistoryMenu();
		// verify history grid title
		hdPortalManageVisitHistoryPage.isVisitHistoryGridTitleDisplayed();
		//visits descending order list 
		hdPortalManageVisitHistoryPage.visitsSortDescendingClick();
		//open Completed visit 
		hdPortalManageVisitHistoryPage.openCancelledVisit(TestData.ADD_VISIT_WEARER_NUI);
		//verify the status 
		hdPortalManageVisitHistoryPage.isCancelledStatusDisplayed();
		//close panel
		hdPortalManageVisitHistoryPage.closePanel();
		Log.endTestCase("verifyCancelledVisitsInHistory");
	}

}
