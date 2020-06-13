/**
 * 
 */
package com.buddi.hdportal.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.buddi.colombia.common.CommonActions;
import com.colombia.testdata.TestData;
import com.colombia.utilities.ReadProperties;

/**
 * @author irfan
 *
 */
public class HDPortalLoginPage {
	WebDriver driver;
	ReadProperties properties = new ReadProperties();

	//Constructor
	public HDPortalLoginPage(WebDriver driver){
		/*this.driver=driver;
			PageFactory.initElements(driver, HDPortalLoginPage.class);*/
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Using FindBy for locating elements
	@FindBy(xpath = "//input[@name='username']") 
	private WebElement hdUserNameElement;	
	@FindBy(xpath = "//input[@name='password']") 
	private WebElement hdPasswordElement;
	@FindBy(xpath = "//span[text()='Login']") 
	private WebElement loginElement;
	@FindBy(xpath = "//span[text()='Logout']") 
	private WebElement logOutElement;
	

	//Method to verify HD login page title
	public String verifyPageTitle() {
		return driver.getTitle();
	}

	//Method to set user name
	public void setUserName(){
		CommonActions.waitForElementToBeClickable(driver, hdUserNameElement);
		hdUserNameElement.clear();	
		hdUserNameElement.click();	
		hdUserNameElement.sendKeys(TestData.APP_EMAIL);
	}

	//Method to set password
	public void setPassword(){
		CommonActions.waitForElementToBeClickable(driver, hdPasswordElement);
		hdPasswordElement.clear();	
		hdPasswordElement.click();	
		hdPasswordElement.sendKeys(TestData.APP_PASSWORD);
	}

	// To verify buddi logo
	/*public boolean verifybuddiLogo(){
		return buddiLogoElement.isDisplayed();
	}*/

	//Method to click login element
	public void clickLogin(){
		CommonActions.waitForElementToBeClickable(driver, loginElement);
		loginElement.click();		
	}

	// Method to login HD portal
	public void loginHDPortal(){
		setUserName();
		setPassword();
		clickLogin();
	}

	//Method to click logout element
	public void clickLogOut(){
		CommonActions.waitForElementToBeClickable(driver, logOutElement);
		logOutElement.click();		
	}

	// Method to logout HD portal
	public void logoutHDPortal(){
		clickLogOut();
		//System.out.println("Logged out HD portal successfully....");
	}
}
