package com.buddi.hdportal.pages;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.buddi.colombia.common.CommonActions;
import com.buddi.colombia.testdata.StringConstants;

/**
 * @author irfan
 *
 */

public class HDPortalManageInProgressAlertsPage {
	WebDriver driver;

	//Constructor
	public HDPortalManageInProgressAlertsPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Using FindBy for locating elements
	//Web elements related to manage alerts
	@FindBy(xpath = "//span[contains(text(),'Regional')]") 
	private WebElement regionalColumnElement;

	//In Progress Alerts elements
	@FindBy(xpath = "//div[contains(text(),'In Progress Alerts')]") 
	private WebElement inProgressAlertsElement;
	@FindBy(xpath = "//div[contains(text(),'In Progress Alerts')]") 
	private WebElement inProgressAlertsPanelTitleElement;
	@FindBy(xpath = "//input[@class='x-form-field x-form-text x-form-text-default ']") 
	private WebElement selectWearerComboBox;
	@FindBy(xpath = "//input[@class='x-form-field x-form-empty-field x-form-empty-field-default x-form-text x-form-text-default ']") 
	private WebElement startDateField;
	@FindBy(xpath = "/html/body/div[2]/div/div[2]/div/div/div[2]/div[2]/div[4]/div[1]/div[2]/table/tbody/tr/td[1]/div/span/i") 
	private WebElement openAlert;
	@FindBy(xpath = "//div[@class='x-tool-tool-el x-tool-img x-tool-close ']") 
	private WebElement closeAlertPage;


	//Close Alert elements
	@FindBy(xpath = "//span[@class='x-btn-inner x-btn-inner-default-toolbar-small' and text()='Close Alert']") 
	private WebElement closeAlertButton;
	@FindBy(xpath = "//div[@class='x-title-text x-title-text-default x-title-item' and text()='Close Alert']") 
	private WebElement closeAlertDialogBox;
	@FindBy(xpath = "//input[@name='outcome_combo']") 
	private WebElement outcomeComboBoxElement;
	@FindBy(xpath = "//textarea[@name='notes']") 
	private WebElement notesElement;
	@FindBy(xpath = "//span[@class='x-btn-inner x-btn-inner-default-small' and text()='Close']") 
	private WebElement closeButton;
	@FindBy(xpath = "//span[@class='x-btn-inner x-btn-inner-default-toolbar-small' and text()='Reopen Alert']") 
	private WebElement reopenAlertButton;
	@FindBy(xpath = "//div[@class='alert-panel-heading']/i[@class='fa fa-times']") 
	private WebElement closedAlertPanelHeading;

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

	public LinkedList<String> returnAlertsPageColumns(){
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


	// Method to click In Progress Alerts menu option
	public void clickInProgressAlertsMenu() {
		CommonActions.waitForElementToBeVisible(driver, inProgressAlertsElement);
		if(inProgressAlertsElement.isDisplayed()){
			inProgressAlertsElement.click();
			System.out.println("In Progress Alerts menu is clicked...");			
		}else{
			System.out.println("Failed to click In Progress Alerts menu...");	
		}
	}

	// Method to verify in progress alerts panel title
	public String verifyInProgressAlertsGridTitle() {
		CommonActions.waitForElementToBeVisible(driver, inProgressAlertsElement);
		System.out.println(inProgressAlertsElement.getText()+" in progress alerts panel title is displayed...");
		return inProgressAlertsElement.getText();
	}

	//Method to navigate to In Progress Alerts
	public void navigateToInProgressAlertsOption(){
		driver.navigate().refresh();
		CommonActions.waitForElementToBeClickable(driver, inProgressAlertsElement);
		CommonActions.clickElementToHandleStaleElementException(inProgressAlertsElement);
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


	//Method to open alert
	public void openAlert(String alertWearerName, String alertStartDateFormat, String wearerNUI){
		boolean isWeareNUIPresent = isWearerNUIDisplayedInInProgressListPage(wearerNUI);
		if(isWeareNUIPresent = true){
			//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			System.out.println("Wearer with NUI "+wearerNUI+ " is displayed in In Progress Alerts list page...");
			CommonActions.waitForElementToBeVisible(driver, openAlert);
			if(openAlert.isDisplayed()){
				//CommonActions.clickElementToHandleStaleElementException(openAlert);
				openAlert.click();
				System.out.println("Alerts detail page is displayed...");
			}else{
				System.out.println("Alerts detail page is not displayed...");
			}
		}else{
			System.out.println("Wearer with NUI "+wearerNUI+ " is not displayed in In Progress Alerts list page...");
		}		
	}

	//Method to close alert detail page
	public void closeAlertDetailPage(){
		CommonActions.waitForElementToBeVisible(driver, closeAlertPage);
		if(closeAlertPage.isDisplayed()){
			closeAlertPage.click();
			System.out.println("Close(X) button is displayed in alerts detail page...");
		}else{
			System.out.println("Close(X) button is not displayed in alerts detail page...");
		}
	}

	//Method to click close alert button
	public void clickCloseAlertButtonInDetailPage(){
		CommonActions.waitForElementToBeVisible(driver, closeAlertButton);
		if(closeAlertButton.isDisplayed()){
			closeAlertButton.click();
			System.out.println("Close Alert button is displayed in alerts detail page...");
		}else{
			System.out.println("Close Alert button is not displayed in alerts detail page...");
		}
	}

	//Method to verify Close Alert dialog box 
	public void verifyCloseAlertDialogBox(){
		CommonActions.waitForElementToBeVisible(driver, closeAlertDialogBox);
		if(closeAlertDialogBox.isDisplayed()){
			String getDialogTitle = closeAlertDialogBox.getText();
			if(getDialogTitle.equals(StringConstants.CLOSE_ALERT_DIALOG_TITLE)){
				System.out.println("Close alert dialog box with title "+getDialogTitle+" is displayed...");
			}else{
				System.out.println("Close alert dialog box with title "+getDialogTitle+" is not displayed...");
			}
			//System.out.println("Outcome combo box is displayed in Close alert dialog box...");
		}else{
			System.out.println("Outcome combo box is not displayed in Close alert dialog box...");
		}
	}

	//Method to set alert notes
	public void setAlertNotes(String alertNotes){
		CommonActions.waitForElementToBeVisible(driver, notesElement);
		if(notesElement.isDisplayed()){
			notesElement.click();
			notesElement.clear();
			notesElement.sendKeys(alertNotes);
			System.out.println("Note textarea is displayed in Close alert dialog box...");
		}else{
			System.out.println("Note textarea is not displayed in Close alert dialog box...");
		}
	}

	//Method to select Confirm option in Outcome combo box
	public void selectConfirmOption(){
		CommonActions.waitForElementToBeVisible(driver, outcomeComboBoxElement);
		if(outcomeComboBoxElement.isDisplayed()){
			outcomeComboBoxElement.click();
			outcomeComboBoxElement.sendKeys(Keys.ARROW_DOWN);
			outcomeComboBoxElement.sendKeys(Keys.ENTER);
			System.out.println("Outcome combo box is displayed in Close alert dialog box...");
		}else{
			System.out.println("Outcome combo box is not displayed in Close alert dialog box...");
		}
	}

	//Method to click Close button in Close alert dialog box
	public void clickCloseButton(){
		CommonActions.waitForElementToBeVisible(driver, closeButton);
		if(closeButton.isDisplayed()){
			closeButton.click();
			System.out.println("Close button is displayed in Close alert dialog box...");
		}else{
			System.out.println("Close button is not displayed in Close alert dialog box...");
		}
	}

	//Method to Confirm alert
	public void confirmAlert(String alertNotes){
		clickCloseAlertButtonInDetailPage();
		verifyCloseAlertDialogBox();
		selectConfirmOption();
		setAlertNotes(alertNotes);
		clickCloseButton();
	}


	//Method to verify reopen alert button
	public void verifyReopenAlertButton(){
		//driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		CommonActions.waitForElementToBeVisible(driver, reopenAlertButton);
		if(reopenAlertButton.isDisplayed()){			
			System.out.println("Reopen alert button is displayed in alert details page and the alert is closed successfully...");
		}else{
			System.out.println("Reopen alert button is not displayed in alert details page and the alert is not closed...");
		}
	}

	//Method to verify close alert panel heading
	public void verifyClosedAlertPanelHeading(){
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		CommonActions.waitForElementToBeVisible(driver, closedAlertPanelHeading);
		if(closedAlertPanelHeading.isDisplayed()){			
			System.out.println("Closed alert panel heading is displayed in alert details page and the alert is closed successfully...");
		}else{
			System.out.println("Closed alert panel heading is not displayed in alert details page and the alert is not closed...");
		}
	}

}
