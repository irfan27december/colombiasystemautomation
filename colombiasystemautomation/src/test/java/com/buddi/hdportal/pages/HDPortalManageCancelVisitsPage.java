package com.buddi.hdportal.pages;

import java.awt.AWTException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.buddi.colombia.common.CommonActions;
import com.buddi.colombia.utilities.Log;
import com.buddi.colombia.utilities.ReadProperties;

/**
 * @author sumaiah
 *
 */

public class HDPortalManageCancelVisitsPage {

	WebDriver driver;
	ReadProperties properties = new ReadProperties();

	//Constructor
	public HDPortalManageCancelVisitsPage(WebDriver driver){
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
	// Select cancelled reason
	@FindBy(xpath="//input[@name='cancelled_combo']")
	private WebElement cancelledReasonDropdownElement;
	//Notes text Box 
	@FindBy(xpath="//textarea[@name='notes']")
	private WebElement cancelledNotesTextBoxElement;
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
			Log.info("Visits menu is clicked...!");

		}else{
			Log.info("Failed to clicked visit menu..!");
		}
	}

	//Method to navigate to Scheduled Page
	public void clickSheduledVisitsMenu(){
		CommonActions.waitForElementToBeVisible(driver, scheduledVisitsMenuElement);
		if(scheduledVisitsMenuElement.isDisplayed()) {
			CommonActions.javaScriptClick(driver, scheduledVisitsMenuElement);
			Log.info("Scheduled Visits menu is clicked...!");

		}else{
			Log.info("Failed to clicked Scheduled visit menu..!");
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
				Log.info("Open "+addNewVisitWearerNUI+" NUI wearer visit.. "+addVisitWearerNUIElement.isDisplayed());
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
			Log.info("Is Action dialog title is displayed..."+actionDialogTitleElement.isDisplayed());
			return actionDialogTitleElement.isDisplayed();
		}else{
			Log.info("Failed to display Action dialog title...!");
			return false;
		}
	}

	//Method to select outcome dropdown value
	public void setOutComeOption(String selectCancelledOutCome){
		CommonActions.waitForElementToBeClickable(driver, outComeDropdownElement);
		if(outComeDropdownElement.isDisplayed()) {
			CommonActions.javaScriptClick(driver, outComeDropdownElement);
			outComeDropdownElement.sendKeys(selectCancelledOutCome);
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ENTER).perform();
			//CommonActions.autoSuggestionMethod(driver);
			Log.info("OutCome dropdown is clicked...selected value is : "+selectCancelledOutCome);
		}else{
			Log.info("OutCome dropdown is not clicked...!");
		}
	}

	//Method to cancelled visit reason  
	public void setCancelledReason(String selectCancelledReason){
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		CommonActions.waitForElementToBeClickable(driver, cancelledReasonDropdownElement);
		if(cancelledReasonDropdownElement.isDisplayed()) {
			CommonActions.javaScriptClick(driver, cancelledReasonDropdownElement);
			cancelledReasonDropdownElement.sendKeys(selectCancelledReason);
			CommonActions.autoSuggestionMethod(driver);
			Log.info("reason dropdown is clicked...selected value is: "+selectCancelledReason);

		}else{
			Log.info("Failed to clicked cancelled reason...");
		}
	}

	//Method to enter notes
	public void enterCancelledNotes(String enterCancelledNotes){
		CommonActions.waitForElementToBeClickable(driver, cancelledNotesTextBoxElement);
		if(cancelledNotesTextBoxElement.isDisplayed()) {
			CommonActions.javaScriptClick(driver, cancelledNotesTextBoxElement);
			cancelledNotesTextBoxElement.sendKeys(enterCancelledNotes);
			CommonActions.autoSuggestionMethod(driver);
			Log.info("Entered cancelled notes is:.."+ enterCancelledNotes);

		}else{
			Log.info("Failed to clicked cancelled notes text box ....");
		}
	}

	//Method to click Update button
	public void clickUpdateButton() {
		CommonActions.waitForElementToBeClickable(driver, actionDialogUpdateButtonElement);
		if(actionDialogUpdateButtonElement.isEnabled() && actionDialogUpdateButtonElement.isDisplayed()){
			CommonActions.javaScriptClick(driver, actionDialogUpdateButtonElement);
			Log.info("Clicked Update button..");
		}else{
			Log.info("Failed to click add new visit button...");
		}
	}

	//Method to navigate to Scheduled Page
	public void selectCancelledVisit(String selectCancelledOutCome, String selectCancelledReason, String enterCancelledNotes){
		setOutComeOption(selectCancelledOutCome);
		setCancelledReason(selectCancelledReason);
		enterCancelledNotes(enterCancelledNotes);
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
