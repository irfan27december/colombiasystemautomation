package com.buddi.hdportal.pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.buddi.colombia.common.CommonActions;
import com.buddi.colombia.testdata.StringConstants;
import com.buddi.colombia.testdata.TestData;
import com.buddi.colombia.utilities.DateToString;
import com.mysql.cj.x.protobuf.MysqlxExpect.Open.Condition.Key;

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
	private WebElement newAlertsPanelTitleElement;
	@FindBy(xpath = "//span[contains(text(),'Regional')]") 
	private WebElement regionalColumnElement;

	//In Progress Alerts elements
	/*@FindBy(xpath = "//div[contains(text(),'In Progress Alerts')]") 
	private WebElement inProgressAlertsElement;
	@FindBy(xpath = "//div[contains(text(),'In Progress Alerts')]") 
	private WebElement inProgressAlertsPanelTitleElement;*/

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
		CommonActions.waitForElementToBeVisible(driver, newAlertsElement);
		newAlertsElement.click();
		System.out.println("New Alerts menu is clicked...");
	}

	// Method to verify new alerts panel title
	public String verifyNewAlertsPanelTitle() {
		CommonActions.waitForElementToBeVisible(driver, newAlertsPanelTitleElement);
		System.out.println(newAlertsPanelTitleElement.getText()+" new alerts panel title is displayed...");
		return newAlertsPanelTitleElement.getText();
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
		/*WebElement columnElement = driver.findElement(By.xpath("//div[@class='x-column-header-inner x-leaf-column-header']/following::span[contains(text(),'"+columnName+"')]['"+getXpathCount(columnName)+"']"));		
		System.out.println("columnElement       "+columnElement);*/
		//WebElement columnElement = driver.findElement(By.xpath("//span[contains(text(),'"+columnName+"')]"));
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
		if(toolBarAddAlertButton.isEnabled()){
			toolBarAddAlertButton.click();
			System.out.println("Tools bar add button is clicked...");
		}else{
			System.out.println("Tools bar add button is not clicked...");
		}
	}

	//Method to verify Add New Alert dialog title
	public String verifyAddNewAlertDialogTitle(){
		CommonActions.waitForElementToBeVisible(driver, addNewAlertDialogTitleElement);
		if(addNewAlertDialogTitleElement.isEnabled()){
			System.out.println("Add New Alert dialog box is displayed...");
			return addNewAlertDialogTitleElement.getText();
		}else{
			System.out.println("Add New Alert dialog box is not displayed...");
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
		//return false;
	}



	//Method to set alert wearer name
	//9010203040 ~ Pacífica Rosalía Huerta, Automation-
	//NUI: 9010203040
	//CC: 50607080
	public void setAlertWearerName(String alertWearerName) {
		CommonActions.waitForElementToBeVisible(driver, alertWearerDropDown);
		alertWearerDropDown.click();
		alertWearerDropDown.clear();
		alertWearerDropDown.sendKeys(alertWearerName);
		CommonActions.autoSuggestionMethod(driver);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
	}

	//Method to set alert severity
	public void setAlertSeverity(String alertSeverity) {
		// getting the list of elements with the xpath
		List<WebElement> listOfComboBox = driver.findElements(By.xpath("//input[@name='alertseverity']"));
		int numberOfComboBoxOptions = listOfComboBox.size();
		// Iterating through the list selecting the desired option
		for( int j = 0; j< listOfComboBox.size(); j++){
			// if the option is By Subject click that option
			if( listOfComboBox.get(j).getText().equals(alertSeverity)){
				System.out.println("opt.get(j)   "+listOfComboBox.get(j));
				listOfComboBox.get(j).click();
				break;
			}
		}
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
	}

	//Method to set alert start date
	public void setAlertStartDate(String alertStartDateFormat) {
		CommonActions.waitForElementToBeVisible(driver, startDateField);
		startDateField.click();
		startDateField.clear();
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		System.out.println("DateToString.returnDate(alertStartDateFormat)     "+DateToString.returnDate(alertStartDateFormat));
		startDateField.sendKeys(DateToString.returnDate(alertStartDateFormat));
		//startDateField.sendKeys(Keys.TAB);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}

	//Method to set alert start hours
	public void setAlertStartHours(String alertStartHours){
		CommonActions.waitForElementToBeVisible(driver, startHourField);
		startHourField.click();
		startHourField.clear();
		startHourField.sendKeys(alertStartHours);
		startHourField.sendKeys(Keys.TAB);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}


	//Method to set alert start minutes
	public void setAlertStartMinutes(String alertStartMinutes){
		CommonActions.waitForElementToBeVisible(driver, startMinutesField);
		startMinutesField.click();
		startMinutesField.clear();
		startMinutesField.sendKeys(alertStartMinutes);
		startMinutesField.sendKeys(Keys.TAB);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}

	//Method to set alert notes
	public void setAlertNotes(String alertNotes){
		CommonActions.waitForElementToBeVisible(driver, notesField);
		notesField.click();
		notesField.clear();
		notesField.sendKeys(alertNotes);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}


	//Method to click add button in Add New Alert dialog
	public void clickAddNewAlertButton() {
		CommonActions.waitForElementToBeVisible(driver, addNewAlertButton);
		if(addNewAlertButton.isEnabled()){
			addNewAlertButton.click();
			System.out.println("Add new alert button is clicked in Add New Alert dialog...");
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		}else{
			System.out.println("Failed to click add new alert button is clicked in Add New Alert dialog...");
		}
	}

	//Method to create new alert
	public void createNewAlert(String alertWearerName, String alertSeverity, String alertStartDateFormat, String alertStartHours, 
			String alertStartMinutes, String alertNotes){
		setAlertWearerName(alertWearerName);
		setAlertSeverity(alertSeverity);
		setAlertStartDate(alertStartDateFormat);
		setAlertStartHours(alertStartHours);
		setAlertStartMinutes(alertStartMinutes);
		setAlertNotes(alertNotes);
		clickAddNewAlertButton();
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
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
	/*

	// Method to click In Progress Alerts menu option
	public void clickInProgressAlertstMenu() {
		CommonActions.waitForElementToBeVisible(driver, inProgressAlertsElement);
		inProgressAlertsElement.click();
		System.out.println("In Progress Alerts menu is clicked...");
	}

	// Method to verify in progress alerts panel title
	public String verifyInProgressAlertsPanelTitle() {
		CommonActions.waitForElementToBeVisible(driver, inProgressAlertsElement);
		System.out.println(inProgressAlertsElement.getText()+" in progress alerts panel title is displayed...");
		return inProgressAlertsElement.getText();
	}
	 */
	//Method to navigate to New Alerts
	public void navigateToNewAlertsOption(){
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.navigate().refresh();
		CommonActions.waitForElementToBeClickable(driver, newAlertsElement);
		CommonActions.clickElementToHandleStaleElementException(newAlertsElement);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);	
	}
}
