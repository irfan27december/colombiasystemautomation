package com.buddi.colombia.utilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.buddi.colombia.common.CommonActions;
import java.awt.AWTException;

public class DatePicker {

	//Method for select the date from Date picker(calendar) using JS. 
	public static void datePcikerByJS(WebDriver driver, WebElement element, String dateVal) {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value','"+dateVal+"');", element);
	}

	/* @Click and open the calendar(Date Picker) 
	 * @Verify Date picker open or not
	 * @set Current month and year period (or)
	 * @set Future month and year period (or)
	 * @set Past month and year period
	 * @set date
	 */

	//Method to click and open the calendar(Date Picker) 
	public static void setDatePicker(WebDriver driver, WebElement elementclickDatePicker) {
		CommonActions.waitForElementToBeClickable(driver, elementclickDatePicker);
		if(elementclickDatePicker.isDisplayed()) {
			CommonActions.javaScriptClick(driver, elementclickDatePicker);
			Log.info("Date picker is clicked...");
		}
		else {
			Log.info("Failed to clicked date picker...");
		}
	}
	//Method for verify Date picker open or not
	public static boolean isDatePickerOPen(WebDriver driver, WebElement elementHeaderDatePicker) {
		CommonActions.waitForElementToBeVisible(driver, elementHeaderDatePicker);
		if(elementHeaderDatePicker.isDisplayed()){
			Log.info("Is Date picker open...!"+elementHeaderDatePicker.isDisplayed());
			return elementHeaderDatePicker.isDisplayed();
		}else{
			Log.info("Failed to open date picker...");
			return false;
		}
	}
	//Method to set Current month and year
	public static void setCurrentMonthYearPeriod(WebDriver driver, WebElement elementMonthYearPeriod, String monthYearPeriod) throws AWTException {
		CommonActions.waitForElementToBeVisible(driver, elementMonthYearPeriod);
		while(true){	
			if(elementMonthYearPeriod.isDisplayed()) {
				String str=elementMonthYearPeriod.getText();
				if(str.equalsIgnoreCase(str)) {
					Log.info("Selected month and year period as: " +monthYearPeriod);	
					break;	
				}	
			}	
		}
	}

	//Method to set future months period
	public static void setFutureMonthYearPeriod(WebDriver driver, WebElement elementMonthYearPeriod, String monthYearPeriod) throws AWTException {
		while(true){	
			CommonActions.waitForElementToBeVisible(driver, elementMonthYearPeriod);
			String str=elementMonthYearPeriod.getText();
			if(str.equalsIgnoreCase(str)) {
				Log.info("Select month and year is: " +monthYearPeriod);
				break;					
			}else {
				//Depends on current month calculate and set the loop 
				for (int i = 0; i < 2; i++) {
					CommonActions.clickCTRLPLUSRIGTARROW();
					Log.info("selected month and year is: " +monthYearPeriod);
				}
			}
		}
	}
	
	//Method to set past month period
	public static void setPastMonthYearPeriod(WebDriver driver, WebElement elementMonthYearPeriod, String monthYearPeriod) throws AWTException {
		while(true){	
			CommonActions.waitForElementToBeVisible(driver, elementMonthYearPeriod);
			String str=elementMonthYearPeriod.getText();
			if(str.equalsIgnoreCase(str)) {
				Log.info("selected month and year period as: " +monthYearPeriod);
				break;					
			}else {
				//Depends on current month calculate and set the loop 
				for (int i = 0; i < 2; i++) {
					CommonActions.clickCTRLPLUSLEFTARROW();
					Log.info("selected month and year period as: " +monthYearPeriod);
				}
			}
		}
	}
	//Method for select the data
	public static void selectDate(WebDriver driver, WebElement elementSetDate, String setDate) throws AWTException {
		CommonActions.waitForElementToBeClickable(driver, elementSetDate);
		if(elementSetDate.isDisplayed()) {
			CommonActions.javaScriptClick(driver, elementSetDate);
			Log.info("selected date is : "+setDate);
		}
		else {
			Log.info("Failed to select date...!!");
		}	
	}	
}

