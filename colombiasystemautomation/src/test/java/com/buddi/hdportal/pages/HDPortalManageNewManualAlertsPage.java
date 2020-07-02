package com.buddi.hdportal.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.buddi.colombia.common.CommonActions;

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

}
