/**
 * 
 */
package com.buddi.hdportal.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.colombia.common.CommonActions;
import com.colombia.testdata.TestData;
import com.colombia.utilities.ReadProperties;

/**
 * @author irfan
 *
 */
public class HDPortalHomePage {
	WebDriver driver;
	ReadProperties properties = new ReadProperties();

	//Constructor
	public HDPortalHomePage(WebDriver driver){
		/*this.driver=driver;
			PageFactory.initElements(driver, HDPortalHomePage.class);*/
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Using FindBy for locating elements

	//@FindBy(xpath = "//div[@class='helpdesk-title' and contains(text(), 'Herramienta de gestión')]") 
	//@FindBy(xpath = "//*[starts-with(text(), 'Herramienta')]") 
	@FindBy(className = "helpdesk-title") 
	private WebElement helpDeskTitleElement;

	@FindBy(xpath = "//div[contains(text(),'Status')]") 
	private WebElement statusElement;


	// Method to verify HD title
	public boolean verifyHelpDeskTitle() {
		return helpDeskTitleElement.isDisplayed();
	}

	// Method to verify Status title
	public boolean verifyStatusTitle() {
		return statusElement.isDisplayed();
	}
	
	public String verifyHomePageTitle(){
		return driver.getTitle();
	}
}
