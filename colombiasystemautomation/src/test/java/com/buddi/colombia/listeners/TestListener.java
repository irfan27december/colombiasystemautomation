package com.buddi.colombia.listeners;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buddi.colombia.extentreports.ExtentManager;
import com.buddi.colombia.extentreports.ExtentTestManager;
import com.buddi.colombia.utilities.Log;

public class TestListener implements ITestListener {
	public static ExtentTest logger;
	WebDriver driver;
	@Override
	public void onTestStart(ITestResult result) { 
		Log.info(("*** Running test method " + result.getMethod().getMethodName() + "..."));
		ExtentTestManager.startTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Log.info("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
		String testName = result.getMethod().getMethodName() + " test passed...";
		ExtentTestManager.getTest().log(Status.PASS, testName);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Log.info("*** Test execution " + result.getMethod().getMethodName() + " failed...");
		//ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
		//Show exception in the report for the failed test cases
		//ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable().toString());
		ExtentTestManager.getTest().log(Status.FAIL, result.getName() + " test is failed " +  result.getThrowable());
		/*try {
			logger.log(Status.FAIL, "Failed "+logger.addScreenCaptureFromPath(captureScreen(driver)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}


	@Override
	public void onTestSkipped(ITestResult result) {
		Log.info("*** Test " + result.getMethod().getMethodName() + " skipped...");
		//ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");		
		ExtentTestManager.getTest().log(Status.SKIP, result.getName() + " test is skipped " +  result.getThrowable());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		Log.info("*** Test failed but within percentage % " + result.getMethod().getMethodName());
	}

	@Override
	public void onStart(ITestContext context) {
		Log.info("Test started");
		Log.info("*** Test Suite " + context.getName() + " started ***");
	}

	@Override
	public void onFinish(ITestContext context) {
		Log.info(("*** Test Suite " + context.getName() + " ending ***"));
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();		
	}


	public String getcurrentdateandtime(){
		String str = null;
		try{
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS");
			Date date = new Date();
			str= dateFormat.format(date);
			str = str.replace(" ", "").replaceAll("/", "").replaceAll(":", "");
		}
		catch(Exception e){
		}
		return str;
	}

	public String captureScreen(WebDriver driver) throws IOException {

		TakesScreenshot screen = (TakesScreenshot) driver;
		File src = screen.getScreenshotAs(OutputType.FILE);
		System.out.println("src   "+src);
		String reportFileName = "Colombia-System-Test-Automaton-Report"+".html";
		String fileSeperator = System.getProperty("file.separator");
		String reportFilepath = System.getProperty("user.dir") +fileSeperator+ "test-report";
		String reportFileLocation =  reportFilepath +fileSeperator+ reportFileName;


		String dest = reportFilepath+getcurrentdateandtime()+".png";
		File target = new File(dest);
		System.out.println("target   "+target);
		FileUtils.copyFile(src, target);
		System.out.println("dest   "+dest);
		return dest;
	}	
}

