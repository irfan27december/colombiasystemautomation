/**
 * 
 */
package com.buddi.colombiapp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.buddi.colombia.common.CommonActions;
import com.buddi.colombia.testdata.StringConstants;
import com.buddi.colombia.testdata.TestData;
import com.buddi.colombia.utilities.ReadProperties;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * @author irfan
 *
 */
public class LoginColombiaAppPage2 {

	WebDriver driver;
	ReadProperties properties = new ReadProperties();

	//Constructor
	public LoginColombiaAppPage2(WebDriver driver){
		/*this.driver=driver;
		PageFactory.initElements(driver, LoginColombiaAppPage.class);*/
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//@FindBy(xpath = "//*[contains(@value='Management Tool')]") private WebElement AppMainTitle;	
	//@FindBy(how=How.XPATH,using=("//android.widget.TextView[contains(text(),'Management Tool')]")) private WebElement AppMainTitle;
	//@FindBy(how=How.XPATH,using=("//android.widget.TextView[contains(.,' Help Desk System ')]")) private WebElement AppMainTitle;
	//@AndroidFindBy(uiAutomator = "new UiSelector().textContains('Management Tool')") private MobileElement AppMainTitle;

	@FindBy(xpath = "//*[@class='android.widget.TextView'][@index = '1']") private WebElement AppMainTitle;	
	@FindBy(xpath = "//*[@class='android.widget.TextView'][@index = '0']") private WebElement AppSubTitle;	
	@FindBy(id = "txt_phone_id") private WebElement textPhoneID;

	@FindBy(id = "edit_email") private WebElement emailField;
	@FindBy(id = "edit_password") private WebElement passwordField;
	@FindBy(id = "button") private WebElement loginButton;
	@FindBy(id = "txt_no_jobs") private WebElement txtNoJobs;
	@FindBy(className = "android.widget.ImageView") private WebElement logOutButton;
	//WebElement logOutButton = driver.findElement(MobileBy.className("android.widget.ImageView"));



	// To verify if App home screen main title is present
	public boolean isAppHomeScreenMainTitlePresent(){
		//CommonActions.waitForElementToBeVisible(driver, AppMainTitle);
		return AppMainTitle.isDisplayed();
	}

	//Method to get App home screen main title
	public String getAppHomeScreenTitle(){
		String mainTitle = AppMainTitle.getText();
		System.out.println("App home screen main title is " +mainTitle);
		System.out.println("App is launched successfully....");
		return mainTitle;
	}

	// To verify if App home screen sub title is present
	public boolean isAppHomeScreenSubTitlePresent(){
		//CommonActions.waitForElementToBeVisible(driver, AppSubTitle);
		return AppSubTitle.isDisplayed();
	}

	//Method to get App home screen sub title
	public String getAppHomeScreenSubTitle(){
		String subTitle = AppSubTitle.getText();
		System.out.println("App home screen sub title is " +subTitle);
		return subTitle;
	}


	//Method to get Phone ID
	public void getPhoneID(){
		//CommonActions.waitForElementToBeVisible(driver, textPhoneID);
		String phoneID = textPhoneID.getAttribute("text");
		System.out.println("Phone ID is " +phoneID );
	}

	//Method to get no jobs text
	public String getNoJobsText(){
		//CommonActions.waitForElementToBeVisible(driver, txtNoJobs);
		String noJobsText = txtNoJobs.getText();
		System.out.println("Screen shows following no jobs text: " +noJobsText);
		return noJobsText;
	}


	//Method to set text in Email field
	public void setTextInEmailField(){
		CommonActions.waitForElementToBeVisible(driver, emailField);
		emailField.click();
		emailField.clear();
		emailField.sendKeys(TestData.APP_EMAIL);
	}

	//Method to set text in Password field
	public void setTextInPasswordField(){
		CommonActions.waitForElementToBeVisible(driver, passwordField);
		passwordField.click();
		passwordField.clear();
		passwordField.sendKeys(TestData.APP_PASSWORD);
	}

	//Method to click Login button
	public void clickLoginButton(){
		CommonActions.waitForElementToBeVisible(driver, loginButton);
		loginButton.click();
	}

	//Method to click LogOut button
	public void clickLogOutButton(){
		CommonActions.waitForElementToBeVisible(driver, logOutButton);
		logOutButton.click();
	}

	//App login method
	public void loginColombiaApp(){
		setTextInEmailField();
		setTextInPasswordField();
		clickLoginButton();
	}

	//App logout method
	public void logOutColombiaApp(){
		clickLogOutButton();
		System.out.println("Logged out successfully...");
	}



}
