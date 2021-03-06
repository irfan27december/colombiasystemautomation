/**
 * 
 */
package com.buddi.hdportal.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.buddi.colombia.common.CommonActions;
import com.buddi.colombia.testdata.TestData;

/**
 * @author irfan
 *
 */
public class HDPortalManageUserGroupsPage {
	WebDriver driver;

	//Constructor
	public HDPortalManageUserGroupsPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Using FindBy for locating elements

	//Web elements related to manage user groups
	@FindBy(xpath = "//div[contains(text(),'Management')]") 
	private WebElement managementElement;
	@FindBy(xpath = "//div[contains(text(),'User Groups')]") 
	private WebElement userGroupsElement;
	@FindBy(xpath = "//div[contains(text(),'Manage User Groups ~ Helpdesk access permission')]") 
	private WebElement userGroupsPanelTitleElement;

	//Web elements related to add user groups
	@FindBy(xpath = "//span[contains(text(),'Add')]") 
	private WebElement toolBarAddUserGroupButton;
	@FindBy(xpath = "//div[contains(text(),'Add User Group')]") 
	private WebElement addUserGroupDialogElement;
	@FindBy(name = "name") 
	private WebElement nameField;
	@FindBy(xpath = "*//span[@class='x-btn-inner x-btn-inner-default-small' and contains(text(),'Add')]")
	private WebElement addButton;
	@FindBy(xpath = "//div[contains(text(),'User group created')]") 
	private WebElement userGroupsAddedMessage;

	//Web elements related to delete user groups
	@FindBy(xpath = "//div[contains(text(),'"+TestData.USERGROUP_NAME+"')]/following::i[@class='x-fa fa-icon-red fa-times-circle']")
	//div[contains(text(),'UG_AUTOMATION')]/following::span[@class="btn btn-default btn-sm"] --- This xpath also works
	private WebElement deleteUserGroupIcon;
	@FindBy(xpath = "//div[contains(text(),'Confirm')]") 
	private WebElement confirmationPopUp;
	@FindBy(xpath = "//div[contains(text(),'Are you sure you wish to remove this User Group?')]") 
	private WebElement deleteUserGroupConfirmationText;
	@FindBy(xpath = "//span[contains(text(),'Yes')]") 
	private WebElement buttonYes;

	//Web elements related to duplicate user groups
	@FindBy(xpath = "//span[contains(text(),'Duplicate')]") 
	private WebElement toolBarDuplicateUserGroupButton;	
	@FindBy(xpath = "//input[@name='name']") 
	private WebElement duplicateUGNameElement;
	@FindBy(xpath = "*//span[@class='x-btn-inner x-btn-inner-default-small' and contains(text(),'Duplicate')]")
	private WebElement duplicateButton;
	
	//Method accepts user group name and returns user group name element
	public WebElement returnUserGroupNameElement(String userGroupName){
		WebElement userGroupNameElement = driver.findElement(By.xpath("//div[contains(@class,'x-grid-cell-inner')][contains(text(),'"+userGroupName+"')]"));
		return userGroupNameElement;
	}

	//Web elements related to error pop up for duplicate user groups
	@FindBy(xpath = "//div[contains(text(),'Error')]") 
	private WebElement errorPopUp;
	@FindBy(xpath = "//div[contains(text(),'User Group with name already exists')]") 
	private WebElement userGroupAlreadyExistsConfirmationText;
	@FindBy(xpath = "//span[contains(text(),'OK')]") 
	private WebElement buttonOK;
	
	
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
	public void clickAddUserGroupsButtonOnToolBar() {
		CommonActions.waitForElementToBeVisible(driver, toolBarAddUserGroupButton);
		toolBarAddUserGroupButton.click();
		System.out.println("Clicked Add button to add user group...");
	}


	// Method to set user group name
	public void setUserGroupName(String userGroupName) {
		CommonActions.waitForElementToBeVisible(driver, nameField);
		if(nameField.isDisplayed()){
			nameField.click();
			nameField.clear();
			nameField.sendKeys(userGroupName);
			System.out.println("Entered user group name...");
		}else{
			System.out.println("Failed to enter user group name...");
		}				
	}

	//Method to create user group
	public void createUserGroup(String strUserGroupName){
		setUserGroupName(strUserGroupName);
		if(addButton.isEnabled()){	
			System.out.println("Add button is enabled "+addButton.isEnabled());		
			CommonActions.waitForElementToBeClickable(driver, addButton);
			CommonActions.clickElementToHandleStaleElementException(addButton);
			driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
			CommonActions.waitForElementToBeVisible(driver, returnUserGroupNameElement(strUserGroupName));			
		}else{
			System.out.println("Add button is not enabled "+addButton.isEnabled());
		}
	}

	//Method to get user group name
	public String getUserGroupName(String strUserGroupName){
		CommonActions.waitForElementToBeVisible(driver, returnUserGroupNameElement(strUserGroupName));
		System.out.println("User group is created successfully with name:  "+returnUserGroupNameElement(strUserGroupName).getText());
		return returnUserGroupNameElement(strUserGroupName).getText();
	}

	//Method to select user group
	public void selectUserGroup(String strUserGroupName){
		navigateToUserGroupsOption();
		CommonActions.scrollDownVertically(driver, returnUserGroupNameElement(strUserGroupName));
		if(returnUserGroupNameElement(strUserGroupName).isDisplayed()){
			CommonActions.waitForElementToBeClickable(driver, returnUserGroupNameElement(strUserGroupName));
			CommonActions.clickElementToHandleStaleElementException(returnUserGroupNameElement(strUserGroupName));
			System.out.println("Clicked on user group with name: "+strUserGroupName);
		}else{
			System.out.println("Failed to click on user group with name: "+strUserGroupName);
		}
	}

	//Method to delete user group
	public void deleteUserGroup(String strUserGroupName){
		//selectUserGroup(strUserGroupName);
		CommonActions.scrollDownVertically(driver);
		CommonActions.scrollDownVertically(driver, deleteUserGroupIcon);
		if(deleteUserGroupIcon.isDisplayed()){
			CommonActions.waitForElementToBeClickable(driver, deleteUserGroupIcon);
			CommonActions.clickElementToHandleStaleElementException(deleteUserGroupIcon);			
			verifyConfirmationPopUp();
			verifyConfirmationText();
			clickYesInConfirmationPopUp();
		}else{
			System.out.println("Delete user group button is not displayed...");
		}
	}

	//Method to verify confirmation pop up
	public void verifyConfirmationPopUp(){
		if(confirmationPopUp.isDisplayed()){
			CommonActions.waitForElementToBeVisible(driver, confirmationPopUp);
			System.out.println(confirmationPopUp.getText()+" confirmation pop up is displayed...");
		}else{
			System.out.println(confirmationPopUp.getText()+" confirmation pop up is not displayed...");
		}
	}

	//Method to verify confirmation pop up
	public void verifyConfirmationText(){
		if(deleteUserGroupConfirmationText.isDisplayed()){
			CommonActions.waitForElementToBeVisible(driver, deleteUserGroupConfirmationText);
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
		}else{
			System.out.println("Yes button is not displayed in confirmation pop up....");
		}
	}

	//Method to check if a user group exist
	public boolean isUserGroupDeleted(String strUserGroupName){
		try{
			System.out.println("User group with name: "+returnUserGroupNameElement(strUserGroupName).getText()+ " is deleted...");	
		}catch(Exception e){
			e.printStackTrace();
		}
		return returnUserGroupNameElement(strUserGroupName).isDisplayed();	
	}

	// Method to click Duplicate button
	public void clickDuplicateButtonOnToolBar() {
		if(toolBarDuplicateUserGroupButton.isDisplayed()){
			//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			CommonActions.waitForElementToBeVisible(driver, toolBarDuplicateUserGroupButton);
			CommonActions.clickElementToHandleStaleElementException(toolBarDuplicateUserGroupButton);	
			System.out.println("Clicked Duplicate button...");	
		}else{
			System.out.println("Failed to click Duplicate button...");
		}		
	}

	//Method to set duplicate user group name
	public void setDuplicateUserGroupName(String duplicateUserGroupName) {
		if(nameField.isDisplayed()){
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			nameField.clear();
			nameField.click();
			nameField.sendKeys(duplicateUserGroupName);
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
	public void createDuplicateUserGroup(String strDuplicateUserGroupName){
		//selectUserGroup(strDuplicateUserGroupName);
		clickDuplicateButtonOnToolBar();
		setDuplicateUserGroupName(strDuplicateUserGroupName);
		clickDuplicateUserGroupButton();
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
	}

	//Method to select duplicate user group
	public void selectDuplicateUserGroup(String strDuplicateUserGroupName){
		navigateToUserGroupsOption();
		CommonActions.scrollDownVertically(driver, returnUserGroupNameElement(strDuplicateUserGroupName));
		try{
			if(returnUserGroupNameElement(strDuplicateUserGroupName).isDisplayed()){
				JavascriptExecutor js = (JavascriptExecutor) driver;
				//This will scroll the page Horizontally till the element is found
				js.executeScript("arguments[0].scrollIntoView();", returnUserGroupNameElement(strDuplicateUserGroupName));
				CommonActions.waitForElementToBeClickable(driver, returnUserGroupNameElement(strDuplicateUserGroupName));
				driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
				//CommonActions.clickElementToHandleStaleElementException(duplicateUserGroupName);
				//System.out.println("returnDuplicateUGNameElement(TestData.DUPLICATE_USERGROUP_NAME)     "+returnUserGroupNameElement(strDuplicateUserGroupName));
				CommonActions.clickElementToHandleStaleElementException(returnUserGroupNameElement(strDuplicateUserGroupName));
				System.out.println("Clicked on user group with name: "+strDuplicateUserGroupName);
			}else{
				System.out.println("Failed to click on user group with name: "+strDuplicateUserGroupName);
			}
		}catch(Exception e){
			e.printStackTrace();
		}	
	}

	//Method to get duplicate user group name
	public String getDuplicateUserGroupName(String strDuplicateUserGroupName){
		CommonActions.waitForElementToBeVisible(driver, returnUserGroupNameElement(strDuplicateUserGroupName));
		//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		System.out.println("Duplicate user group is created successfully with name:  "+returnUserGroupNameElement(strDuplicateUserGroupName).getText());
		return returnUserGroupNameElement(strDuplicateUserGroupName).getText();
	}


	//Method to delete duplicate user group
	public void deleteDuplicateUserGroup(String strDuplicateUserGroupName){
		//selectDuplicateUserGroup(strDuplicateUserGroupName);
		CommonActions.scrollDownVertically(driver);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		CommonActions.scrollDownVertically(driver, deleteUserGroupIcon);
		if(deleteUserGroupIcon.isDisplayed()){
			CommonActions.waitForElementToBeClickable(driver, deleteUserGroupIcon);
			CommonActions.clickElementToHandleStaleElementException(deleteUserGroupIcon);			
			verifyConfirmationPopUp();
			verifyConfirmationText();
			clickYesInConfirmationPopUp();
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		}else{
			System.out.println("Delete user group button is not displayed...");
		}
	}

	//Method to check if a duplicate user group exist
	public boolean isDuplicateUserGroupDeleted(String strDuplicateUserGroupName){
		//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		try{
			System.out.println("User group with name: "+returnUserGroupNameElement(strDuplicateUserGroupName).getText()+ " is deleted...");	
		}catch(Exception e){
			e.printStackTrace();
		}
		return returnUserGroupNameElement(strDuplicateUserGroupName).isDisplayed();	
	}

	//Method to navigate to user groups option
	public void navigateToUserGroupsOption(){
		//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.navigate().refresh();
		CommonActions.waitForElementToBeClickable(driver, managementElement);
		CommonActions.clickElementToHandleStaleElementException(managementElement);
		CommonActions.waitForElementToBeClickable(driver, userGroupsElement);
		CommonActions.clickElementToHandleStaleElementException(userGroupsElement);		
	}



	//Method to verify error pop up
	public boolean isErrorPopUpDisplayed(){
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		if(errorPopUp.isDisplayed()){
			//System.out.println("A pop up with title "+errorPopUp.getText()+ " appeared...");
			return true;
		}else{
			System.out.println("A pop up with title "+errorPopUp.getText()+ " did not appear...");
			return false;
		}
	}

	//Method to verify error message in error pop up 
	public boolean isErrorMessageDisplayed(){
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		if(userGroupAlreadyExistsConfirmationText.isDisplayed()){
			//System.out.println("Error pop up appear with text "+userGroupAlreadyExistsConfirmationText.getText()+ " appeared...");
			return true;
		}else{
			System.out.println("Error pop up appear with text "+userGroupAlreadyExistsConfirmationText.getText()+ " did not appear...");
			return false;
		}		
	}

	//Method to validate duplicate user group
	public void validateExistingUserGroup(){
		if(isErrorPopUpDisplayed() && isErrorMessageDisplayed()){		
			CommonActions.waitForElementToBeClickable(driver, buttonOK);
		}else{
			System.out.println("Error pop up appear with text "+userGroupAlreadyExistsConfirmationText.getText()+ " did not appear...");
		}
	}

}
