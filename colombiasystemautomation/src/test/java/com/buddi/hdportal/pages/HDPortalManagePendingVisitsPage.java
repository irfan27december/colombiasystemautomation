/**
 * 
 */
package com.buddi.hdportal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.buddi.colombia.common.CommonActions;

/**
 * @author irfan
 *
 */
public class HDPortalManagePendingVisitsPage {
	WebDriver driver;


	//Constructor
	public HDPortalManagePendingVisitsPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Using FindBy for locating elements

	//Web elements related to pending visits
	@FindBy(xpath = "//div[@class='x-treelist-item-text' and text()='Visits']") 
	private WebElement visitsMenuElement;
	@FindBy(xpath = "//div[@class='x-treelist-item-text' and text()='Pending']") 
	private WebElement pendingVisitsElement;
	@FindBy(xpath = "//div[@class='x-title-text x-title-text-default-framed x-title-item' and text()='Pending Visits']") 
	private WebElement pendingVisitsGridTitleElement;

	// Method to click LHS menu option
	public void clickVisitsMenu() {
		CommonActions.waitForElementToBeVisible(driver, visitsMenuElement);
		visitsMenuElement.click();
		System.out.println("Visits menu is expanded...");
	}

	// Method to click Pending option
	public void clickPendingOption() {
		CommonActions.waitForElementToBeVisible(driver, pendingVisitsElement);
		pendingVisitsElement.click();
		System.out.println("Clicked Pending option under Visits menu...");
	}

	// Method to verify Pending Visits Grid title
	public String verifyPendingVisitsGridTitle() {
		CommonActions.waitForElementToBeVisible(driver, pendingVisitsGridTitleElement);
		System.out.println(pendingVisitsGridTitleElement.getText()+" pending visit grid title is displayed...");
		return pendingVisitsGridTitleElement.getText();
	}

	//Method to click Add new visit tool bar button
	public void clickAddNewVisitToolBarButton(WebDriver driver) throws InterruptedException{
		if(pendingVisitsGridTitleElement.getText().equals("Pending Visits")){
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", 
					driver.findElement(By.xpath(".//span[@class = 'x-btn-inner x-btn-inner-default-toolbar-small' and contains(text(), 'Add')]")));	
		}else{
			System.out.println("Add new visit button not present...");
		}
	}


}
