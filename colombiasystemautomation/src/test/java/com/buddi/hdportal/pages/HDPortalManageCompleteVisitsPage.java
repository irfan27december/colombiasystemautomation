package com.buddi.hdportal.pages;

import java.awt.AWTException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.buddi.colombia.common.CommonActions;
import com.buddi.colombia.utilities.DatePicker;
import com.buddi.colombia.utilities.DateToString;
import com.buddi.colombia.utilities.Log;
import com.buddi.colombia.utilities.ReadProperties;


/**
 * @author sumaiah
 *
 */
public class HDPortalManageCompleteVisitsPage {

	WebDriver driver;
	ReadProperties properties = new ReadProperties();

	//Constructor
	public HDPortalManageCompleteVisitsPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//visitsMenuElement
	@FindBy(xpath = "//div[@class='x-treelist-item-text' and text()='Visits']") 
	private WebElement visitsMenuElement;
	//Scheduled visits related element 
	@FindBy(xpath= "//div[@class='x-treelist-item-text' and contains(text(),'Scheduled')]")
	private WebElement scheduledVisitsMenuElement;
	//Open scheduled visit
	@FindBy(xpath="/html/body/div[2]/div/div[2]/div/div/div[2]/div[2]/div[3]/div[1]/div[2]/table[1]/tbody/tr/td[1]/div/span")
	private WebElement scheduledOpenVisitElement;
	//Action Visit
	@FindBy(xpath="//span[@class='x-btn-inner x-btn-inner-default-toolbar-small' and text()='Action Visit']")
	private WebElement actionDialogButtonElement;
	@FindBy(xpath="//div[@class='x-title-text x-title-text-default x-title-item' and text()='Action Visit']")
	private WebElement actionDialogTitleElement;
	//Select OutCome option
	@FindBy(xpath="//input[@name='outcome_combo']")
	private WebElement outComeDropdownElement;
	//Select Completed Reason
	@FindBy(xpath="//input[@name='complete_combo']")
	private WebElement completedReasonDropdownElement;
	// Visit Start Date related elements 
	@FindBy(xpath="//input[@name='startvisitdate']")
	private WebElement visitStartDateElement;
	@FindBy(xpath="//input[@name='startvisithour']")
	private WebElement visitStartHourElement;
	@FindBy(xpath="//input[@name='startvisitmin']")
	private WebElement visitStartMinElement;
	//Visit end Date related 
	@FindBy(xpath="//input[@name='endvisitdate']")
	private WebElement visitEndDateElement;
	@FindBy(xpath="//input[@name='endvisithour']")
	private WebElement visitEndHourElement;
	@FindBy(xpath="//input[@name='endvisitmin']")
	private WebElement visitEndMinElement;
	//Notes text Box 
	@FindBy(xpath="//textarea[@name='notes']")
	private WebElement completedNotesTextBoxElement;
	//Update button
	@FindBy(xpath="//span[@class='x-btn-inner x-btn-inner-default-small' and text()='Update']")
	private WebElement actionDialogUpdateButtonElement;

	@FindBy(xpath="/html/body/div[3]/div/div[2]/div/div/div[3]/div[1]/div/div/div[2]/div")
	private WebElement closePanelElement;


	// Method to click LHS menu option
	public void clickVisitsMenu() {
		CommonActions.waitForElementToBeVisible(driver, visitsMenuElement);
		if(visitsMenuElement.isDisplayed()){
			visitsMenuElement.click();
			Log.info("Visits menu is clicked...");
		}else{
			Log.info("Visits menu is not displayed...");
		}
	}

	//Method to navigate to Scheduled Page
	public void clickSheduledVisitsMenu(){
		CommonActions.waitForElementToBeVisible(driver, scheduledVisitsMenuElement);
		if(scheduledVisitsMenuElement.isDisplayed()) {
			CommonActions.javaScriptClick(driver, scheduledVisitsMenuElement);
			Log.info("Scheduled Visits menu is clicked...");
		}
		else{
			Log.info("Failed to clicked on Scheduled Visits..");
		}
	}

	// Method to Select visit in scheduled List page 
	public boolean isNUIListedInScheduledPage(String addNewVisitWearerNUI ) {
		WebElement addVisitWearerNUIElement = driver.findElement(By.xpath("//div[@class='x-grid-cell-inner ' and contains(text(),"
				+ " '"+addNewVisitWearerNUI+"')]"));
		CommonActions.waitForElementToBeClickable(driver, addVisitWearerNUIElement);
		if(addVisitWearerNUIElement.isDisplayed()) {
			CommonActions.javaScriptClick(driver, addVisitWearerNUIElement);
			Log.info("Is wearer NUI listed in scheduled page...!"+addVisitWearerNUIElement.isDisplayed());
			return addVisitWearerNUIElement.isDisplayed();
		}

		else{
			Log.info("Failed to clicked on open visit..!");
			return false;
		}	
	}
	// Method to click open visit in scheduled page 
	public void clickOpenVisitInScheduledPage(String addNewVisitWearerNUI ) {
		WebElement addVisitWearerNUIElement = driver.findElement(By.xpath("//div[@class='x-grid-cell-inner ' and contains(text(),"
				+ " '"+addNewVisitWearerNUI+"')]"));
		CommonActions.waitForElementToBeClickable(driver, addVisitWearerNUIElement);
		if(addVisitWearerNUIElement.isDisplayed()) {
			CommonActions.javaScriptClick(driver, addVisitWearerNUIElement);
			CommonActions.waitForElementToBeClickable(driver, scheduledOpenVisitElement);
			if(scheduledOpenVisitElement.isDisplayed()) {
				CommonActions.javaScriptClick(driver, scheduledOpenVisitElement);
				Log.info("Open "+addNewVisitWearerNUI+" NUI wearer visit ..."+addVisitWearerNUIElement.isDisplayed());
			}
		}
		else{
			Log.info("Failed to clicked on open visit..!");
		}	
	}
	//Method to click action button 
	public void clickActionDialogButton(){
		CommonActions.waitForElementToBeClickable(driver, actionDialogButtonElement);
		if(actionDialogButtonElement.isDisplayed()) {
			CommonActions.javaScriptClick(driver, actionDialogButtonElement);
			Log.info("Clicked on action visit button..!");
		}
		else{
			Log.info("Failed to clicked on action visit button..!");
		}
	}

	//Method to verify Add New Visit dialog window title  
	public boolean isActionDialogTitleDisplayed() throws AWTException{
		CommonActions.waitForElementToBeVisible(driver, actionDialogTitleElement);
		if(actionDialogTitleElement.isDisplayed()){
			Log.info("Is Action dialog title is displayed...!"+actionDialogTitleElement.isDisplayed());
			return actionDialogTitleElement.isDisplayed();
		}else{
			Log.info("Failed to displayed Action dialog title...");
			return false;
		}
	}

	//Method to select outcome dropdown value
	public void setOutComeOption(String selectCompletedOutCome){
		CommonActions.waitForElementToBeClickable(driver, outComeDropdownElement);
		if(outComeDropdownElement.isDisplayed()) {
			CommonActions.javaScriptClick(driver, outComeDropdownElement);
			outComeDropdownElement.sendKeys(selectCompletedOutCome);
			CommonActions.autoSuggestionMethod(driver);
			Log.info("OutCome dropdown is clicked......Selected value is: "+selectCompletedOutCome);
		}else{
			Log.info("OutCome dropdown is not clicked...");
		}
	}

	//Method to cancelled visit reason  
	public void setCompletedReason(String selectCompletedReason){
		CommonActions.waitForElementToBeClickable(driver, completedReasonDropdownElement);
		if(completedReasonDropdownElement.isDisplayed()) {
			CommonActions.javaScriptClick(driver, completedReasonDropdownElement);
			completedReasonDropdownElement.sendKeys(selectCompletedReason);
			CommonActions.autoSuggestionMethod(driver);
			Log.info("Reason dropdown is clicked...Selected value as: "+selectCompletedReason);

		}else{
			Log.info("Failed to clicked reason dropdown...");
		}
	}
	//Method to set visit start date
	public void setVisitStartDate(String visitStartDateVal) {
		CommonActions.waitForElementToBeClickable(driver, visitStartDateElement);
		if(visitStartDateElement.isDisplayed()){
			CommonActions.javaScriptClick(driver, visitStartDateElement);
			DatePicker.datePcikerByJS(driver, visitStartDateElement, visitStartDateVal);
			Log.info("Selected end date is: "+visitStartDateVal);

		}else{
			Log.info("Failed to select End date...");
		}	
	}	
	//Method to set visit start hours
	public void setVisitStartHours(String visitStartHours){
		CommonActions.waitForElementToBeVisible(driver, visitStartHourElement);
		if(visitStartHourElement.isDisplayed()){
			visitStartHourElement.click();
			visitStartHourElement.clear();
			visitStartHourElement.sendKeys(visitStartHours);
			visitStartHourElement.sendKeys(Keys.TAB);
			Log.info("Selected visit start hours as: "+visitStartHours);
		}else{
			Log.info("Failed to select visit start hours...");
		}
	}

	//Method to set visit start minutes
	public void setVisitStartMinutes(String visitStartMinutes){
		CommonActions.waitForElementToBeVisible(driver, visitStartMinElement);
		if(visitStartMinElement.isDisplayed()){
			visitStartMinElement.click();
			visitStartMinElement.clear();
			visitStartMinElement.sendKeys(visitStartMinutes);
			visitStartMinElement.sendKeys(Keys.TAB);
			Log.info("Selected visit start minutes as: "+visitStartMinutes);
		}else{
			Log.info("Failed to selecte visit start minutes...");
		}		
	}

	//Method to set visit end date
	public void setVisitEndDate(String visitEndDateVal) {
		CommonActions.waitForElementToBeClickable(driver, visitEndDateElement);
		if(visitEndDateElement.isDisplayed()){
			CommonActions.javaScriptClick(driver, visitEndDateElement);
			DatePicker.datePcikerByJS(driver, visitEndDateElement, visitEndDateVal);
			Log.info("Selected visit end date as: "+visitEndDateVal);

		}else{
			Log.info("Failed to select End date...");
		}	
	}	

	//Method to set visit start hours
	public void setVisitEndHours(String visitEndHours){
		CommonActions.waitForElementToBeVisible(driver, visitEndHourElement);
		if(visitEndHourElement.isDisplayed()){
			visitEndHourElement.click();
			visitEndHourElement.clear();
			visitEndHourElement.sendKeys(visitEndHours);
			visitEndHourElement.sendKeys(Keys.TAB);
			Log.info("Selected visit End hours as :"+visitEndHours);
		}else{
			Log.info("Failed to selecte End hours...");
		}	
	}

	//Method to set visit start minutes
	public void setVisitEndMinutes(String visitEndinutes){
		CommonActions.waitForElementToBeVisible(driver, visitEndMinElement);
		if(visitEndMinElement.isDisplayed()){
			visitEndMinElement.click();
			visitEndMinElement.clear();
			visitEndMinElement.sendKeys(visitEndinutes);
			visitEndMinElement.sendKeys(Keys.TAB);
			Log.info("Selected visit End minutes as :"+visitEndinutes);
		}else{
			Log.info("Failed to select visit End minutes ...");
		}		
	}

	//Method to enter notes
	public void enterCompletedNotes(String enterCompletedNotes){
		CommonActions.waitForElementToBeClickable(driver, completedNotesTextBoxElement);
		if(completedNotesTextBoxElement.isDisplayed()) {
			CommonActions.javaScriptClick(driver, completedNotesTextBoxElement);
			completedNotesTextBoxElement.sendKeys(enterCompletedNotes);
			CommonActions.autoSuggestionMethod(driver);
			Log.info("Entered completed Notes is : "+enterCompletedNotes);
		}else{
			Log.info("Failed to entered completed Notes text box...");
		}
	}
	//Method to click Update button
	public void clickUpdateButton() {
		CommonActions.waitForElementToBeClickable(driver, actionDialogUpdateButtonElement);
		if(actionDialogUpdateButtonElement.isEnabled() && actionDialogUpdateButtonElement.isDisplayed()){
			CommonActions.javaScriptClick(driver, actionDialogUpdateButtonElement);
			Log.info("Clicked Update button..");
		}else{
			Log.info("Failed to click add new visit button..");
		}
	}

	//Method to navigate to Scheduled Page
	public void selectCompletedVisit(String selectCompletedOutCome, String selectCompletedReason, String visitStartDateFormat, String visitStartHours, 
			String visitStartMinutes, String visitEndDateFormat, String visitEndHours, String visitEndMinutes, String enterCompletedNotes){
		setOutComeOption(selectCompletedOutCome);
		setCompletedReason(selectCompletedReason);
		setVisitStartDate(visitStartDateFormat);
		setVisitStartHours(visitStartHours);
		setVisitStartMinutes(visitStartMinutes);
		setVisitEndDate(visitEndDateFormat);
		setVisitEndHours(visitEndHours);
		setVisitEndMinutes(visitEndMinutes);
		enterCompletedNotes(enterCompletedNotes);
		clickUpdateButton();
	}

	// Method to close the panel
	public void closePanel() {
		CommonActions.waitForElementToBeClickable(driver, closePanelElement);
		if(closePanelElement.isDisplayed()) {
			CommonActions.javaScriptClick(driver, closePanelElement);
			Log.info("Clicked on close panel..!");
		}
		else{
			Log.info("Failed to clicked on close panel..!");
		}	
	}
}


