package com.buddi.hdportal.tests;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.buddi.colombia.testdata.StringConstants;
import com.buddi.colombia.testdata.TestData;

/**
 * @author Ganesh
 *
 */

         // Method to Access HD portal Login
        public class HDPortalManageCreditNoteTest extends HDPortalBaseTest 
        
{
	     @Test(priority = 1, groups = "Smoke")
	     public void accessManagementMenuForCR() throws InterruptedException
	     {
		    hdPortalLoginPage.loginHDPortal("ganesh.chilakamarthi@buddi.co.uk", "Shankar@18");
		    hdPortalManageCreditNotePage.clickInventoryMenu();
	     }
	
	     
	     // Method to Access Inventory menu for Credit note
	     @Test(priority = 2, groups = "Smoke",dependsOnMethods={"accessManagementMenuForCR"})
	     public void accessRecordCreditNoteMenuItem()
	     {
		    hdPortalManageCreditNotePage.clickCreditNoteOption();
		    String actualCreditNoteTitle = hdPortalManageCreditNotePage.verifyAddNewRecordDialogTitle();
		    Assert.assertEquals(actualCreditNoteTitle, StringConstants.CREDITNOTE_PANEL_TITLE);
	     }	
	
	     
	     // Method to enter details in Credit note window
	     @Test(priority = 3, groups = "Smoke", dependsOnMethods={"accessRecordCreditNoteMenuItem"})
	     public void createNewCreditNote()
	     {
		    driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		    hdPortalManageCreditNotePage.clickToolsBarAddRecordCreditNotesButton();
		    hdPortalManageCreditNotePage.createNewRecordCreditNote(TestData.RECORDCREDITNOTE_UBIN_ITEMNUMBER,TestData.RECORDCREDITNOTE_WEARER_NAME,TestData.RECORDCREDITNOTE_NOTES,TestData.RECORDCREDITNOTE_OBSERVATION,TestData.RECORDCREDITNOTE_START_DATE_FORMAT,TestData.RECORDCREDITNOTE_END_DATE_FORMAT);
		    driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		    driver.navigate().refresh();
		    driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		 }

}
