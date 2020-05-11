/**
 * 
 */
package com.buddi.hdportal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.colombia.common.CommonActions;
import com.colombia.testdata.TestData;

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
	@FindBy(xpath = "*//span[contains(text(),'Add')]") 
	private WebElement addButton;
	@FindBy(xpath = "//div[contains(text(),'User group created')]") 
	private WebElement userGroupsAddedMessage;



	// Method to click LHS menu option
	public void clickManagementMenu() {
		CommonActions.waitForElementToBeVisible(driver, managementElement);
		managementElement.click();
		System.out.println("Management menu is expanded...");
	}

	// Method to click User Groups option
	public void clickUserGroups() {
		CommonActions.waitForElementToBeVisible(driver, userGroupsElement);
		userGroupsElement.click();
		System.out.println("Clicked Uer Groups option...");
	}

	// Method to verify user groups panel title
	public String verifyUserGroupsPanelTitle() {
		CommonActions.waitForElementToBeVisible(driver, userGroupsPanelTitleElement);
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
		//By.xpath("//div[@id='module-tabs-tab']//a[text()='Add']")
		/*WebElement addElement =new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Add')]")));
		Actions action =new Actions(driver);
		action.moveToElement(addElement).click().build().perform();*/
		
		try {
			Thread.sleep(5000);
			if(addButton.isEnabled()){	
				System.out.println("Add button is enabled "+addButton.isEnabled());
				//addButton.click();
				/*addButton.sendKeys(Keys.TAB);
				addButton.sendKeys(Keys.ENTER);*/
				
				Actions builder = new Actions(driver);
				builder.sendKeys(Keys.TAB).build().perform();
				builder.sendKeys(Keys.SPACE).build().perform();
			}else{
				System.out.println("Add button is not enabled "+addButton.isEnabled());
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
