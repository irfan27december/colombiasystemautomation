package com.buddi.hdportal.pages;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.buddi.colombia.common.CommonActions;
import com.buddi.colombia.testdata.StringConstants;
import com.buddi.colombia.utilities.DateToString;

/**
 * @author irfan
 *
 */

public class HDPortalManageNewManualAlertsPage {
	WebDriver driver;

	//Constructor
	public HDPortalManageNewManualAlertsPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Using FindBy for locating elements
	//Web elements related to manage alerts
	@FindBy(xpath = "//div[contains(text(),'New Alerts')]") 
	private WebElement newAlertsElement;
	@FindBy(xpath = "//div[contains(text(),'New Alerts')]") 
	private WebElement newAlertsGridTitleElement;
	@FindBy(xpath = "//span[contains(text(),'Regional')]") 
	private WebElement regionalColumnElement;

	//Web elements related to add alert
	@FindBy(xpath = "//span/span[contains(@class,'x-btn-inner x-btn-inner-default-toolbar-small') and contains(text(), 'Add')]") 
	private WebElement toolBarAddAlertButton;
	@FindBy(xpath = "//div[contains(text(),'Add New Alert')]") 
	private WebElement addNewAlertDialogTitleElement;
	@FindBy(xpath = "//input[@name='alertwearer']") 
	private WebElement alertWearerDropDown;
	@FindBy(xpath = "//input[@name='alertseverity']") 
	private WebElement alertSeverityDropDown;
	@FindBy(xpath = "//input[@name='startdate']") 
	private WebElement startDateField;
	@FindBy(xpath = "//input[@name='starthour']") 
	private WebElement startHourField;
	@FindBy(xpath = "//input[@name='startmin']") 
	private WebElement startMinutesField;
	@FindBy(xpath = "//textarea[@name='notes']") 
	private WebElement notesField;
	@FindBy(xpath = "//span[@class='x-btn-inner x-btn-inner-default-small' and contains(text(),'Add')]")
	private WebElement addNewAlertButton;

	// Method to click LHS menu option
	public void clickNewAlertstMenu() {
		//driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		driver.navigate().refresh();
		CommonActions.waitForElementToBeVisible(driver, newAlertsElement);
		if(newAlertsElement.isDisplayed()){	
			CommonActions.waitForElementToBeClickable(driver, newAlertsElement);
			newAlertsElement.click();
			System.out.println("New Alerts menu is clicked...");
		}else{
			System.out.println("New Alerts menu is not clicked...");
		}
	}

	// Method to verify new alerts panel title
	public String verifyNewAlertsGridTitle() {
		CommonActions.waitForElementToBeVisible(driver, newAlertsGridTitleElement);
		System.out.println(newAlertsGridTitleElement.getText()+" grid title is displayed...");
		return newAlertsGridTitleElement.getText();
	}

	//Method to get count of alerts page grid columns
	public int getXpathCount(String columnName){
		List<WebElement> locatedElements = driver.findElements(By.xpath("//div[@class='x-column-header-inner x-leaf-column-header']/following::span[contains(text(),'"+columnName+"')]"));
		int count = 0;
		for(WebElement locatedElement:locatedElements){
			count++;
		}
		System.out.println("Count    "+count);
		return count;
	}

	//Method accepts column name and returns column element
	public WebElement returnColumnNameElement(String columnName){
		WebElement columnElement = driver.findElement(By.xpath("//div[@class='x-column-header-text']/following::span[contains(text(),'"+columnName+"')]"));
		return columnElement;	
	}

	public LinkedList<String> returnNewAlertsPageColumns(){
		LinkedList<String> list = new LinkedList<String>();
		//list.add(StringConstants.ALERTS_PAGE_OPENALERT_COLUMN);
		list.add(StringConstants.ALERTS_PAGE_STATUS_COLUMN);
		list.add(StringConstants.ALERTS_PAGE_DATETIME_COLUMN);
		list.add(StringConstants.ALERTS_PAGE_REGIONAL_COLUMN);
		list.add(StringConstants.ALERTS_PAGE_WEARERGROUP_COLUMN);
		list.add(StringConstants.ALERTS_PAGE_NUI_COLUMN);
		//list.add(StringConstants.ALERTS_PAGE_WEARER_COLUMN);
		list.add(StringConstants.ALERTS_PAGE_DEVICE_COLUMN);
		list.add(StringConstants.ALERTS_PAGE_RULETYPE_COLUMN);
		list.add(StringConstants.ALERTS_PAGE_RULENAME_COLUMN);
		list.add(StringConstants.ALERTS_PAGE_LOCKED_COLUMN);
		return list;
	}

	//Method to click tools bar add button
	public void clickToolsBarAddAlertButton() {
		CommonActions.waitForElementToBeVisible(driver, toolBarAddAlertButton);
		if(toolBarAddAlertButton.isDisplayed()){
			toolBarAddAlertButton.click();
			System.out.println("Tools bar add button is clicked to create new alert...");
		}else{
			System.out.println("Tools bar add button is not clicked to create new alert...");
		}
	}

	//Method to verify Add New Alert dialog title
	public String verifyAddNewAlertDialogTitle(){
		CommonActions.waitForElementToBeVisible(driver, addNewAlertDialogTitleElement);
		if(addNewAlertDialogTitleElement.isEnabled()){
			System.out.println("Add New Alert dialog box is displayed with title..."+addNewAlertDialogTitleElement.getText());
			return addNewAlertDialogTitleElement.getText();
		}else{
			System.out.println("Add New Alert dialog box is not displayed with title..."+addNewAlertDialogTitleElement.getText());
		}
		return null;
	}

	//Method to check if Add New Alert dialog is displayed
	public boolean isAddNewAlertDialogDisplayed(){
		CommonActions.waitForElementToBeVisible(driver, addNewAlertDialogTitleElement);
		if(addNewAlertDialogTitleElement.isDisplayed()){
			System.out.println("Add New Alert dialog box is displayed...");
			return true;
		}else{
			System.out.println("Add New Alert dialog box is not displayed...");
			return false;
		}
	}

	//Method to set alert wearer name
	public void setAlertWearerName(String alertWearerName) throws InterruptedException {
		CommonActions.waitForElementToBeVisible(driver, alertWearerDropDown);
		if(alertWearerDropDown.isDisplayed()){
			alertWearerDropDown.click();
			System.out.println("Wearer drop down is displayed...");
			//driver.manage().timeouts().implicitlyWait(50000, TimeUnit.MILLISECONDS);
			//Not ideal to use Thread.sleep, will check for alternate solution for this
			Thread.sleep(3000);
			alertWearerDropDown.sendKeys(alertWearerName);
			CommonActions.autoSuggestionMethod(driver);
		}else{
			System.out.println("Wearer drop down is not displayed...");
		}		
	}

	//Method to set alert severity
	public void setAlertSeverity(String alertSeverity) throws InterruptedException {
		if(alertSeverityDropDown.isDisplayed()){
			alertSeverityDropDown.click();
			System.out.println("Severity drop down is displayed...");
			alertSeverityDropDown.sendKeys(Keys.ARROW_DOWN);
			if(alertSeverity.equalsIgnoreCase("high")){
				alertSeverityDropDown.sendKeys(Keys.ARROW_UP);
				alertSeverityDropDown.sendKeys(Keys.ARROW_UP);
				alertSeverityDropDown.sendKeys(Keys.ARROW_UP);
				alertSeverityDropDown.sendKeys(Keys.ARROW_UP);
				alertSeverityDropDown.sendKeys(Keys.ENTER);				
			}else if(alertSeverity.equalsIgnoreCase("medium")){
				alertSeverityDropDown.sendKeys(Keys.ARROW_UP);
				alertSeverityDropDown.sendKeys(Keys.ARROW_UP);
				alertSeverityDropDown.sendKeys(Keys.ARROW_UP);
				alertSeverityDropDown.sendKeys(Keys.ENTER);		
			}else if(alertSeverity.equalsIgnoreCase("low")){
				alertSeverityDropDown.sendKeys(Keys.ARROW_UP);
				alertSeverityDropDown.sendKeys(Keys.ARROW_UP);
				alertSeverityDropDown.sendKeys(Keys.ENTER);				
			}else if(alertSeverity.equalsIgnoreCase("standard")){	
				//alertSeverityDropDown.sendKeys(Keys.ESCAPE);	
				alertSeverityDropDown.sendKeys(Keys.ARROW_DOWN);
				alertSeverityDropDown.sendKeys(Keys.ARROW_DOWN);
				alertSeverityDropDown.sendKeys(Keys.ARROW_DOWN);
				alertSeverityDropDown.sendKeys(Keys.ENTER);
			}else{
				alertSeverityDropDown.sendKeys(Keys.TAB);
			}
			alertSeverityDropDown.sendKeys(Keys.TAB);
			//driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		}else{
			System.out.println("Severity drop down is not displayed...");
		}
		
	}

	//Method to set alert start date
	public void setAlertStartDate(String alertStartDateFormat) {
		CommonActions.waitForElementToBeVisible(driver, startDateField);
		if(startDateField.isDisplayed()){
			startDateField.click();
			startDateField.clear();
			//System.out.println("DateToString.returnDate(alertStartDateFormat)     "+DateToString.returnDate(alertStartDateFormat));
			startDateField.sendKeys(DateToString.returnDate(alertStartDateFormat));
			//startDateField.sendKeys(Keys.TAB);
		}else{
			System.out.println("Date field is not displayed...");
		}		
	}

	//Method to set alert start hours
	public void setAlertStartHours(String alertStartHours){
		CommonActions.waitForElementToBeVisible(driver, startHourField);
		if(startHourField.isDisplayed()){
			startHourField.click();
			startHourField.clear();
			startHourField.sendKeys(alertStartHours);
			startHourField.sendKeys(Keys.TAB);
			//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		}else{
			System.out.println("Hour field is not displayed...");
		}		
	}


	//Method to set alert start minutes
	public void setAlertStartMinutes(String alertStartMinutes){
		CommonActions.waitForElementToBeVisible(driver, startMinutesField);
		if(startMinutesField.isDisplayed()){
			startMinutesField.click();
			startMinutesField.clear();
			startMinutesField.sendKeys(alertStartMinutes);
			startMinutesField.sendKeys(Keys.TAB);
		}else{
			System.out.println("Minutes field is not displayed...");
		}		
	}

	//Method to set alert notes
	public void setAlertNotes(String alertNotes){
		CommonActions.waitForElementToBeVisible(driver, notesField);
		if(notesField.isDisplayed()){
			notesField.click();
			notesField.clear();
			notesField.sendKeys(alertNotes);
			//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		}else{
			System.out.println("Notes field is not displayed...");
		}		
	}


	//Method to click add button in Add New Alert dialog
	public void clickAddNewAlertButton() {
		CommonActions.waitForElementToBeVisible(driver, addNewAlertButton);
		if(addNewAlertButton.isEnabled()){
			addNewAlertButton.click();
			System.out.println("Add new alert button is clicked in Add New Alert dialog...");
		}else{
			System.out.println("Failed to click add new alert button is clicked in Add New Alert dialog...");
		}
	}

	//Method to create new alert
	public void createNewAlert(String alertWearerName, String alertSeverity, String alertStartDateFormat, String alertStartHours, 
			String alertStartMinutes, String alertNotes) throws InterruptedException{
		setAlertWearerName(alertWearerName);
		setAlertSeverity(alertSeverity);
		setAlertStartDate(alertStartDateFormat);
		setAlertStartHours(alertStartHours);
		setAlertStartMinutes(alertStartMinutes);
		setAlertNotes(alertNotes);
		clickAddNewAlertButton();
	}

	//Method to check NUI and wearre name displayed in alerts detail page  
	public boolean isWearerNameNUIDisplayedInAlertsDetailPage(String wearerName, String wearerNUI){		
		WebElement wearerNameElement = driver.findElement(By.xpath("//a[@class='wearerlink' and text()='"+wearerName+"']"));
		WebElement wearerNUIElement = driver.findElement(By.xpath("//table[3]/tbody/tr/td[6]/b/i[text()='"+wearerNUI+"']"));
		CommonActions.waitForElementToBeVisible(driver, wearerNUIElement);
		if(wearerNUIElement.isDisplayed() && wearerNameElement.isDisplayed()){
			System.out.println("Wearer with name "+wearerName+" and NUI "+wearerNUI+ " is displayed in alerts detail page...");	
			return true;
		}else{
			System.out.println("Wearer with name "+wearerName+" and NUI "+wearerNUI+ " is not displayed in alerts detail page...");
			return false;
		}
	}

	//Method to check NUI in In Progress alerts page 
	public boolean isWearerNUIDisplayedInInProgressListPage(String wearerNUI){
		WebElement wearerNUIElement = driver.findElement(By.xpath("//div[contains(text(),'"+wearerNUI+"')]"));
		CommonActions.waitForElementToBeVisible(driver, wearerNUIElement);
		if(wearerNUIElement.isDisplayed()){
			System.out.println("Wearer with NUI "+wearerNUI+ " is displayed in In Progress Alerts list page...");	
			return true;
		}else{
			System.out.println("Wearer with NUI "+wearerNUI+ " is not displayed in In Progress Alerts list page...");
			return false;
		}
	}

	//Method to check wearer name in In Progress alerts page 
	public boolean isWearerNameDisplayedInInProgressListPage(String wearerName){
		WebElement wearerNameElement = driver.findElement(By.xpath("//div[contains(text(),'"+wearerName+"')]"));
		CommonActions.waitForElementToBeVisible(driver, wearerNameElement);
		if(wearerNameElement.isDisplayed()){
			System.out.println("Wearer with name "+wearerName+ " is displayed in In Progress Alerts list page...");	
			return true;
		}else{
			System.out.println("Wearer with name "+wearerName+ " is not displayed in In Progress Alerts list page...");
			return false;
		}
	}


	//Method to navigate to New Alerts
	public void navigateToNewAlertsOption(){
		driver.navigate().refresh();
		CommonActions.waitForElementToBeClickable(driver, newAlertsElement);
		CommonActions.clickElementToHandleStaleElementException(newAlertsElement);
	}
}
