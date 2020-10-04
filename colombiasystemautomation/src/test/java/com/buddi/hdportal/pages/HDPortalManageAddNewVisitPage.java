package com.buddi.hdportal.pages;

import java.awt.AWTException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.buddi.colombia.common.CommonActions;
import com.buddi.colombia.testdata.TestData;
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

	//Using FindBy for locating elements

	//Web elements related to pending visits
	//visitsMenuElement
	@FindBy(xpath = "//div[@class='x-treelist-item-text' and text()='Visits']") 
	private WebElement visitsMenuElement;
	//pendingVisitsElement
	@FindBy(xpath = "//div[@class='x-treelist-item-text' and @id='ext-element-31' and text()='Pending']") 
	private WebElement pendingVisitsElement;
	//pendingVisitsGridTitleElement
	@FindBy(xpath = "//div[@class='x-title-text x-title-text-default-framed x-title-item' and text()='Pending Visits']") 
	private WebElement pendingVisitsGridTitleElement;

	//Add Button
	@FindBy(xpath = ".//span[@class = 'x-btn-inner x-btn-inner-default-toolbar-small' and contains(text(), 'Add')]")
	private WebElement pendingVisitsAddToolBarButtonElement;	

	// AddNewVisit windowtitle
	@FindBy(xpath = "//div[@class = 'x-title-text x-title-text-default x-title-item' and contains(text(), 'Add New Visit')]")
	//".//div[@class = 'x-title-text x-title-text-default x-title-item'][starts-with(@id,'visitsNewAddWindow')]") 
	private WebElement addNewVisitDialogTitleElement;

	//Elements in Add New visit window 
	@FindBy(xpath ="//input[@name='job_type_id']") 
	private WebElement jobtypeDropDown;


	@FindBy(xpath = "//input[@name='visitwearergroup']") 
	private WebElement wearerGroupDropDown;


	@FindBy(xpath = "//input[@name='visitwearer']") 
	private WebElement addVisitWearer;

	@FindBy(xpath = "//input[@name='assignedofficer']")
	private WebElement assignedfieldOfficerDropdown;

	@FindBy(xpath = "//input[@name='startdate']") 
	private WebElement startDateField;
	@FindBy(xpath = "//input[@name='starthour']") 
	private WebElement startHourField;
	@FindBy(xpath = "//input[@name='startmin']") 
	private WebElement startMinutesField;

	@FindBy(xpath = "//input[@name='enddate']")
	private WebElement endDateField;
	@FindBy(xpath = "//input[@name='endhour']") 
	private WebElement endHourField;
	@FindBy(xpath = "//input[@name='endmin']") 
	private WebElement endMinutesField;

	@FindBy(xpath = "//textarea[@name='notes']") 
	private WebElement notesField;

	@FindBy(xpath = "//span[@class='x-btn-inner x-btn-inner-default-small' and contains(text(),'Add')]")
	private WebElement addNewVisitButton;

	@FindBy(xpath= "//div[@class='x-treelist-item-text' and contains(text(),'Scheduled')]")
	private WebElement scheduledVisitsElement;

	@FindBy(xpath="//div[@class='x-title-text x-title-text-default-framed x-title-item'][starts-with(text(),'Visit - ')]")
	private WebElement addNewVisitDetailPageElement;

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
	public void clickPendingOption() {
		CommonActions.waitForElementToBeVisible(driver, pendingVisitsElement);
		if(pendingVisitsElement.isDisplayed()){
			pendingVisitsElement.click();
			System.out.println("Pending visits sub menu is clicked...");
		}else{
			System.out.println("Pending visits sub menu is not displayed...");
		}
	}

	// Method to verify Pending Visits Grid title
	public void verifyPendingVisitsGridTitle() {
		CommonActions.waitForElementToBeVisible(driver, pendingVisitsGridTitleElement);
		if(pendingVisitsGridTitleElement.isDisplayed()){
			System.out.println(pendingVisitsGridTitleElement.getText()+" : pending visit grid title is verified ...");
			return;
		}else{
			System.out.println("Pending visits sub menu is not displayed...");
		}	
	}	

	//Method to click Add new visit tool bar button
	public void clickAddNewVisitToolBarButton() throws InterruptedException, AWTException{
		//CommonActions.zoomOut();
		WebElement collapsePanelElement = driver.findElement(By.xpath("//div[@class='x-tool-tool-el x-tool-img x-tool-collapse-left ']"));
		collapsePanelElement.click();
		CommonActions.waitForElementToBeVisible(driver, pendingVisitsAddToolBarButtonElement);//Irfan: Added this line
		//CommonActions.javaScriptClick(driver, pendingVisitsAddToolBarButtonElement); //Irfan: This element must be clicked once it is displayed
		if(pendingVisitsAddToolBarButtonElement.isDisplayed()){ //Irfan: Here you need to check add tool bar button and not pendingVisitsElement
			CommonActions.javaScriptClick(driver, pendingVisitsAddToolBarButtonElement);
			System.out.println("Pending Visits Add Tool Bar Button is clicked...");
		}else{
			System.out.println("Pending Visits Add Tool Bar Button is not displayed...");
		}

	}

	//Method to verify Add New Visit dialog window title
	public void verifyAddNewVisitDialogTitle() throws AWTException{
		//CommonActions.zoomIn();
		CommonActions.waitForElementToBeVisible(driver, addNewVisitDialogTitleElement);
		if(addNewVisitDialogTitleElement.isDisplayed()){
			System.out.println(addNewVisitDialogTitleElement.getText()+" : Add New Visit title is verified ...");
			return; // Irfan: This method is not returning anything, no need to have
		}else{
			System.out.println("Add New visit dialog title is not displayed...");
		}
	}

	//Method to select jobtype
	public void setJobtype(String selectJobType ) {
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		//CommonActions.javaScriptClick(driver, jobtypeDropDown);//Irfan: This should be called once the element is displayed
		CommonActions.waitForElementToBeClickable(driver, jobtypeDropDown);
		if(jobtypeDropDown.isDisplayed()){
			CommonActions.javaScriptClick(driver, jobtypeDropDown);
			System.out.println("Select Job Type dropdown is clicked ...");
			jobtypeDropDown.sendKeys(selectJobType);
			CommonActions.autoSuggestionMethod(driver);
		}
		else{
			System.out.println("Select Job Type dropdown is not displayed ...");
		}
		/*jobtypeDropDown.click();
		CommonActions.waitForElementToBeClickable(driver, jobtypeDropDown);
		jobtypeDropDown.sendKeys(selectJobType);
		CommonActions.autoSuggestionMethod(driver);*/ //Irfan:Not required, as this should be part of if-else
	}

	//Method to select the Wearer Group
	public void setWearerGroup(String selectWearerGroup) {
		CommonActions.waitForElementToBeClickable(driver, wearerGroupDropDown);
		//CommonActions.javaScriptClick(driver, wearerGroupDropDown);//Irfan: This should be called once the element is displayed
		if(wearerGroupDropDown.isDisplayed()){
			CommonActions.javaScriptClick(driver, wearerGroupDropDown);
			wearerGroupDropDown.sendKeys(selectWearerGroup);
			CommonActions.autoSuggestionMethod(driver);
			System.out.println("WearerGroup dropdown is clicked...");
		}else{
			System.out.println("WearerGroup dropdown is not displayed...");
		}
		/*wearerGroupDropDown.click();
		wearerGroupDropDown.sendKeys(selectWearerGroup);
		CommonActions.autoSuggestionMethod(driver);*///Irfan:Not required, as this should be part of if-else
	}

	//Method to select the Wearer 
	public void setAddNewVisitWearer(String addNewVisitWearerNUI, String addNewVisitWearer) {
		CommonActions.waitForElementToBeVisible(driver, addVisitWearer);
		if(addVisitWearer.isDisplayed()){
			addVisitWearer.click();
			String str = addNewVisitWearerNUI+addNewVisitWearer;//Irfan append NUI and wearer name and pass it
			addVisitWearer.sendKeys(addNewVisitWearerNUI, addNewVisitWearer);// Either send wearer NUI + wearer name or just wearer name
			CommonActions.autoSuggestionMethod(driver);
			System.out.println("Select Wearer dropdown is clicked...");
		}else{
			System.out.println("Select Wearer dropdown is not displayed...");
		}
		/*addVisitWearer.click();
		addVisitWearer.sendKeys(addNewVisitWearerNUI, addNewVisitWearer);
		CommonActions.autoSuggestionMethod(driver);*/ //Irfan:Not required, as this should be part of if-else
	}
	//Method to assinged fieldofficer
	public void setAssignedFieldOfficer(String assignedFieldOfficer) {
		CommonActions.waitForElementToBeClickable(driver, assignedfieldOfficerDropdown);
		//CommonActions.javaScriptClick(driver, assignedfieldOfficerDropdown);  //Irfan: This should be clicked once element is displayed
		if(assignedfieldOfficerDropdown.isDisplayed()){
			CommonActions.javaScriptClick(driver, assignedfieldOfficerDropdown);
			//assignedfieldOfficerDropdown.click(); //Irfan: this is not required as you are clicking element using JS
			System.out.println("assigned field Officer dropdown is clicked...");
			assignedfieldOfficerDropdown.sendKeys(assignedFieldOfficer);
			CommonActions.autoSuggestionMethod(driver);
		}else{
			System.out.println("assigned field Officer dropdown is not displayed...");
		}
		/*assignedfieldOfficerDropdown.sendKeys(assignedFieldOfficer);
		CommonActions.autoSuggestionMethod(driver);*/ //Irfan:Not required, as this should be part of if-else

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
			return; //Irfan: This is not required as we are not returning anything
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
			return;//Irfan: This is not required as we are not returning anything
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
			return;//Irfan: This is not required as we are not returning anything
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
			return;//Irfan: This is not required as we are not returning anything
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
			return;//Irfan: This is not required as we are not returning anything
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
			return;//Irfan: This is not required as we are not returning anything
		}		
	}

	//Method to set visit notes
	public void setAddNewVisitNotes(String addNewVisitNotes){
		CommonActions.waitForElementToBeVisible(driver, notesField);
		if(notesField.isDisplayed()){
			notesField.click();	
			notesField.sendKeys(addNewVisitNotes);
			System.out.println("Notes text box is clicked..");
		}else{
			System.out.println("Notes text box failed to clicked...");
		}
		//notesField.sendKeys(addNewVisitNotes);//Irfan this should be part of if-else
	}

	//Method to click add button in Add New visit dialog window
	public void clickAddNewVisitButton() {
		CommonActions.waitForElementToBeVisible(driver, addNewVisitButton);//Irfan: Added this command
		//CommonActions.javaScriptClick(driver, addNewVisitButton);//Irfan: This should be part of if-else
		if(addNewVisitButton.isEnabled() && addNewVisitButton.isDisplayed()){
			CommonActions.javaScriptClick(driver, addNewVisitButton);
			System.out.println("Add new visit button is clicked..");
			WebElement expandPanelElement = driver.findElement(By.xpath("//div[@class='x-tool-tool-el x-tool-img x-tool-expand-right ']"));
			CommonActions.waitForElementToBeVisible(driver, expandPanelElement);
			expandPanelElement.click();//Irfan: Define it at the top where other elements are defined
		}else{
			System.out.println("Failed to click add new visit button..");
		}
		/*addNewVisitButton.click();*///Irfan: This should be part of if-else
	}

	//Method to create new visit
	public void createNewAddVisit(String selectJobType, String selectWearerGroup, String addNewVisitWearerNUI, String addNewVisitWearer,
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
		clickAddNewVisitButton();
	}

	// Method for viewing created Add New visit wearer details
	public void verifyCreatedAddNewVisit() {	
		CommonActions.waitForElementToBeVisible(driver, addNewVisitDetailPageElement);//Irfan: Added this command
		//CommonActions.javaScriptClick(driver, addNewVisitDetailPageElement);//Irfan: This should be part of if-else
		if(addNewVisitDetailPageElement.isDisplayed()) {
			CommonActions.javaScriptClick(driver, addNewVisitDetailPageElement);
			System.out.println(addNewVisitDetailPageElement.getText()+""
					+ " -: title is displayed...\n Add New Visit created successfully...");
		}
	}

	//Method to navigate to Shedulded Page
	public void navigateToSheduledVisitsOption(){ // Irfan: Correct the spelling of Shedulded, it should be Scheduled
		CommonActions.waitForElementToBeVisible(driver, scheduledVisitsElement);//Irfan: Added this command
		//CommonActions.javaScriptClick(driver, scheduledVisitsElement);//Irfan: This should be part of if-else
		if(scheduledVisitsElement.isDisplayed()) {
			CommonActions.javaScriptClick(driver, scheduledVisitsElement);
			System.out.println("Scheduled Visits menu is clicked...");
		}
		else{
			System.out.println("Failed to clicked on Scheduled Visits..");
		}
		//scheduledVisitsElement.click();//Irfan: This is not required as you are using JS to click element
	}	

	//Method to check NUI in In Shedulded visit 
	public boolean verifyAddedNewVisitWithWearerNUIInSheduldedListPage(String addNewVisitWearerNUI){ 
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
	}	//Irfan: If this method is returning some value then the method name must be like: isWearerNUIDisplayedInScheduledListPage
}


