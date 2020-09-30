/**
 * 
 */
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
public class HDPortalHomePage {
	WebDriver driver;

	//Constructor
	public HDPortalHomePage(WebDriver driver){
		/*this.driver=driver;
			PageFactory.initElements(driver, HDPortalHomePage.class);*/
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Using FindBy for locating elements

	@FindBy(xpath = "//div[@class='helpdesk-title' and contains(text(), 'Herramienta de gestión')]") 
	private WebElement helpDeskTitleElement;
	@FindBy(xpath = "//div[contains(text(),'Status')]") 
	private WebElement statusElement;
	@FindBy(xpath = "//div[contains(text(),'Management')]") 
	private WebElement managementElement;


	// Method to verify HD title
	public boolean verifyHelpDeskMainTitle() {
		return helpDeskTitleElement.isDisplayed();
	}

	// Method to get HD title
	public String getHelpDeskTitle() {
		System.out.println("HelpDesk main title is: "+ helpDeskTitleElement.getText());
		return helpDeskTitleElement.getText();
	}

	// Method to verify Status title
	public boolean verifyStatusTitle() {
		return statusElement.isDisplayed();
	}

	// Method to verify home page title
	public String verifyHomePageTitle(){
		return driver.getTitle();
	}

	// Method to click LHS menu option
	public void clickManagementMenu() {
		CommonActions.waitForElementToBeVisible(driver, managementElement);
		managementElement.click();
	}
}
