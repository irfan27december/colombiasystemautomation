/**
 * 
 */

package com.buddi.hdportal.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.buddi.colombia.common.CommonActions;
import com.buddi.colombia.utilities.DateToString;
import com.buddi.colombia.utilities.Log;

/**
 * @author Ganesh
 *
 */
public class HDPortalManageCreditNotePage 
{
	WebDriver driver;

	//Constructor
	public HDPortalManageCreditNotePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//Using FindBy for locating elements

	//Web elements related to RecordCreditNotes
	@FindBy(xpath = "//div[contains(text(),'Inventory')]") 
	private WebElement inventoryMenuElement;
	@FindBy(xpath = "//div[@class='x-treelist-item-text' and contains(text(),'Credit Note')]") 
	private WebElement creditNotetElement; //"//"//div[contains(text(),'Credit Note')]")
	@FindBy(xpath = "//div[contains(text(),'Record Credit Notes')]") 
	private WebElement recordCreditNotesTitleElement;

	//Web elements related to add Record Notes
	@FindBy(xpath = "//span[contains(text(),'Record')]") 
	private WebElement toolBarRecordCreditNotesButton;
	@FindBy(xpath = "//div[contains(text(),'Record Credit Note')]") 
	private WebElement recordCreditNoteDialogTitleElement;


	//Web elements related to  add Record notes window
	@FindBy(xpath = "//input[@name='ubin']")
	private WebElement ubinDropdownField;
	//@FindBy(xpath = "//input[@role='presentation']")
	//private WebElement UbinDropdown;
	@FindBy(xpath = "//input[@name='wearer_name']")
	private WebElement wearerNameField;
	@FindBy(xpath = "//textarea[@name='notes']") 
	private WebElement notesField;
	@FindBy(xpath = "//textarea[@name='lab_observation']") 
	private WebElement labobservationTextboxField;
	@FindBy(xpath = "//input[@name='startdate']") 
	private WebElement startDateField;
	@FindBy(xpath = "//input[@name='enddate']") 
	private WebElement endDateField;
	@FindBy(xpath = "*//span[@class='x-btn-inner x-btn-inner-default-small'  and contains(text(),'Record')]")
	private WebElement addRecordButton;
	@FindBy(xpath = "//div[contains(text(),'Record created')]") 
	private WebElement creditNoteAddedMessage;





	// Method to click LHS menu option
	public void clickInventoryMenu(){
		CommonActions.waitForElementToBeVisible(driver, inventoryMenuElement);
		if(inventoryMenuElement.isDisplayed()){
			inventoryMenuElement.click();
			Log.info("Inventory menu option is present...");
		}else{				
			Log.error("Inventory menu option is not displayed...");
		}
		System.out.println("Inventory menu is expanded...");
	}


	// Method to click Credit Note option
	public void clickCreditNoteOption(){
		CommonActions.waitForElementToBeVisible(driver, creditNotetElement);
		if(creditNotetElement.isDisplayed()){
			creditNotetElement.click();
			Log.info("CreditNote option is present...");
		}else{
			Log.error("CreditNote option is not displayed...");
		}
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		Log.info("Clicked Credit Note option...");

	}


	//Method to click tools bar add button
	public void clickToolsBarAddRecordCreditNotesButton(){
		CommonActions.waitForElementToBeVisible(driver, toolBarRecordCreditNotesButton);
		if(toolBarRecordCreditNotesButton.isDisplayed()){
			Log.info("Tools bar addRecord option is present...");
		}else{
			Log.error("Tools bar addRecord option is not displayed...");
		}
		if(toolBarRecordCreditNotesButton.isEnabled()){
			toolBarRecordCreditNotesButton.click();
			Log.info("Tools bar addRecord button is clicked...");
		}else{
			Log.error("Tools bar addRecord button is not clicked...");
		}
	}


	//Method to verify Add New Record dialog title
	public String verifyAddNewRecordDialogTitle(){
		CommonActions.waitForElementToBeVisible(driver, recordCreditNoteDialogTitleElement);
		if(recordCreditNoteDialogTitleElement.isEnabled()){
			Log.info("Add New Record dialog box is displayed...");
			return recordCreditNoteDialogTitleElement.getText();
		}else{
			Log.error("Add New Record dialog box is not displayed...");
		}
		return null;
	}


	//Method to check if Add New Record dialog is displayed
	public boolean isAddNewRecordtDialogDisplayed(){
		CommonActions.waitForElementToBeVisible(driver, recordCreditNoteDialogTitleElement);
		if(recordCreditNoteDialogTitleElement.isDisplayed()){
			Log.info("Add New Record dialog box is displayed...");
			return true;
		}else{
			Log.error("Add New Record dialog box is not displayed...");
			return false;
		}

	}


	//Method to set ubin
	public void setUbin(String UbinNumber){
		CommonActions.waitForElementToBeVisible(driver, ubinDropdownField);
		if(ubinDropdownField.isDisplayed()){
			Log.info("ubin text box option is present...");
		}else{
			Log.error("ubin text box option is not displayed...");
		}
		ubinDropdownField.click();
		ubinDropdownField.clear();
		ubinDropdownField.sendKeys(UbinNumber);
		Log.info("Entered ubin number is..."+UbinNumber);

	}


	// Method to set wearer
	public void setWearer(String wearer){
		CommonActions.waitForElementToBeVisible(driver, wearerNameField);
		if(wearerNameField.isDisplayed()){
			Log.info("wearer name option is present...");
		}else{
			Log.error("wearer name option  is not displayed...");
		}
		wearerNameField.click();
		wearerNameField.clear();
		wearerNameField.sendKeys(wearer);
		Log.info("Entered wearer is..."+wearer);		
	}


	// Method to set Notes
	public void setNotes(String Notes){
		CommonActions.waitForElementToBeVisible(driver, notesField);
		if(notesField.isDisplayed()){
			Log.info("Notes field option is present...");
		} else{
			Log.error("Notes field option  is not displayed...");
		}
		notesField.click();
		notesField.clear();
		notesField.sendKeys(Notes);
		Log.info("Entered Notes is..."+Notes);		
	}


	// Method to set observation
	public void setObservtion(String Observation){
		CommonActions.waitForElementToBeVisible(driver, labobservationTextboxField);
		if(labobservationTextboxField.isDisplayed()){
			Log.info("lab observation option is present...");
		}else{
			Log.error("lab observation option  is not displayed...");
		}
		labobservationTextboxField.click();
		labobservationTextboxField.clear();
		labobservationTextboxField.sendKeys(Observation);
		Log.info("Entered Notes is..."+Observation);		
	}


	//Method to set Record start date
	public void setRecordStartDate(String RecordStartDateFormat){
		CommonActions.waitForElementToBeVisible(driver, startDateField);
		startDateField.click();
		startDateField.clear();
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		startDateField.sendKeys(RecordStartDateFormat);
		//System.out.println("DateToString.returnDate(alertStartDateFormat)     "+DateToString.returnDate(RecordStartDateFormat));
		//startDateField.sendKeys(DateToString.returnDate(RecordStartDateFormat));
		//startDateField.sendKeys(Keys.TAB);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}

	//Method to set Record End date
	public void setRecordEndDate(String RecordEndDateFormat){
		CommonActions.waitForElementToBeVisible(driver, endDateField);
		endDateField.click();
		endDateField.clear();
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		endDateField.sendKeys(RecordEndDateFormat);
		System.out.println("Entered date is..."  +RecordEndDateFormat);
		//Log.info("DateToString.returnDate(RecordEndDateFormat)     "+DateToString.returnDate(RecordEndDateFormat));
		//endDateField.sendKeys(DateToString.returnDate(RecordEndDateFormat));
		//startDateField.sendKeys(Keys.TAB);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

	}




	//Method to create Record credit note
	public void createRecordCreditNote(String UbinNumber, String wearer, String Notes, String Observation,String RecordStartDateFormat, String RecordEndDateFormat)
	{
		setUbin(UbinNumber);
		setWearer(wearer);
		setNotes(Notes);
		setObservtion(Observation);
		setRecordStartDate(RecordStartDateFormat);
		setRecordEndDate(RecordEndDateFormat);
		if(addRecordButton.isEnabled())
		{	
			Log.info("Add button to create RCN is enabled "+addRecordButton.isEnabled());		
			CommonActions.waitForElementToBeClickable(driver, addRecordButton);
			CommonActions.clickElementToHandleStaleElementException(addRecordButton);
			driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);				
		}
	} 


	//Method to click record button in Add new record dialog
	public void clickAddNewRecordButton(){
		CommonActions.waitForElementToBeVisible(driver, addRecordButton);
		if(addRecordButton.isEnabled()){
			addRecordButton.click();
			Log.info("Add new record button is clicked in Add New record dialog...");
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			//JavascriptExecutor jse = (JavascriptExecutor)driver;
			//jse.executeScript("window.scrollBy(0,-250)");
		}else{
			Log.error("Failed to click add new record button is clicked in Add New record dialog...");				
		}				
	}



	//Method to create new record
	public void createNewRecordCreditNote(String UbinNumber, String wearer, String Notes, String Observation,String RecordStartDateFormat, String RecordEndDateFormat) throws InterruptedException
	{
		setUbin(UbinNumber);
		setWearer(wearer);
		setNotes(Notes);
		setObservtion(Observation);
		setRecordStartDate(RecordStartDateFormat);
		setRecordEndDate(RecordEndDateFormat);
		clickAddNewRecordButton();
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		isUbinDisplayedInRecordCrediNotePage(UbinNumber);


	}


	//Method to check UBIN in Record credit note page 
	public boolean isUbinDisplayedInRecordCrediNotePage(String UbinNumber) throws InterruptedException{ 

		//navigateToNewRecordOption();
		WebElement UbinElement = driver.findElement(By.xpath("//div[@class='x-grid-cell-inner ' and contains(text(),"
				+ " '"+UbinNumber+"')]"));
		String ue =UbinElement.getText();
		Log.info(ue);
		CommonActions.waitForElementToBeVisible(driver, UbinElement);
		if(UbinElement.isDisplayed()){
			//Thread.sleep(5000);

			CommonActions.clickElementToHandleStaleElementException(UbinElement);
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(5000);
			Log.info("New UBIN number "+UbinNumber+ " is displayed in Record credit note page and clicked...");
			return true;
		}else{
			Log.error("UBIN number "+UbinNumber+ " No Record credit note page...");
			return false;

		}
	}


	//Method to navigate to New Records
	public void navigateToNewRecordOption(){
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.navigate().refresh();
		CommonActions.waitForElementToBeClickable(driver, inventoryMenuElement);
		CommonActions.clickElementToHandleStaleElementException(inventoryMenuElement);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		CommonActions.waitForElementToBeClickable(driver, creditNotetElement);
		//CommonActions.clickElementToHandleStaleElementException(creditNotetElement);	
	}	


}
