/**
 * 
 */
package com.buddi.hdportal.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.buddi.colombia.common.CommonActions;
import com.buddi.colombia.testdata.TestData;

/**
 * @author irfan
 *
 */
public class HDPortalManagementPage {
	WebDriver driver;

	//Constructor
	public HDPortalManagementPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Using FindBy for locating elements

	@FindBy(xpath = "//div[contains(text(),'Management')]") 
	private WebElement managementElement;
	@FindBy(xpath = "//div[contains(text(),'User Groups')]") 
	private WebElement userGroupsElement;
	@FindBy(xpath = "//div[contains(text(),'Manage User Groups ~ Helpdesk access permission')]") 
	private WebElement userGroupsPanelTitleElement;
	@FindBy(xpath = "//span[contains(text(),'Add')]") 
	private WebElement addUserGroupButton;
	@FindBy(xpath = "//div[contains(text(),'Add User Group')]") 
	private WebElement addUserGroupDialogElement;
	@FindBy(name = "name") 
	private WebElement nameField;
	@FindBy(xpath = "*//span[@class='x-btn-inner x-btn-inner-default-small' and contains(text(),'Add')]")
	private WebElement addButton;
	@FindBy(xpath = "//div[contains(text(),'User group created')]") 
	private WebElement userGroupsAddedMessage;
	@FindBy(xpath = "//div[contains(@class,'x-grid-cell-inner')][contains(text(),'"+TestData.USERGROUP_NAME+"')]") 
	private WebElement userGroupName;

	@FindBy(xpath = "//div[contains(text(),'"+TestData.USERGROUP_NAME+"')]/following::i[@class='x-fa fa-icon-red fa-times-circle']")
	////div[contains(text(),'UG_AUTOMATION')]/following::span[@class="btn btn-default btn-sm"] --- This xpath also works
	////td[contains(div/text(),'UG_AUTOMATION')]/following-sibling::td[div/span/i/@class='x-fa fa-icon-red fa-times-circle']/@data-qtip
	private WebElement deleteUserGroupIcon;
	@FindBy(xpath = "//div[contains(text(),'Confirm')]") 
	private WebElement confirmationPopUp;
	@FindBy(xpath = "//div[contains(text(),'Are you sure you wish to remove this User Group?')]") 
	private WebElement deleteUserGroupConfirmationText;
	@FindBy(xpath = "//span[contains(text(),'Yes')]") 
	private WebElement buttonYes;


	@FindBy(xpath = "//span[contains(text(),'Duplicate')]") 
	private WebElement duplicateUserGroupButton;	
	@FindBy(xpath = "//input[@name='name']") 
	private WebElement duplicateUGNameElement;
	@FindBy(xpath = "*//span[@class='x-btn-inner x-btn-inner-default-small' and contains(text(),'Duplicate')]")
	private WebElement duplicateButton;
	@FindBy(xpath = "//div[contains(@class,'x-grid-cell-inner')][contains(text(),'"+TestData.DUPLICATE_USERGROUP_NAME+"')]") 
	private WebElement duplicateUserGroupName;


	// Method to click LHS menu option
	public void clickManagementMenu() {
		CommonActions.waitForElementToBeVisible(driver, managementElement);
		managementElement.click();
		System.out.println("Management menu is expanded...");
	}

	// Method to click User Groups option
	public void clickUserGroupsOption() {
		CommonActions.waitForElementToBeVisible(driver, userGroupsElement);
		userGroupsElement.click();
		System.out.println("Clicked Uer Groups option...");
	}

	// Method to verify user groups panel title
	public String verifyUserGroupsPanelTitle() {
		CommonActions.waitForElementToBeVisible(driver, userGroupsPanelTitleElement);
		System.out.println(userGroupsPanelTitleElement.getText()+" user groups panel title is displayed...");
		return userGroupsPanelTitleElement.getText();
	}

	// Method to click User Groups option
	public void clickAddUserGroupsButton() {
		CommonActions.waitForElementToBeVisible(driver, addUserGroupButton);
		addUserGroupButton.click();
		System.out.println("Clicked Add button to add user group...");
	}


	// Method to set user group name
	public void setUserGroupName() {
		CommonActions.waitForElementToBeVisible(driver, nameField);
		nameField.click();
		nameField.clear();
		nameField.sendKeys(TestData.USERGROUP_NAME);
		System.out.println("Entered user group name...");		
	}

	//Method to create user group
	public void createUserGroup() throws InterruptedException{
		setUserGroupName();
		if(addButton.isEnabled()){	
			System.out.println("Add button is enabled "+addButton.isEnabled());		
			CommonActions.waitForElementToBeClickable(driver, addButton);
			CommonActions.clickElementToHandleStaleElementException(addButton);
			//Below action code works fine
			/*Actions builder = new Actions(driver);
			builder.sendKeys(Keys.TAB).build().perform();
			builder.sendKeys(Keys.SPACE).build().perform();*/
			CommonActions.waitForElementToBeVisible(driver, userGroupName);
			//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		}else{
			System.out.println("Add button is not enabled "+addButton.isEnabled());
		}
	}

	//Method to get user group name
	public String getUserGroupName(){
		CommonActions.waitForElementToBeVisible(driver, userGroupName);
		//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		System.out.println("User group is created successfully with name:  "+userGroupName.getText());
		return userGroupName.getText();
	}

	//Method to select user group
	public void selectUserGroup(){
		navigateToUserGroupsOption();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		if(userGroupName.isDisplayed()){
			CommonActions.waitForElementToBeClickable(driver, userGroupName);
			//driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			CommonActions.clickElementToHandleStaleElementException(userGroupName);
			System.out.println("Clicked on user group with name: "+TestData.USERGROUP_NAME);
		}else{
			System.out.println("Failed to click on user group with name: "+TestData.USERGROUP_NAME);
		}
	}



	//Method to delete user group
	public void deleteUserGroup(){
		selectUserGroup();
		if(deleteUserGroupIcon.isDisplayed()){
			CommonActions.waitForElementToBeClickable(driver, deleteUserGroupIcon);
			CommonActions.clickElementToHandleStaleElementException(deleteUserGroupIcon);			
			verifyConfirmationPopUp();
			verifyConfirmationText();
			clickYesInConfirmationPopUp();
			//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		}else{
			System.out.println("Delete user group button is not displayed...");
		}
	}

	//Method to verify confirmation pop up
	public void verifyConfirmationPopUp(){
		if(confirmationPopUp.isDisplayed()){
			CommonActions.waitForElementToBeVisible(driver, confirmationPopUp);
			//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			System.out.println(confirmationPopUp.getText()+" confirmation pop up is displayed...");
		}else{
			System.out.println(confirmationPopUp.getText()+" confirmation pop up is not displayed...");
		}
	}

	//Method to verify confirmation pop up
	public void verifyConfirmationText(){
		if(deleteUserGroupConfirmationText.isDisplayed()){
			CommonActions.waitForElementToBeVisible(driver, deleteUserGroupConfirmationText);
			//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			System.out.println(deleteUserGroupConfirmationText.getText()+" confirmation text is displayed...");
		}else{
			System.out.println(deleteUserGroupConfirmationText.getText()+" confirmation text is not displayed...");
		}
	}

	//Method to click Yes button in confirmation pop up
	public void clickYesInConfirmationPopUp(){
		if(buttonYes.isDisplayed() && buttonYes.isEnabled()){
			CommonActions.waitForElementToBeVisible(driver, buttonYes);			
			System.out.println("Yes button is displayed in confirmation pop up....");
			buttonYes.click();
			//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		}else{
			System.out.println("Yes button is not displayed in confirmation pop up....");
		}
	}


	//Method to check if a user group exist
	public boolean isUserGroupDeleted(){
		//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		try{
			System.out.println("User group with name: "+userGroupName.getText()+ " is deleted...");	
		}catch(Exception e){
			e.printStackTrace();
		}
		return userGroupName.isDisplayed();	
	}

	// Method to click Duplicate button
	public void clickDuplicateButton() {
		if(duplicateUserGroupButton.isDisplayed()){
			//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			CommonActions.waitForElementToBeVisible(driver, duplicateUserGroupButton);
			CommonActions.clickElementToHandleStaleElementException(duplicateUserGroupButton);	
			//duplicateButton.click();
			System.out.println("Clicked Duplicate button...");	
		}else{
			System.out.println("Failed to click Duplicate button...");
		}		
	}

	//Method to set duplicate user group name
	public void setDuplicateUserGroupName() {
		if(nameField.isDisplayed()){
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			nameField.clear();
			nameField.click();
			nameField.sendKeys(TestData.DUPLICATE_USERGROUP_NAME );
			System.out.println("Entered duplicate user group name...");	
		}else{
			System.out.println("Failed to enter duplicate user group name...");
		}	
	}


	//Method to click duplicate user group button
	public void clickDuplicateUserGroupButton() {
		if(duplicateButton.isDisplayed()){
			//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			CommonActions.waitForElementToBeClickable(driver, duplicateButton);
			CommonActions.clickElementToHandleStaleElementException(duplicateButton);
			System.out.println("Clicked Duplicate button in the pop up...");	
		}else{
			System.out.println("Failed to click Duplicate button in the pop up...");
		}	
	}

	//Method to save duplicate user group
	public void saveDuplicateUserGroup(){
		//driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		selectUserGroup();
		//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		clickDuplicateButton();
		//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		setDuplicateUserGroupName();
		clickDuplicateUserGroupButton();
	}

	//Method to select duplicate user group
	public void selectDuplicateUserGroup(){
		navigateToUserGroupsOption();
		if(duplicateUserGroupName.isDisplayed()){
			CommonActions.waitForElementToBeClickable(driver, duplicateUserGroupName);
			//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			CommonActions.clickElementToHandleStaleElementException(duplicateUserGroupName);
			System.out.println("Clicked on user group with name: "+TestData.DUPLICATE_USERGROUP_NAME);
		}else{
			System.out.println("Failed to click on user group with name: "+TestData.DUPLICATE_USERGROUP_NAME);
		}
	}

	//Method to get duplicate user group name
	public String getDuplicateUserGroupName(){
		CommonActions.waitForElementToBeVisible(driver, duplicateUserGroupName);
		//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		System.out.println("Duplicate user group is created successfully with name:  "+duplicateUserGroupName.getText());
		return duplicateUserGroupName.getText();
	}


	//Method to delete duplicate user group
	public void deleteDuplicateUserGroup(){
		selectDuplicateUserGroup();
		//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		if(deleteUserGroupIcon.isDisplayed()){
			CommonActions.waitForElementToBeClickable(driver, deleteUserGroupIcon);
			CommonActions.clickElementToHandleStaleElementException(deleteUserGroupIcon);			
			verifyConfirmationPopUp();
			verifyConfirmationText();
			clickYesInConfirmationPopUp();
			//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		}else{
			System.out.println("Delete user group button is not displayed...");
		}
	}

	//Method to check if a duplicate user group exist
	public boolean isDuplicateUserGroupDeleted(){
		//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		try{
			System.out.println("User group with name: "+duplicateUserGroupName.getText()+ " is deleted...");	
		}catch(Exception e){
			e.printStackTrace();
		}
		return duplicateUserGroupName.isDisplayed();	
	}
	
	//Method to navigate to user groups option
	public void navigateToUserGroupsOption(){
		//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.navigate().refresh();
		CommonActions.waitForElementToBeClickable(driver, managementElement);
		CommonActions.clickElementToHandleStaleElementException(managementElement);
		//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		CommonActions.waitForElementToBeClickable(driver, userGroupsElement);
		CommonActions.clickElementToHandleStaleElementException(userGroupsElement);
		
	}

}
