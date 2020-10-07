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
import com.buddi.colombia.utilities.DateToString;
import com.buddi.colombia.utilities.ReadProperties;


/**
 * @author sumaiah
 *
 */

public class HDPortalManageAddNewVisitPage {

	WebDriver driver;
	ReadProperties properties = new ReadProperties();

	//Constructor
	public HDPortalManageAddNewVisitPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	//Web elements related to pending visits
	//visitsMenuElement
	@FindBy(xpath = "//div[@class='x-treelist-item-text' and text()='Visits']") 
	private WebElement visitsMenuElement;
	//pendingVisitsElement
	@FindBy(xpath = "//div[@class='x-treelist-item-text' and @id='ext-element-31' and text()='Pending']") 
	private WebElement pendingVisitsMenuElement;
	//pendingVisitsGridTitleElement
	@FindBy(xpath = "//div[@class='x-title-text x-title-text-default-framed x-title-item' and text()='Pending Visits']") 
	private WebElement pendingVisitsGridTitleElement;
	//Collapse and expand menu panel elements
	@FindBy(xpath = "//div[@class='x-tool-tool-el x-tool-img x-tool-collapse-left ']")
	private WebElement collapsePanelElement;
	@FindBy(xpath ="//div[@class='x-tool-tool-el x-tool-img x-tool-expand-right ']")
	private WebElement expandPanelElement; 
	//Add Button
	@FindBy(xpath = ".//span[@class = 'x-btn-inner x-btn-inner-default-toolbar-small' and contains(text(), 'Add')]")
	private WebElement addToolBarButtonElement;	
	// AddNewVisit window title
	@FindBy(xpath = "//div[@class = 'x-title-text x-title-text-default x-title-item' and contains(text(), 'Add New Visit')]")
	//".//div[@class = 'x-title-text x-title-text-default x-title-item'][starts-with(@id,'visitsNewAddWindow')]") 
	private WebElement addNewVisitDialogTitleElement;
	//Elements in Add New visit window 
	@FindBy(xpath ="//input[@name='job_type_id']") 
	private WebElement jobtypeDropDownElement;
	@FindBy(xpath = "//input[@name='visitwearergroup']") 
	private WebElement wearerGroupDropDownElement;
	@FindBy(xpath = "//input[@name='visitwearer']") 
	private WebElement addVisitWearerDropDownElement;
	@FindBy(xpath = "//input[@name='assignedofficer']")
	private WebElement assignedfieldOfficerDropdownElement;
	// Start Dates related Elements
	@FindBy(xpath = "//input[@name='startdate']") 
	private WebElement startDateField;
	@FindBy(xpath = "//input[@name='starthour']") 
	private WebElement startHourField;
	@FindBy(xpath = "//input[@name='startmin']") 
	private WebElement startMinutesField;
	// End Date related element 
	@FindBy(xpath = "//input[@name='enddate']")
	private WebElement endDateField;
	@FindBy(xpath = "//input[@name='endhour']") 
	private WebElement endHourField;
	@FindBy(xpath = "//input[@name='endmin']") 
	private WebElement endMinutesField;
	//Notes text box element 
	@FindBy(xpath = "//textarea[@name='notes']") 
	private WebElement addVisitNotesTextBoxElement;
	//Add Button
	@FindBy(xpath = "//span[@class='x-btn-inner x-btn-inner-default-small' and contains(text(),'Add')]")
	private WebElement addNewVisitDialogAddButtonElement;
	//Scheduled visits related element 
	@FindBy(xpath= "//div[@class='x-treelist-item-text' and contains(text(),'Scheduled')]")
	private WebElement scheduledVisitsMenuElement;
	@FindBy(xpath="//div[@class='x-title-text x-title-text-default-framed x-title-item'][starts-with(text(),'Visit - ')]")
	private WebElement scheduledVisitGridTitleElement;
	//Open scheduled visit
	@FindBy(xpath="//div[@class='x-grid-cell-inner x-grid-cell-inner-action-col']/span[@class='btn btn-default btn-sm']")
	private WebElement scheduledOpenVisitElement;
	//Action Visit
	@FindBy(xpath="//span[@class='x-btn-inner x-btn-inner-default-toolbar-small' and text()='Action Visit']")
	private WebElement actionDialogButtonElement;
	@FindBy(xpath="//div[@class='x-title-text x-title-text-default x-title-item' and text()='Action Visit']")
	private WebElement actionDialogTitleElement;
	//Select OutCome option
	@FindBy(xpath="//input[@name='outcome_combo']")
	private WebElement outComeDropdownElement;
	// Select cancelled reason
	@FindBy(xpath="//input[@name='cancelled_combo']")
	private WebElement cancelledReasonDropdownElement;
	//Notes text Box 
	@FindBy(xpath="//textarea[@name='notes']")
	private WebElement cancelledNotesTextBoxElement;
	//Update button
	@FindBy(xpath="//span[@class='x-btn-inner x-btn-inner-default-small' and text()='Update']")
	private WebElement actionDialogUpdateButtonElement;
	//History menu element
	@FindBy(xpath="//div[@class='x-treelist-item-text' and text()='History']")
	private WebElement visitHistoryMenuElement;
	@FindBy(xpath="//div[@class='x-title-text x-title-text-default-framed x-title-item' and text()='Visit History']")
	private WebElement visitHistoryGridTitleElement;


	// Method to click LHS menu option
	public void clickVisitsMenu() {
		CommonActions.waitForElementToBeVisible(driver, visitsMenuElement);
		if(visitsMenuElement.isDisplayed()){
			visitsMenuElement.click();
			System.out.println("Visits menu is clicked...");
		}else{
			System.out.println("Visits menu is not displayed...");
		}
	}
	// Method to click Pending option
	public void clickPendingOption() throws AWTException {
		CommonActions.waitForElementToBeVisible(driver, pendingVisitsMenuElement);
		if(pendingVisitsMenuElement.isDisplayed()){
			pendingVisitsMenuElement.click();
			System.out.println("Pending visits sub menu is clicked...");
		}else{
			System.out.println("Pending visits sub menu is not displayed...");
		}
	}

	// Method to verify Pending Visits Grid title
	public boolean isPendingVisitsGridTitleDisplayed() {
		CommonActions.waitForElementToBeVisible(driver, pendingVisitsGridTitleElement);
		if(pendingVisitsGridTitleElement.isDisplayed()){
			System.out.println(pendingVisitsGridTitleElement.getText()+" : pending visit grid title is verified ...");
			return true;
		}else{
			System.out.println("Pending visits sub menu is not displayed...");
			return false;
		}	
	}	

	//Method to click Add tool bar button
	public void clickAddNewVisitToolBarButton() throws InterruptedException, AWTException{
		//CommonActions.waitForElementToBeClickable(driver, collapsePanelElement);
		//collapsePanelElement.click();
		CommonActions.zoomOut();
		CommonActions.waitForElementToBeClickable(driver, addToolBarButtonElement);
		if(addToolBarButtonElement.isDisplayed()){
			CommonActions.javaScriptClick(driver, addToolBarButtonElement);
			System.out.println("'Add' tool bar button is clicked...");
		}else{
			System.out.println("'Add' tool bar button is not displayed...");
		}
	}

	//Method to verify Add New Visit dialog window title  
	public boolean isAddNewVisitDialogTitleDisplayed() throws AWTException{
		CommonActions.zoomIn();
		CommonActions.waitForElementToBeVisible(driver, addNewVisitDialogTitleElement);
		if(addNewVisitDialogTitleElement.isDisplayed()){
			System.out.println(addNewVisitDialogTitleElement.getText()+" : dialog title is verified ...");
			return true;
		}else{
			System.out.println("Add New visit dialog title is not displayed...");
			return false;
		}
	}

	//Method to select jobtype
	public void setJobtype(String selectJobType ) {
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		CommonActions.waitForElementToBeClickable(driver, jobtypeDropDownElement);
		if(jobtypeDropDownElement.isDisplayed()){
			CommonActions.javaScriptClick(driver, jobtypeDropDownElement);
			System.out.println("Select Job Type dropdown is clicked ...");
			jobtypeDropDownElement.sendKeys(selectJobType);
			CommonActions.autoSuggestionMethod(driver);
		}
		else{
			System.out.println("Select Job Type dropdown is not displayed ...");
		}
	}

	//Method to select the Wearer Group
	public void setWearerGroup(String selectWearerGroup) {
		CommonActions.waitForElementToBeClickable(driver, wearerGroupDropDownElement);
		if(wearerGroupDropDownElement.isDisplayed()){
			CommonActions.javaScriptClick(driver, wearerGroupDropDownElement);
			wearerGroupDropDownElement.sendKeys(selectWearerGroup);
			CommonActions.autoSuggestionMethod(driver);
			System.out.println("WearerGroup dropdown is clicked...");
		}else{
			System.out.println("WearerGroup dropdown is not displayed...");
		}
	}

	//Method to select the Wearer 
	public void setAddNewVisitWearer(String addNewVisitWearerNUI, String addNewVisitWearer) {
		CommonActions.waitForElementToBeVisible(driver, addVisitWearerDropDownElement);
		if(addVisitWearerDropDownElement.isDisplayed()){
			addVisitWearerDropDownElement.click();
			String str = addNewVisitWearerNUI+addNewVisitWearer;
			addVisitWearerDropDownElement.sendKeys(str);
			CommonActions.autoSuggestionMethod(driver);
			System.out.println("Select Wearer dropdown is clicked...");
		}else{
			System.out.println("Select Wearer dropdown is not displayed...");
		}
	}

	//Method to assigned field officer
	public void setAssignedFieldOfficer(String assignedFieldOfficer) {
		CommonActions.waitForElementToBeClickable(driver, assignedfieldOfficerDropdownElement);
		if(assignedfieldOfficerDropdownElement.isDisplayed()){
			CommonActions.javaScriptClick(driver, assignedfieldOfficerDropdownElement);
			System.out.println("Assigned field Officer dropdown is clicked...");
			assignedfieldOfficerDropdownElement.sendKeys(assignedFieldOfficer);
			CommonActions.autoSuggestionMethod(driver);
		}else{
			System.out.println("assigned field Officer dropdown is not displayed...");
		}
	}
	//Method to set visit start date
	public void setVisitStartDate(String visitStartDateFormat) {
		CommonActions.waitForElementToBeVisible(driver, startDateField);
		if(startDateField.isDisplayed()){
			startDateField.click();
			startDateField.clear();
			startDateField.sendKeys(DateToString.returnDate(visitStartDateFormat));
		}else{
			System.out.println("Start date is not displayed...");
		}
	}

	//Method to set visit start hours
	public void setVisitStartHours(String visitStartHours){
		CommonActions.waitForElementToBeVisible(driver, startHourField);
		if(startHourField.isDisplayed()){
			startHourField.click();
			startHourField.clear();
			startHourField.sendKeys(visitStartHours);
			startHourField.sendKeys(Keys.TAB);
		}else{
			System.out.println("Start hours is not displayed...");
		}
	}

	//Method to set visit start minutes
	public void setVisitStartMinutes(String visitStartMinutes){
		CommonActions.waitForElementToBeVisible(driver, startMinutesField);
		if(startMinutesField.isDisplayed()){
			startMinutesField.click();
			startMinutesField.clear();
			startMinutesField.sendKeys(visitStartMinutes);
			startMinutesField.sendKeys(Keys.TAB);
		}else{
			System.out.println("Start minutes is not displayed...");
		}		
	}

	//Method to set visit end date
	public void setVisitEndDate(String visitEndDateFormat) {
		CommonActions.waitForElementToBeVisible(driver, endDateField);
		if(endDateField.isDisplayed()){
			endDateField.click();
			endDateField.clear();
			endDateField.sendKeys(DateToString.returnDate(visitEndDateFormat));
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		}else{
			System.out.println("End date is not displayed...");
		}	
	}

	//Method to set visit start hours
	public void setVisitEndHours(String visitEndHours){
		CommonActions.waitForElementToBeVisible(driver, endHourField);
		if(endHourField.isDisplayed()){
			endHourField.click();
			endHourField.clear();
			endHourField.sendKeys(visitEndHours);
			endHourField.sendKeys(Keys.TAB);
		}else{
			System.out.println("End hours is not displayed...");
		}	
	}

	//Method to set visit start minutes
	public void setVisitEndMinutes(String visitEndinutes){
		CommonActions.waitForElementToBeVisible(driver, endMinutesField);
		if(endMinutesField.isDisplayed()){
			endMinutesField.click();
			endMinutesField.clear();
			endMinutesField.sendKeys(visitEndinutes);
			endMinutesField.sendKeys(Keys.TAB);
		}else{
			System.out.println("End minutes is not displayed...");
		}		
	}

	//Method to set visit notes
	public void setAddNewVisitNotes(String addNewVisitNotes){
		CommonActions.waitForElementToBeClickable(driver, addVisitNotesTextBoxElement);
		if(addVisitNotesTextBoxElement.isDisplayed()){
			addVisitNotesTextBoxElement.click();	
			addVisitNotesTextBoxElement.sendKeys(addNewVisitNotes);
			System.out.println("Notes text box is clicked..");
		}else{
			System.out.println("Notes text box failed to clicked...");
		}
	}
	//Method to click add button in Add New visit dialog window
	public void clickAddNewVisitDialogAddButton() {
		CommonActions.waitForElementToBeClickable(driver, addNewVisitDialogAddButtonElement);
		if(addNewVisitDialogAddButtonElement.isEnabled() && addNewVisitDialogAddButtonElement.isDisplayed()){
			CommonActions.javaScriptClick(driver, addNewVisitDialogAddButtonElement);
			System.out.println("Add new visit button is clicked..");
			//CommonActions.waitForElementToBeVisible(driver, expandPanelElement);
			//expandPanelElement.click();
		}else{
			System.out.println("Failed to click add new visit button..");
		}
	}

	//Method to create new visit
	public void createAddNewVisit(String selectJobType, String selectWearerGroup, String addNewVisitWearerNUI, String addNewVisitWearer,
			String assignedFieldOfficer,String visitStartDateFormat, String visitStartHours, String visitStartMinutes,
			String visitEndDateFormat,String visitEndHours, String visitEndMinutes, String addVisitNotes){
		setJobtype(selectJobType);
		setWearerGroup(selectWearerGroup);
		setAddNewVisitWearer(addNewVisitWearerNUI, addNewVisitWearer);
		setAssignedFieldOfficer(assignedFieldOfficer);
		setVisitStartDate(visitStartDateFormat);
		setVisitStartHours(visitStartHours);
		setVisitStartMinutes(visitStartMinutes);
		setVisitEndDate(visitEndDateFormat);
		setVisitEndHours(visitEndHours);
		setVisitEndMinutes(visitEndMinutes);
		setAddNewVisitNotes(addVisitNotes);
		clickAddNewVisitDialogAddButton();
	}

	// Method for viewing created Add New visit wearer details
	public boolean isScheduledVisitGridTitleDisplayed() throws AWTException {	
		CommonActions.waitForElementToBeVisible(driver, scheduledVisitGridTitleElement);
		if(scheduledVisitGridTitleElement.isDisplayed()) {
			CommonActions.javaScriptClick(driver, scheduledVisitGridTitleElement);
			System.out.println(scheduledVisitGridTitleElement.getText()	
					+" : Visit title is displayed...\n Add New Visit created successfully...");
			return true;
		}
		else{
			System.out.println(scheduledVisitGridTitleElement.getText()
					+" : Visit title is not displayed...\n Add New Visit is not created...");
			return false;
		}
	}
	//Method to navigate to Scheduled Page
	public void clickSheduledVisitsMenu(){
		CommonActions.waitForElementToBeVisible(driver, scheduledVisitsMenuElement);
		if(scheduledVisitsMenuElement.isDisplayed()) {
			CommonActions.javaScriptClick(driver, scheduledVisitsMenuElement);
			System.out.println("Scheduled Visits menu is clicked...");
		}
		else{
			System.out.println("Failed to clicked on Scheduled Visits..");
		}
	}	
	//Method to check NUI in In Scheduled visit 
	public boolean isWearerNUIDisplayedInScheduledListPage(String addNewVisitWearerNUI){ 
		WebElement addVisitWearerNUIElement = driver.findElement(By.xpath("//div[@class='x-grid-cell-inner ' and contains(text(),"
				+ " '"+addNewVisitWearerNUI+"')]"));
		CommonActions.waitForElementToBeVisible(driver, addVisitWearerNUIElement);
		if(addVisitWearerNUIElement.isDisplayed()){
			System.out.println("Add New Visit Wearer NUI "+addNewVisitWearerNUI+ " is displayed in Shedulded Visits list page...");
			addVisitWearerNUIElement.click();
			return true;
		}else{
			System.out.println("Wearer with NUI "+addNewVisitWearerNUI+ " is not displayed in In Shedulded visit list page...");
			return false;
		}
	}
	// Method to click open visit in scheduled page 
	public void clickOpenVisitInScheduledPage(String addNewVisitWearerNUI ) {
		WebElement addVisitWearerNUIElement = driver.findElement(By.xpath("//div[@class='x-grid-cell-inner ' and contains(text(),"
				+ " '"+addNewVisitWearerNUI+"')]"));
		CommonActions.waitForElementToBeVisible(driver, addVisitWearerNUIElement);
		CommonActions.waitForElementToBeClickable(driver, scheduledOpenVisitElement);
		if(scheduledOpenVisitElement.equals(addVisitWearerNUIElement) && scheduledOpenVisitElement.isDisplayed()) {
			CommonActions.javaScriptClick(driver, scheduledOpenVisitElement);
			System.out.println("Clicked on open visit..!");
		}
		else{
			System.out.println("Failed to clicked on open visit..!..");
		}	
	}
	//Method to click action button 
	public void clickActionDialogButton(){
		CommonActions.waitForElementToBeClickable(driver, actionDialogButtonElement);
		if(actionDialogButtonElement.isDisplayed()) {
			CommonActions.javaScriptClick(driver, actionDialogButtonElement);
			System.out.println("Clicked on action visit button..!");
		}
		else{
			System.out.println("Failed to clicked on action visit button..!");
		}
	}

	//Method to verify Add New Visit dialog window title  
	public boolean isActionDialogTitleDisplayed() throws AWTException{
		CommonActions.waitForElementToBeVisible(driver, actionDialogTitleElement);
		if(actionDialogTitleElement.isDisplayed()){
			System.out.println(actionDialogTitleElement.getText()+" : dialog title is verified ...");
			return true;
		}else{
			System.out.println("Action dialog title is not displayed...");
			return false;
		}
	}

	//Method to select outcome dropdown value
	public void setOutComeOption(String selectOutCome){
		CommonActions.waitForElementToBeClickable(driver, outComeDropdownElement);
		if(outComeDropdownElement.isDisplayed()) {
			CommonActions.javaScriptClick(driver, outComeDropdownElement);
			System.out.println("OutCome dropdown is clicked...");
			outComeDropdownElement.sendKeys(selectOutCome);
			CommonActions.autoSuggestionMethod(driver);
		}else{
			System.out.println("OutCome dropdown is not clicked...");
		}
	}

	//Method to cancelled visit reason  
	public void setCancelledReason(String selectReason){
		CommonActions.waitForElementToBeClickable(driver, cancelledReasonDropdownElement);
		if(cancelledReasonDropdownElement.isDisplayed()) {
			CommonActions.javaScriptClick(driver, cancelledReasonDropdownElement);
			System.out.println("Cancelled reason dropdown is clicked...");
			cancelledReasonDropdownElement.sendKeys(selectReason);
			CommonActions.autoSuggestionMethod(driver);
		}else{
			System.out.println("Cancelled reason dropdown is notclicked...");
		}
	}

	//Method to enter notes
	public void enterCancelledNotes(String enterNotes){
		CommonActions.waitForElementToBeClickable(driver, cancelledNotesTextBoxElement);
		if(cancelledNotesTextBoxElement.isDisplayed()) {
			CommonActions.javaScriptClick(driver, cancelledNotesTextBoxElement);
			System.out.println("Cancelled reason dropdown is clicked...");
			cancelledNotesTextBoxElement.sendKeys(enterNotes);
			CommonActions.autoSuggestionMethod(driver);
		}else{
			System.out.println("Cancelled reason dropdown is notclicked...");
		}
	}

	//Method to click Update button
	public void clickUpdateButton() {
		CommonActions.waitForElementToBeClickable(driver, actionDialogUpdateButtonElement);
		if(actionDialogUpdateButtonElement.isEnabled() && actionDialogUpdateButtonElement.isDisplayed()){
			CommonActions.javaScriptClick(driver, actionDialogUpdateButtonElement);
			System.out.println("Clicked Update button..");
		}else{
			System.out.println("Failed to click add new visit button..");
		}
	}

	//Method to navigate to Scheduled Page
	public void selectCancelledVisit(String outComeOption, String selectReason, String enterNotes){
		setOutComeOption(outComeOption);
		setCancelledReason(selectReason);
		enterCancelledNotes(enterNotes);
		clickUpdateButton();
		System.out.println("Visit Cancelled successfully..!");
	}
	// Method to open scheduled visit
	public void clickHistoryMenu() {
		CommonActions.waitForElementToBeClickable(driver, visitHistoryMenuElement);
		if(visitHistoryMenuElement.isDisplayed()) {
			CommonActions.javaScriptClick(driver, visitHistoryMenuElement);
			System.out.println("Clicked on History menu..!");
		}
		else{
			System.out.println("Failed to clicked on History menu..!");
		}	
	}
	//Method to verify Add New Visit dialog window title  
	public boolean isVisitHistoryGridTitleDisplayed() throws AWTException{
		CommonActions.waitForElementToBeVisible(driver,visitHistoryGridTitleElement );
		if(visitHistoryGridTitleElement.isDisplayed()){
			System.out.println(visitHistoryGridTitleElement.getText()+" : History Grid title is verified ...");
			return true;
		}else{
			System.out.println("History Grid title is not displayed...");
			return false;
		}
	}
}


