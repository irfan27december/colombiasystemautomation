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


	//Method accepts knowledge base name and returns knowledge base name element
	public WebElement returnKnowledgeBaseNameElement(String knowledgeBaseName){
		WebElement knowledgeBaseNameElement = driver.findElement(By.xpath("//div[contains(@class,'x-grid-cell-inner')][contains(text(),'"+knowledgeBaseName+"')]"));
		return knowledgeBaseNameElement;
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
		CommonActions.waitForElementToBeVisible(driver, toolBarAddKnowledgeBaseButton);
		toolBarAddKnowledgeBaseButton.click();
		System.out.println("Clicked Add button to add knowledge base...");
	}


	// Method to set item number
	public void setItemNumber(String itemNumber) {
		CommonActions.waitForElementToBeVisible(driver, itemNumberField);
		itemNumberField.click();
		itemNumberField.clear();
		itemNumberField.sendKeys(itemNumber);
		System.out.println("Entered item number...");		
	}


	// Method to set subject
	public void setSubject(String subject) {
		CommonActions.waitForElementToBeVisible(driver, subjectField);
		subjectField.click();
		subjectField.clear();
		subjectField.sendKeys(subject);
		System.out.println("Entered subject...");		
	}


	// Method to set description
	public void setDescription(String description) {
		CommonActions.waitForElementToBeVisible(driver, descriptionField);
		descriptionField.click();
		descriptionField.clear();
		descriptionField.sendKeys(description);
		System.out.println("Entered description...");		
	}


	//Method to create knowledge base
	public void createKnowledgeBase(String strItemNumber, String strSubject, String strDescription){
		strItemNumber = strItemNumber+"_"+java.time.LocalTime.now();
		setItemNumber(strItemNumber);
		setSubject(strSubject);
		setDescription(strDescription);
		if(addButton.isEnabled()){	
			System.out.println("Add button is enabled "+addButton.isEnabled());		
			CommonActions.waitForElementToBeClickable(driver, addButton);
			CommonActions.clickElementToHandleStaleElementException(addButton);
			driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
			//Below action code works fine
			/*Actions builder = new Actions(driver);
			builder.sendKeys(Keys.TAB).build().perform();
			builder.sendKeys(Keys.SPACE).build().perform();*/
			CommonActions.waitForElementToBeVisible(driver, returnKnowledgeBaseNameElement(strItemNumber));			
		}else{
			System.out.println("Add button is not enabled "+addButton.isEnabled());
		}
	}

	//Method to get knowledge base name
	public String getKnowledgeBaseName(String strKnowledgeBaseName){
		CommonActions.waitForElementToBeVisible(driver, returnKnowledgeBaseNameElement(strKnowledgeBaseName));
		System.out.println("Knowledge base is created successfully with name:  "+returnKnowledgeBaseNameElement(strKnowledgeBaseName).getText());
		return returnKnowledgeBaseNameElement(strKnowledgeBaseName).getText();
	}

	//Method to select knowledge base
	public void selectKnowledgebase(String strKnowledgeBaseName){
		navigateToKnowledgeBaseOption();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		if(returnKnowledgeBaseNameElement(strKnowledgeBaseName).isDisplayed()){
			CommonActions.waitForElementToBeClickable(driver, returnKnowledgeBaseNameElement(strKnowledgeBaseName));
			CommonActions.clickElementToHandleStaleElementException(returnKnowledgeBaseNameElement(strKnowledgeBaseName));
			System.out.println("Clicked on knowledge base with name: "+strKnowledgeBaseName);
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
		CommonActions.waitForElementToBeClickable(driver, knowledgeBaseElement);
		CommonActions.clickElementToHandleStaleElementException(knowledgeBaseElement);		
	}


}
