package com.buddi.hdportal.pages;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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

public class HDPortalManageAlertHistoryPage {
	WebDriver driver;

	//Constructor
	public HDPortalManageAlertHistoryPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Using FindBy for locating elements
	//Web elements related to manage alerts
	@FindBy(xpath = "//span[contains(text(),'Regional')]") 
	private WebElement regionalColumnElement;

	//In Alerts History elements
	@FindBy(xpath = "//div[@class='x-treelist-item-text' and contains(text(),'Alert History')]") 
	private WebElement alertHistoryElement;
	@FindBy(xpath = "//div[@class='x-title-text x-title-text-default-framed x-title-item' and contains(text(),'Alert History')]") 
	private WebElement alertHistoryGridTitleElement;
	@FindBy(xpath = "//input[@class='x-form-field x-form-text x-form-text-default ']") 
	private WebElement selectWearerComboBox;
	@FindBy(xpath = "//input[@class='x-form-field x-form-empty-field x-form-empty-field-default x-form-text x-form-text-default ']") 
	private WebElement startDateField;
	@FindBy(xpath = "/html/body/div[2]/div/div[2]/div/div/div[2]/div[2]/div[4]/div[1]/div[2]/table[1]/tbody/tr/td[1]/div/span/i") 
	private WebElement openAlert;
	@FindBy(xpath = "//div[@class='x-tool-tool-el x-tool-img x-tool-close ']") 
	private WebElement closeAlertPage;


	//Alert detail page elements
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
		list.add(StringConstants.ALERTS_PAGE_CLOSED_COLUMN);
		return list;
	}


	// Method to click Alert History menu option
	public void clickAlertHistoryMenu() {
		CommonActions.waitForElementToBeVisible(driver, alertHistoryElement);
		if(alertHistoryElement.isDisplayed()){
			alertHistoryElement.click();
			System.out.println("Alert History menu is clicked...");			
		}else{
			System.out.println("Failed to click Alert History menu...");	
		}
	}

	// Method to verify alert history grid title
	public String verifyAlertHistoryGridTitle() {
		CommonActions.waitForElementToBeVisible(driver, alertHistoryGridTitleElement);
		System.out.println(alertHistoryGridTitleElement.getText()+" alert history title is displayed...");
		return alertHistoryGridTitleElement.getText();
	}

	//Method to navigate to Alert History menu
	public void navigateToAlertHistoryOption(){
		driver.navigate().refresh();
		CommonActions.waitForElementToBeClickable(driver, alertHistoryElement);
		CommonActions.clickElementToHandleStaleElementException(alertHistoryElement);
	}

	//Method to check NUI in alert history page 
	public boolean isWearerNUIDisplayedInAlertHistoryListPage(String wearerNUI){
		WebElement wearerNUIElement = driver.findElement(By.xpath("//div[contains(text(),'"+wearerNUI+"')]"));
		CommonActions.waitForElementToBeVisible(driver, wearerNUIElement);
		if(wearerNUIElement.isDisplayed()){
			System.out.println("Wearer with NUI "+wearerNUI+ " is displayed in Alert History list page...");	
			return true;
		}else{
			System.out.println("Wearer with NUI "+wearerNUI+ " is not displayed in Alert History list page...");
			return false;
		}
	}

	//Method to open alert
	public void openAlert(String alertWearerName, String alertStartDateFormat, String wearerNUI){
		boolean isWeareNUIPresent = isWearerNUIDisplayedInAlertHistoryListPage(wearerNUI);
		if(isWeareNUIPresent = true){
			//System.out.println("Wearer with NUI "+wearerNUI+ " is displayed in Alert History list page...");
			//CommonActions.javaScriptClick(openAlert, driver);
			CommonActions.waitForElementToBeVisible(driver, openAlert);
			if(openAlert.isDisplayed()){
				//CommonActions.clickElementToHandleStaleElementException(openAlert);
				openAlert.click();
				System.out.println("Alerts detail page is displayed...");
			}else{
				System.out.println("Alerts detail page is not displayed...");
			}
		}else{
			System.out.println("Wearer with NUI "+wearerNUI+ " is not displayed in Alert History list page...");
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

	//Method to verify reopen alert button
	public void verifyReopenAlertButton(){
		//driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		CommonActions.waitForElementToBeVisible(driver, reopenAlertButton);
		//CommonActions.waitForElementToBeVisible(driver, closedAlertPanelHeading);
		if(reopenAlertButton.isDisplayed()){			
			System.out.println("Reopen alert button is displayed in alert details page and the alert is closed successfully...");
		}else{
			System.out.println("Reopen alert button is not displayed in alert details page and the alert is not closed...");
		}
	}

	//Method to verify close alert panel heading
	public void verifyClosedAlertPanelHeading(){
		//driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		CommonActions.waitForElementToBeVisible(driver, closedAlertPanelHeading);
		CommonActions.waitForElementToBeRefreshed(driver, closedAlertPanelHeading);
		if(closedAlertPanelHeading.isDisplayed()){			
			System.out.println("Closed alert panel heading is displayed in alert details page and the alert is closed successfully...");
		}else{
			System.out.println("Closed alert panel heading is not displayed in alert details page and the alert is not closed...");
		}
	}

}
