/**
 * 
 */
package com.buddi.hdportal.pages;

import java.util.Random;
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
public class HDPortalManageKnowledgeBasePage {
	WebDriver driver;

	//Constructor
	public HDPortalManageKnowledgeBasePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Using FindBy for locating elements

	//Web elements related to manage knowledge base
	@FindBy(xpath = "//div[contains(text(),'Management')]") 
	private WebElement managementElement;
	@FindBy(xpath = "//div[contains(text(),'Knowledge Base')]") 
	private WebElement knowledgeBaseElement;
	@FindBy(xpath = "//div[contains(text(),'Manage Knowledge Base')]") 
	private WebElement knowledgeBasePanelTitleElement;

	//Web elements related to add knowledge base
	@FindBy(xpath = "//span[contains(text(),'Add')]") 
	private WebElement toolBarAddKnowledgeBaseButton;
	@FindBy(xpath = "//div[contains(text(),'New Knowledge Base')]") 
	private WebElement newKnowledgeBaseDialogTitleElement;
	@FindBy(xpath = "//input[@name='user']") 
	private WebElement searchField;
	@FindBy(xpath = "//input[@name='item_number']") 
	private WebElement itemNumberField;
	@FindBy(xpath = "//*[@name='description']") 
	private WebElement subjectField;
	@FindBy(xpath = "//*[@name='commentary']") 
	private WebElement descriptionField;
	@FindBy(xpath = "*//span[@class='x-btn-inner x-btn-inner-default-small' and contains(text(),'Add')]")
	private WebElement addButton;
	@FindBy(xpath = "//div[contains(text(),'Record created')]") 
	private WebElement knowledgeBaseAddedMessage;

	//Web elements related to edit knowledge base
	@FindBy(xpath = "//span/i[@class='x-fa fa-edit']")
	private WebElement editButton;
	@FindBy(xpath = "//div[contains(text(),'Edit Knowledge Base ~')]") 
	private WebElement editKnowledgeBaseDialogTitleElement;
	@FindBy(xpath = "*//span[@class='x-btn-inner x-btn-inner-default-small' and contains(text(),'Update')]")
	private WebElement updateButton;



	//Method accepts knowledge base name and returns knowledge base name element
	public WebElement returnKnowledgeBaseNameElement(String knowledgeBaseName){
		//knowledgeBaseName = knowledgeBaseName+randomInt;
		WebElement knowledgeBaseNameElement = driver.findElement(By.xpath("//div[contains(@class,'x-grid-cell-inner')][contains(text(),'"+knowledgeBaseName+"')]"));
		return knowledgeBaseNameElement;
	}

	//Method accepts knowledge base subject  and returns knowledge base subject element
	public String returnKnowledgeBaseSubject(String knowledgeBaseSubject){
		return subjectField.getText();
	}

	//Method accepts knowledge base description  and returns knowledge base description element
	public String returnKnowledgeBaseDescription(String knowledgeBaseDescription){
		return descriptionField.getText();
	}

	// Method to click LHS menu option
	public void clickManagementMenu() {
		CommonActions.waitForElementToBeVisible(driver, managementElement);
		managementElement.click();
		System.out.println("Management menu is expanded...");
	}

	// Method to click Knowledge Base option
	public void clickKnowledgeBaseOption() {
		CommonActions.waitForElementToBeVisible(driver, knowledgeBaseElement);
		knowledgeBaseElement.click();
		System.out.println("Clicked Knowledge Base option...");
	}

	// Method to verify Knowledge Base panel title
	public String verifyKnowledgeBasePanelTitle() {
		CommonActions.waitForElementToBeVisible(driver, knowledgeBasePanelTitleElement);
		System.out.println(knowledgeBasePanelTitleElement.getText()+" knowledge base panel title is displayed...");
		return knowledgeBasePanelTitleElement.getText();
	}

	// Method to click Add knowledge base tool bar button
	public void clickKnowledgeBaseButtonOnToolBar() {
		driver.navigate().refresh();
		navigateToKnowledgeBaseOption();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		if(toolBarAddKnowledgeBaseButton.isDisplayed()){
			System.out.println("Add tool bar button to add knowledge base is displayed...");
			CommonActions.waitForElementToBeVisible(driver, toolBarAddKnowledgeBaseButton);
			toolBarAddKnowledgeBaseButton.click();
			System.out.println("Clicked Add button to add knowledge base...");
		}else{
			System.out.println("Add tool bar button to add knowledge base is not displayed...");
		}
	}


	// Method to set item number
	public void setItemNumber(String itemNumber) {
		CommonActions.waitForElementToBeVisible(driver, itemNumberField);
		itemNumberField.click();
		itemNumberField.clear();
		itemNumberField.sendKeys(itemNumber);
		System.out.println("Entered item number is..."+itemNumber);		
	}


	// Method to set subject
	public void setSubject(String subject) {
		CommonActions.waitForElementToBeVisible(driver, subjectField);
		subjectField.click();
		subjectField.clear();
		subjectField.sendKeys(subject);
		System.out.println("Entered subject is..."+subject);		
	}


	// Method to set description
	public void setDescription(String description) {
		CommonActions.waitForElementToBeVisible(driver, descriptionField);
		descriptionField.click();
		descriptionField.clear();
		descriptionField.sendKeys(description);
		System.out.println("Entered description is..."+description);		
	}


	//Method to create knowledge base
	public void createKnowledgeBase(String strItemNumber, String strSubject, String strDescription){
		setItemNumber(strItemNumber);
		setSubject(strSubject);
		setDescription(strDescription);
		if(addButton.isEnabled()){	
			System.out.println("Add button to create KB is enabled "+addButton.isEnabled());		
			CommonActions.waitForElementToBeClickable(driver, addButton);
			CommonActions.clickElementToHandleStaleElementException(addButton);
			driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
			//Below action code works fine
			/*Actions builder = new Actions(driver);
			builder.sendKeys(Keys.TAB).build().perform();
			builder.sendKeys(Keys.SPACE).build().perform();*/
			CommonActions.waitForElementToBeVisible(driver, returnKnowledgeBaseNameElement(strItemNumber));			
		}else{
			System.out.println("Add button to create KB is not enabled "+addButton.isEnabled());
		}
	}

	//Method to search knowledge base by item name
	public String searchKnowledgeBaseByItemName(String strKnowledgeBaseName){
		CommonActions.waitForElementToBeVisible(driver, searchField);
		searchField.click();
		searchField.clear();
		searchField.sendKeys(strKnowledgeBaseName);
		CommonActions.waitForElementToBeVisible(driver, returnKnowledgeBaseNameElement(strKnowledgeBaseName));
		System.out.println("Knowledge base with name "+returnKnowledgeBaseNameElement(strKnowledgeBaseName).getText()+" is searched and is displayed...");
		return returnKnowledgeBaseNameElement(strKnowledgeBaseName).getText();
	}

	//Method to get knowledge base name
	public String getKnowledgeBaseItemName(String strKnowledgeBaseName){
		CommonActions.waitForElementToBeVisible(driver, returnKnowledgeBaseNameElement(strKnowledgeBaseName));
		System.out.println("Knowledge base with name "+returnKnowledgeBaseNameElement(strKnowledgeBaseName).getText()+" is present...");
		return returnKnowledgeBaseNameElement(strKnowledgeBaseName).getText();
	}

	//Method to select knowledge base
	public void selectKnowledgebase(String strKnowledgeBaseName){
		//navigateToKnowledgeBaseOption();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		if(returnKnowledgeBaseNameElement(strKnowledgeBaseName).isDisplayed()){
			CommonActions.waitForElementToBeClickable(driver, returnKnowledgeBaseNameElement(strKnowledgeBaseName));
			CommonActions.clickElementToHandleStaleElementException(returnKnowledgeBaseNameElement(strKnowledgeBaseName));
			System.out.println("Clicked/Selected on knowledge base with name: "+strKnowledgeBaseName);
		}else{
			System.out.println("Failed to click on knowledge base with name: "+strKnowledgeBaseName);
		}
	}


	//Method to navigate to knowledge base option
	public void navigateToKnowledgeBaseOption(){
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.navigate().refresh();
		CommonActions.waitForElementToBeClickable(driver, managementElement);
		CommonActions.clickElementToHandleStaleElementException(managementElement);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		CommonActions.waitForElementToBeClickable(driver, knowledgeBaseElement);
		CommonActions.clickElementToHandleStaleElementException(knowledgeBaseElement);		
	}


	// Method to click edit button
	public void clickEditButton() {
		CommonActions.waitForElementToBeVisible(driver, editButton);
		editButton.click();
		descriptionField.clear();
		System.out.println("Edit button is clicked...");		
	}


}
