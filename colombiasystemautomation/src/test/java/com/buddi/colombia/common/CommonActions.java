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
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	public static void selectByValue(WebDriver driver, WebElement element, String value) {
		Select select = new Select(element);
		element.click();
		select.selectByValue(value);
	}

	public static void selectByVisibleText(WebDriver driver, WebElement element, String text) {
		Select select = new Select(element);
		element.click();
		select.selectByVisibleText(text);
	}

	public static void selectByIndex(WebDriver driver, WebElement element, int index) {
		Select select = new Select(element);
		element.click();
		select.selectByIndex(index);
	}

	public static List<WebElement> getDropDownValues(WebDriver driver, WebElement element) {
		Select select = new Select(element);
		element.click();
		List<WebElement> lisofvalues = select.getOptions();
		return lisofvalues;
	}

	public static void waitForElementToBeClickable(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 50000);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void waitForElementToBeVisible(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 50000);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void waitForElementToBeVisibleByText(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 50000);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public static void waitUntil(WebDriver driver, By locator, int timeout) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeout, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public static void waitUntilButtonEnabled(WebDriver driver, By locator, int timeout) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeout, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public static void fileUpload(String uploadPath) throws InterruptedException, AWTException {
		Thread.sleep(4000);
		setClipboardData(uploadPath);
		// native key strokes for CTRL, V and ENTER keys
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	public static void setClipboardData(String string) {
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

	public static void javaScriptClick(WebElement element, WebDriver driver) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click()", element);
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
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}while(counter == 0);
	}


}
