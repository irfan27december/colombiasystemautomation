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
import com.buddi.colombia.utilities.Log;
import com.buddi.colombia.utilities.ReadProperties;
import com.buddi.colombia.utilities.DatePicker;


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

	@FindBy(xpath = "/html/body/div[5]/div[2]/div/div/div/div[1]/div/div/div[5]/div/div/div[1]/div/div/div[2]") 
	private WebElement clickDatePickerElement;
	@FindBy(xpath = "//div[@class='x-datepicker-month'][@role='heading']") 
	private WebElement datePickerHeaderElement;


	// Start Dates related Elements
	@FindBy(xpath = "//input[@name='startdate']") 
	private WebElement visitStartDateElement;
	@FindBy(xpath = "//input[@name='starthour']") 
	private WebElement visitStartHourElement;
	@FindBy(xpath = "//input[@name='startmin']") 
	private WebElement visitStartMinutesElement;

	// End Date related element 
	@FindBy(xpath = "//input[@name='enddate']")
	private WebElement visitEndDateElement;
	@FindBy(xpath = "//input[@name='endhour']") 
	private WebElement visitEndHourElement;
	@FindBy(xpath = "//input[@name='endmin']") 
	private WebElement visitEndMinutesElement;
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
	@FindBy(xpath="/html/body/div[2]/div/div[2]/div/div/div[2]/div[2]/div[3]/div[1]/div[2]/table[1]/tbody/tr/td[1]/div/span")
	private WebElement scheduledOpenVisitElement;
	//History menu element
	@FindBy(xpath="//div[@class='x-treelist-item-text' and text()='History']")
	private WebElement visitHistoryMenuElement;
	@FindBy(xpath="//div[@class='x-title-text x-title-text-default-framed x-title-item' and text()='Visit History']")
	private WebElement visitHistoryGridTitleElement;
	@FindBy(xpath="/html/body/div[3]/div/div[2]/div/div/div[3]/div[1]/div/div/div[2]/div")
	private WebElement closePanelElement;


	// Method to click LHS menu option
	public void clickVisitsMenu() {
		CommonActions.waitForElementToBeVisible(driver, visitsMenuElement);
		if(visitsMenuElement.isDisplayed()){
			visitsMenuElement.click();
			Log.info("Visits menu is clicked...!");
		}else{
			Log.info("Failed to click Visits menu ...");
		}
	}
	// Method to click Pending option
	public void clickPendingOption() throws AWTException {
		CommonActions.waitForElementToBeVisible(driver, pendingVisitsMenuElement);
		if(pendingVisitsMenuElement.isDisplayed()){
			pendingVisitsMenuElement.click();
			Log.info("Pending visits sub menu is clicked...!");
		}else{
			Log.info("Failed to click pending visit sub menu...");
		}
	}

	// Method to verify Pending Visits Grid title
	public boolean isPendingVisitsGridTitleDisplayed() {
		CommonActions.waitForElementToBeVisible(driver, pendingVisitsGridTitleElement);
		if(pendingVisitsGridTitleElement.isDisplayed()){
			Log.info("Is pending visit grid title is verified ...!"+pendingVisitsGridTitleElement.isDisplayed());
			return pendingVisitsGridTitleElement.isDisplayed();
		}else{
			Log.info("Failed to click pending visits sub menu...");
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
			Log.info(" Add button is clicked...!");
		}else{
			Log.info("Failed to clicked Add button...");
		}
	}

	//Method to verify Add New Visit dialog window title  
	public boolean isAddNewVisitDialogTitleDisplayed() throws AWTException{
		CommonActions.zoomIn();
		CommonActions.waitForElementToBeVisible(driver, addNewVisitDialogTitleElement);
		if(addNewVisitDialogTitleElement.isDisplayed()){
			Log.info("Is Add New Visit dialog title is displayed...!"+addNewVisitDialogTitleElement.isDisplayed());
			return addNewVisitDialogTitleElement.isDisplayed();
		}else{
			Log.info("Failed to clicked Add New visit dialog title...");
			return false;
		}
	}

	//Method to select jobtype
	public void setJobtype(String selectJobType ) {
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		CommonActions.waitForElementToBeClickable(driver, jobtypeDropDownElement);
		if(jobtypeDropDownElement.isDisplayed()){
			CommonActions.javaScriptClick(driver, jobtypeDropDownElement);
			Log.info("Job Type dropdown is clicked ... Selected value is: "+selectJobType);
			jobtypeDropDownElement.sendKeys(selectJobType);
			CommonActions.autoSuggestionMethod(driver);
		}
		else{
			Log.info("Failed to clicked Job Type dropdown ...");
		}
	}

	//Method to select the Wearer Group
	public void setWearerGroup(String selectWearerGroup) {
		CommonActions.waitForElementToBeClickable(driver, wearerGroupDropDownElement);
		if(wearerGroupDropDownElement.isDisplayed()){
			CommonActions.javaScriptClick(driver, wearerGroupDropDownElement);
			wearerGroupDropDownElement.sendKeys(selectWearerGroup);
			CommonActions.autoSuggestionMethod(driver);
			Log.info("WearerGroup dropdown is clicked... Selected WearerGroup is :"+selectWearerGroup);
		}else{
			Log.info("Failed to clicked WearerGroup dropdown...");
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
			Log.info("Select Wearer dropdown is clicked...Selected wearer is : "+str);
		}else{
			Log.info("Failed to clicked Select Wearer dropdown...");
		}
	}

	//Method to assigned field officer
	public void setAssignedFieldOfficer(String assignedFieldOfficer) {
		CommonActions.waitForElementToBeClickable(driver, assignedfieldOfficerDropdownElement);
		if(assignedfieldOfficerDropdownElement.isDisplayed()){
			CommonActions.javaScriptClick(driver, assignedfieldOfficerDropdownElement);
			assignedfieldOfficerDropdownElement.sendKeys(assignedFieldOfficer);
			CommonActions.autoSuggestionMethod(driver);
			Log.info("Assigned field Officer dropdown is clicked...Selected FO is: "+assignedFieldOfficer);
		}else{
			Log.info("Failed to clicked assigned field Officer dropdown...");
		}
	}

	//Method to set visit start date
	public void setVisitStartDate(String visitStartDateVal) throws AWTException {
		CommonActions.waitForElementToBeClickable(driver, visitStartDateElement);
		if(visitStartDateElement.isDisplayed()){
			CommonActions.javaScriptClick(driver, visitStartDateElement); 
			DatePicker.datePcikerByJS(driver, visitStartDateElement, visitStartDateVal);
			Log.info("Selected end date is: "+visitStartDateVal);

		}else{ Log.info("Failed to select End date..."); }
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
			Log.info("Failed to select Start hours ...");
		}
	}

	//Method to set visit start minutes
	public void setVisitStartMinutes(String visitStartMinutes){
		CommonActions.waitForElementToBeVisible(driver, visitStartMinutesElement);
		if(visitStartMinutesElement.isDisplayed()){
			visitStartMinutesElement.click();
			visitStartMinutesElement.clear();
			visitStartMinutesElement.sendKeys(visitStartMinutes);
			visitStartMinutesElement.sendKeys(Keys.TAB);
			Log.info("Selected visit start minutes as: "+visitStartMinutes);

		}else{
			Log.info("Failed to select Start minutes...");
		}		
	}

	//Method to set visit end date
	public void setVisitEndDate(String visitEndDateVal) throws AWTException {
		CommonActions.waitForElementToBeClickable(driver, visitEndDateElement);
		if(visitEndDateElement.isDisplayed()){
			CommonActions.javaScriptClick(driver, visitEndDateElement); 
			DatePicker.datePcikerByJS(driver, visitEndDateElement, visitEndDateVal);
			Log.info("Selected end date is: "+visitEndDateVal);

		}else{ Log.info("Failed to select End date..."); }

	}	

	//Method to set visit start hours
	public void setVisitEndHours(String visitEndHours){
		CommonActions.waitForElementToBeVisible(driver, visitEndHourElement);
		if(visitEndHourElement.isDisplayed()){
			visitEndHourElement.click();
			visitEndHourElement.clear();
			visitEndHourElement.sendKeys(visitEndHours);
			visitEndHourElement.sendKeys(Keys.TAB);
			Log.info("Selected visit end hours as: "+visitEndHours);

		}else{
			Log.info("Failed to select End hours...");
		}	
	}

	//Method to set visit start minutes
	public void setVisitEndMinutes(String visitEndinutes){
		CommonActions.waitForElementToBeVisible(driver, visitEndMinutesElement);
		if(visitEndMinutesElement.isDisplayed()){
			visitEndMinutesElement.click();
			visitEndMinutesElement.clear();
			visitEndMinutesElement.sendKeys(visitEndinutes);
			visitEndMinutesElement.sendKeys(Keys.TAB);
			Log.info("Selected visit end minutes as: "+visitEndinutes);

		}else{
			Log.info("Failed to select End minutes...");
		}		
	}

	//Method to set visit notes
	public void setAddNewVisitNotes(String addNewVisitNotes){
		CommonActions.waitForElementToBeClickable(driver, addVisitNotesTextBoxElement);
		if(addVisitNotesTextBoxElement.isDisplayed()){
			addVisitNotesTextBoxElement.click();	
			addVisitNotesTextBoxElement.sendKeys(addNewVisitNotes);
			Log.info("Entered Notes is: "+addNewVisitNotes);
		}else{
			Log.info("Failed to entered Notes in text box...");
		}
	}
	//Method to click add button in Add New visit dialog window
	public void clickAddNewVisitDialogAddButton() {
		CommonActions.waitForElementToBeClickable(driver, addNewVisitDialogAddButtonElement);
		if(addNewVisitDialogAddButtonElement.isEnabled() && addNewVisitDialogAddButtonElement.isDisplayed()){
			CommonActions.javaScriptClick(driver, addNewVisitDialogAddButtonElement);
			Log.info("Add new visit button is clicked...");
			//CommonActions.waitForElementToBeVisible(driver, expandPanelElement);
			//expandPanelElement.click();
		}else{
			Log.info("Failed to clicked add new visit button..");
		}
	}

	//Method to create new visit
	public void createAddNewVisit(String selectJobType, String selectWearerGroup, String addNewVisitWearerNUI, String addNewVisitWearer,
			String assignedFieldOfficer,String visitStartDateVal, String visitStartHours, String visitStartMinutes,
			String visitEndDateVal, String visitEndHours, String visitEndMinutes, String addVisitNotes) throws AWTException{
		setJobtype(selectJobType);
		setWearerGroup(selectWearerGroup);
		setAddNewVisitWearer(addNewVisitWearerNUI, addNewVisitWearer);
		setAssignedFieldOfficer(assignedFieldOfficer);
		setVisitStartDate(visitStartDateVal);
		setVisitStartHours(visitStartHours);
		setVisitStartMinutes(visitStartMinutes);
		setVisitEndDate(visitEndDateVal);
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
			Log.info("Is scheduled visit title is displayed..."+scheduledVisitGridTitleElement.isDisplayed());
			return scheduledVisitGridTitleElement.isDisplayed();
		}
		else{
			Log.info(scheduledVisitGridTitleElement.getText()
					+" : Failed to displayed visit title...");
			return false;
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

	//Method to click open visit in scheduled page 
	public void clickOpenVisitInScheduledPage(String addNewVisitWearerNUI ) {
		WebElement addVisitWearerNUIElement = driver.findElement(By.xpath("//div[@class='x-grid-cell-inner ' and contains(text(),"
				+ " '"+addNewVisitWearerNUI+"')]"));
		CommonActions.waitForElementToBeClickable(driver, addVisitWearerNUIElement);
		if(addVisitWearerNUIElement.isDisplayed()) {
			CommonActions.javaScriptClick(driver, addVisitWearerNUIElement);
			CommonActions.waitForElementToBeClickable(driver, scheduledOpenVisitElement);
			if(scheduledOpenVisitElement.isDisplayed()) {
				CommonActions.javaScriptClick(driver, scheduledOpenVisitElement);
				Log.info("Open "+addNewVisitWearerNUI + " NUI wearer visit");
			}
		}
		else{
			Log.info("Failed to clicked on " +addNewVisitWearerNUI + " visit..!");
		}	
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


