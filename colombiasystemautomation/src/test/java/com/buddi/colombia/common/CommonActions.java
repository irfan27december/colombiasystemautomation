package com.buddi.colombia.common;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Class which contains the common webdriver actions
 * 
 * @author Irfan
 *
 */
public class CommonActions {
	static Robot robot;

	//Method to select drop down option by value
	public static void selectByValue(WebDriver driver, WebElement element, String value) {
		Select select = new Select(element);
		element.click();
		select.selectByValue(value);
	}

	//Method to select drop down option by visible test
	public static void selectByVisibleText(WebDriver driver, WebElement element, String text) {
		Select select = new Select(element);
		element.click();
		select.selectByVisibleText(text);
	}

	//Method to select drop down option by index
	public static void selectByIndex(WebDriver driver, WebElement element, int index) {
		Select select = new Select(element);
		element.click();
		select.selectByIndex(index);
	}

	//Method to get drop down values
	public static List<WebElement> getDropDownValues(WebDriver driver, WebElement element) {
		Select select = new Select(element);
		element.click();
		List<WebElement> lisofvalues = select.getOptions();
		return lisofvalues;
	}

	//Method to wait for element to be clickable
	public static void waitForElementToBeClickable(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 50000);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	//Method to wait for element to be visible 
	public static void waitForElementToBeVisible(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 50000);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	//Method to wait for element to be visible by text
	public static void waitForElementToBeVisibleByText(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 50000);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}


	//Method to wait for element for its presence
	public static void waitUntil(WebDriver driver, By locator, int timeout) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeout, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	//Method to wait till button is enabled
	public static void waitUntilButtonEnabled(WebDriver driver, By locator, int timeout) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeout, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	//Method to uplaod file
	public static void fileUpload(String uploadPath) throws InterruptedException, AWTException {
		Thread.sleep(4000);
		setClipboardData(uploadPath);
		// native key strokes for CTRL, V and ENTER keys
		robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	//Method to set clipboard data
	public static void setClipboardData(String string) {
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

	//Method to click element using Javascript
	public static void javaScriptClick(WebDriver driver, WebElement element ) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click()", element);
	}


	//Use ExpectedConditions.refreshed to avoid StaleElementReferenceException and retrieve the element again. 
	//This method updates the element by redrawing it and we can access the referenced element
	public static void waitForElementToBeRefreshed(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 50000);
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(element)));
	}

	//Method to handle stale element exception and click element
	public static void clickElementToHandleStaleElementException(WebElement element) {
		int counter = 0; 
		do{
			try{
				if(element.isDisplayed() && element.isEnabled() ){
					counter = counter + 1; 
					element.click();
					break;
				}
			}catch(StaleElementReferenceException ex){
				element.click();
				ex.printStackTrace();
			}
		}while(counter == 0);
	}

	/*How below method works.
	You need to pass three parameter
	1- Driver- pass the driver reference, which will find the element.
	2- Xpath – pass the xpath of the element, which you want to find of the page.
	3- Time- pass the time it will keep polling after each second and if max limit exceeds then it will throw null pointer exception*/
	//http://learn-automation.com/best-way-to-handle-synchronization-in-selenium-webdriver/

	public static WebElement isElementPresent(WebDriver driver,  String xpath, int time){
		WebElement element = null;
		for(int i = 0; i < time; i++){
			try{
				element = driver.findElement(By.xpath(xpath));
				break;
			}
			catch(Exception e){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					System.out.println("Waiting for element to appear on DOM");
				}
			}
		}
		return element;
	}

	//Method to select auto suggestion value
	public static void autoSuggestionMethod(WebDriver driver){
		Actions action = new Actions(driver);
		action.sendKeys(Keys.DOWN).perform();
		//driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		action.sendKeys(Keys.ENTER).perform();
		//driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
	}

	//To zoom In page 4 time using CTRL and + keys.
	public static void zoomIn(WebDriver driver){		
		for(int i = 0; i < 4; i++){   
			driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));
		}
	}
	//To zoom out page 4 time using CTRL and - keys.
	public static void zoomOut(WebDriver driver){		
		for(int i = 0; i < 4; i++){
			driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		}
	}
	//To set browser to default zoom level 100%
	public static void set100(WebDriver driver){		
		driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, "0"));
	}


	//To zoom out page 4 time using CTRL and - keys.
	public static void zoomInBy(WebDriver driver){	
		String zoomInJS = "document.body.style.zoom='90%'"; 
		//String zoomInJS = "document.body.style.zoom='"+zoomInPercentage+"'"; 
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript(zoomInJS); 
	}

	//Method for zoom actions
	public static void zoomActions(WebDriver driver){
		driver.findElement(By.tagName("html")).sendKeys(
				Keys.chord(Keys.CONTROL, Keys.ADD));
		driver.findElement(By.tagName("html")).sendKeys(
				Keys.chord(Keys.CONTROL, Keys.ADD));
		driver.findElement(By.tagName("html")).sendKeys(
				Keys.chord(Keys.CONTROL, Keys.ADD));
		driver.findElement(By.tagName("html")).sendKeys(
				Keys.chord(Keys.CONTROL, Keys.ADD));
		driver.findElement(By.tagName("html")).sendKeys(
				Keys.chord(Keys.CONTROL, Keys.ADD));
		driver.findElement(By.tagName("html")).sendKeys(
				Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		driver.findElement(By.tagName("html")).sendKeys(
				Keys.chord(Keys.CONTROL, "0"));
	}

	//Method for zoom out action
	public static void zoomOut() throws AWTException{
		robot = new Robot();
		System.out.println("About to zoom out");
		for (int i = 0; i < 2; i++) {
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		}
	}

	//Method for zoom in action
	public static void zoomIn() throws AWTException{
		robot = new Robot();
		System.out.println("About to zoom in");
		for (int i = 0; i < 2; i++) {
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ADD);
			robot.keyRelease(KeyEvent.VK_ADD);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		}

	}

	// This  method will scroll down the page by  1000 pixel vertical	
	public static void scrollDownVertically(WebDriver driver){
		JavascriptExecutor js = (JavascriptExecutor) driver;	
		js.executeScript("window.scrollBy(0,1000)");
	}

	// This  method will scroll the page till the element is found
	public static void scrollDownVertically(WebDriver driver, WebElement element){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}

}
