/**
 * 
 */
package com.buddi.hdportal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.buddi.colombia.common.CommonActions;
import com.buddi.colombia.utilities.ReadProperties;

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
	@FindBy(xpath = "//input[@name='username']") private WebElement hdUserNameElement;	
	@FindBy(xpath = "//input[@name='password']") private WebElement hdPasswordElement;
	@FindBy(xpath = "//span[text()='Login']") private WebElement loginElement;
	@FindBy(xpath = "//span[text()='Logout']") private WebElement logOutElement;
	@FindBy(xpath = "//div[@class='x-window x-layer x-window-default x-border-box x-resizable x-window-resizable x-window-default-resizable']") 
	private WebElement loginPanelElement;
	@FindBy(xpath = "//div[@class='x-title-text x-title-text-default x-title-item' and text()='Login failed']") 
	private WebElement loginFailedDialogBoxTitle;

	//Method to verify HD login page title
	public String verifyPageTitle() {
		return driver.getTitle();
	}

	//Method to set user name
	public void setUserName(String userName){
		CommonActions.waitForElementToBeClickable(driver, hdUserNameElement);
		hdUserNameElement.clear();	
		hdUserNameElement.click();	
		hdUserNameElement.sendKeys(userName);
	}

	//Method to set password
	public void setPassword(String password){
		CommonActions.waitForElementToBeClickable(driver, hdPasswordElement);
		hdPasswordElement.clear();	
		hdPasswordElement.click();	
		hdPasswordElement.sendKeys(password);
	}

	// To check if login panel appeared
	public boolean isloginPanelDisplayed(){
		return loginPanelElement.isDisplayed();
	}

	//Method to click login element
	public void clickLogin(){
		CommonActions.waitForElementToBeClickable(driver, loginElement);
		loginElement.click();		
	}

	// Method to login HD portal
	public void loginHDPortal(String userName, String password){
		setUserName(userName);
		setPassword(password);
		clickLogin();
	}

	// To check if login failed dialog box is displayed
	public boolean isloginFailedDialogBoxDisplayed(){
		System.out.println("Is login failed dialog box appeared... "+loginFailedDialogBoxTitle.isDisplayed());
		return loginFailedDialogBoxTitle.isDisplayed();
	}

	// To check if login failed message appeared
	public boolean isUserIconDisplayed(){
		WebElement userIconElement = driver.findElement(By.xpath("//span[@class='x-btn-icon-el x-btn-icon-el-default-small x-fa fa-user ']"));
		System.out.println("Is user icon displayed : "+userIconElement.isDisplayed());
		return userIconElement.isDisplayed();
	}

	//Method to click logout element
	public boolean isLogOutOptionDisplayed(){
		return logOutElement.isDisplayed();		
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
