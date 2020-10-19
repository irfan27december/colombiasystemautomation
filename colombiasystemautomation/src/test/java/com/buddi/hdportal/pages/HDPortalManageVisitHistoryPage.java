package com.buddi.hdportal.pages;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.buddi.colombia.common.CommonActions;
import com.buddi.colombia.testdata.StringConstants;
import com.buddi.colombia.utilities.Log;
import com.buddi.colombia.utilities.ReadProperties;

/**
 * @author sumaiah
 *
 */

public class HDPortalManageVisitHistoryPage {

	WebDriver driver;
	ReadProperties properties = new ReadProperties();

	//Constructor
	public HDPortalManageVisitHistoryPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//visitsMenuElement
	@FindBy(xpath = "//div[@class='x-treelist-item-text' and text()='Visits']") 
	private WebElement visitsMenuElement;	
	//History menu element
	@FindBy(xpath="//div[@class='x-treelist-item-text' and text()='History']")
	private WebElement visitHistoryMenuElement;
	@FindBy(xpath="//div[@class='x-title-text x-title-text-default-framed x-title-item' and text()='Visit History']")
	private WebElement visitHistoryGridTitleElement;
	//Open cancelled visit
	@FindBy(xpath="/html/body/div[2]/div/div[2]/div/div/div[2]/div[2]/div[3]/div[1]/div[2]/table[1]/tbody/tr/td[1]/div/span/i")
	private WebElement completedVisitOpenElement;
	@FindBy(xpath="//i[text()='Completed']")
	private WebElement completedStatusTextElement;
	@FindBy(xpath="/html/body/div[2]/div/div[2]/div/div/div[3]/div[1]/div/div/div[2]/div")
	private WebElement closePanelElement;

	@FindBy(xpath= "/html/body/div[2]/div/div[2]/div/div/div[2]/div[2]/div[3]/div[1]/div[2]/table[2]/tbody/tr/td[1]/div/span/i")
	private WebElement cancelledVisitOpenElement;

	@FindBy(xpath="//i[text()='Cancelled']")
	private WebElement cancelledStatusTextElement;

	// Method to click LHS menu option
	public void clickVisitsMenu() {
		CommonActions.waitForElementToBeVisible(driver, visitsMenuElement);
		if(visitsMenuElement.isDisplayed()){
			visitsMenuElement.click();
			Log.info("Visits menu is clicked...");
		}else{
			Log.info("Failed to clicked visits menu...");
		}
	}

	// Method to open scheduled visit
	public void clickHistoryMenu() {
		CommonActions.waitForElementToBeClickable(driver, visitHistoryMenuElement);
		if(visitHistoryMenuElement.isDisplayed()) {
			CommonActions.javaScriptClick(driver, visitHistoryMenuElement);
			Log.info("Clicked on History menu..!");
		}
		else{
			Log.info("Failed to clicked on History menu..!");
		}	
	}
	//Method to verify Add New Visit dialog window title  
	public boolean isVisitHistoryGridTitleDisplayed() throws AWTException{
		CommonActions.waitForElementToBeVisible(driver,visitHistoryGridTitleElement );
		if(visitHistoryGridTitleElement.isDisplayed()){
			Log.info("Is Visit History grid title displayed ..."+visitHistoryGridTitleElement.isDisplayed());
			return visitHistoryGridTitleElement.isDisplayed();
		}else{
			Log.info("Filed to clicked History Grid title...");
			return false;
		}
	}

	//Method to click open visit in scheduled page 
	public void openCompletedVisit(String addNewVisitWearerNUI ) {
		WebElement addVisitWearerNUIElement = driver.findElement(By.xpath("//div[@class='x-grid-cell-inner ' and contains(text(),"
				+ " '"+addNewVisitWearerNUI+"')]"));
		CommonActions.waitForElementToBeClickable(driver, addVisitWearerNUIElement);
		if(addVisitWearerNUIElement.isDisplayed()) {
			CommonActions.javaScriptClick(driver, addVisitWearerNUIElement);
			CommonActions.waitForElementToBeClickable(driver, completedVisitOpenElement);
			if(completedVisitOpenElement.isDisplayed()) {
				CommonActions.javaScriptClick(driver, completedVisitOpenElement);
				Log.info("Open "+addNewVisitWearerNUI+" NUI wearer visit ..."+addVisitWearerNUIElement.isDisplayed());
			}
		}
		else{
			Log.info("Failed to clicked on open visit..!");
		}	
	}

	//Method to verify Completed status   
	public boolean isCompletedStatusDisplayed() throws AWTException{
		CommonActions.waitForElementToBeVisible(driver,completedStatusTextElement );
		if(completedStatusTextElement.isDisplayed()){
			Log.info("Is completed status displayed.."+completedStatusTextElement.isDisplayed());
			return completedStatusTextElement.isDisplayed();
		}else{
			Log.info("Failed to display completed status...");
			return false;
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

	//Method to click open visit in scheduled page 
	public void openCancelledVisit(String addNewVisitWearerNUI ) {
		WebElement addVisitWearerNUIElement = driver.findElement(By.xpath("//div[@class='x-grid-cell-inner ' and contains(text(),"
				+ " '"+addNewVisitWearerNUI+"')]"));
		CommonActions.waitForElementToBeClickable(driver, addVisitWearerNUIElement);
		if(addVisitWearerNUIElement.isDisplayed()) {
			CommonActions.javaScriptClick(driver, addVisitWearerNUIElement);
			CommonActions.waitForElementToBeClickable(driver, cancelledVisitOpenElement);
			if(cancelledVisitOpenElement.isDisplayed()) {
				CommonActions.javaScriptClick(driver, cancelledVisitOpenElement);
				Log.info("Open "+addNewVisitWearerNUI+" NUI wearer visit ..."+addVisitWearerNUIElement.isDisplayed());
			}
		}
		else{
			Log.info("Failed to clicked on open visit..!");
		}	
	}

	//Method to verify Completed status   
	public boolean isCancelledStatusDisplayed() throws AWTException{
		CommonActions.waitForElementToBeVisible(driver,cancelledStatusTextElement );
		if(cancelledStatusTextElement.isDisplayed()){
			Log.info("Is cancelled status displayed ... "+cancelledStatusTextElement.isDisplayed());
			return cancelledStatusTextElement.isDisplayed();
		}else{
			Log.info("Failed to display cancelled status...");
			return false;
		}
	}
}
