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
import com.buddi.colombia.utilities.Log;
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
		if(hdUserNameElement.isDisplayed()){
			hdUserNameElement.clear();	
			hdUserNameElement.click();	
			hdUserNameElement.sendKeys(userName);
			Log.info("Email field is displayed and email is entered");
		}else{
			Log.error("Email field is not displayed..."); 
		}
		
	}

	//Method to set password
	public void setPassword(String password){
		CommonActions.waitForElementToBeClickable(driver, hdPasswordElement);
		if(hdPasswordElement.isDisplayed()){
			hdPasswordElement.clear();	
			hdPasswordElement.click();	
			hdPasswordElement.sendKeys(password);
			Log.info("Password field is displayed and password is entered......");
		}else{
			Log.error("Password field is not displayed..."); 
		}		
	}

	// To check if login panel appeared
	public boolean isloginPanelDisplayed(){
		return loginPanelElement.isDisplayed();
	}

	//Method to click login element
	public void clickLogin(){
		CommonActions.waitForElementToBeClickable(driver, loginElement);
		loginElement.click();	
		Log.info("Login button is clicked......");
	}

	// Method to login HD portal
	public void loginHDPortal(String userName, String password){
		setUserName(userName);
		setPassword(password);
		clickLogin();
	}

	// To check if login failed dialog box is displayed
	public boolean isloginFailedDialogBoxDisplayed(){ 
		Log.info("Is login failed dialog box appeared... "+loginFailedDialogBoxTitle.isDisplayed());
		return loginFailedDialogBoxTitle.isDisplayed();
	}

	// To check if login failed message appeared
	public boolean isUserIconDisplayed(){
		WebElement userIconElement = driver.findElement(By.xpath("//span[@class='x-btn-icon-el x-btn-icon-el-default-small x-fa fa-user ']")); 
		Log.info("Is user icon displayed : "+userIconElement.isDisplayed());
		return userIconElement.isDisplayed();
	}

	//Method to click logout element
	public boolean isLogOutOptionDisplayed(){
		Log.info("Is logout link displayed : "+logOutElement.isDisplayed());
		return logOutElement.isDisplayed();		
	}

	//Method to click logout element
	public void clickLogOut(){
		CommonActions.waitForElementToBeClickable(driver, logOutElement);
		Log.info("Logout link element found and is clicked...");
		logOutElement.click();		
	}

	// Method to logout HD portal
	public void logoutHDPortal(){
		clickLogOut();
		Log.info("Logged out HD portal successfully....");
	}
}
