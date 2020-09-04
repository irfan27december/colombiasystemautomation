package com.buddi.hdportal.pages;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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

	//Web elements related to add alert
	@FindBy(xpath = "//span[contains(text(),'Add')]") 
	private WebElement toolBarAddAlertButton;

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
		//list.add(StringConstants.NEWALERTS_PAGE_OPENALERT_COLUMN);
		list.add(StringConstants.NEWALERTS_PAGE_STATUS_COLUMN);
		list.add(StringConstants.NEWALERTS_PAGE_DATETIME_COLUMN);
		list.add(StringConstants.NEWALERTS_PAGE_REGIONAL_COLUMN);
		list.add(StringConstants.NEWALERTS_PAGE_WEARERGROUP_COLUMN);
		list.add(StringConstants.NEWALERTS_PAGE_NUI_COLUMN);
		//list.add(StringConstants.NEWALERTS_PAGE_WEARER_COLUMN);
		list.add(StringConstants.NEWALERTS_PAGE_DEVICE_COLUMN);
		list.add(StringConstants.NEWALERTS_PAGE_RULETYPE_COLUMN);
		list.add(StringConstants.NEWALERTS_PAGE_RULENAME_COLUMN);
		list.add(StringConstants.NEWALERTS_PAGE_LOCKED_COLUMN);
		return list;
	}

}
