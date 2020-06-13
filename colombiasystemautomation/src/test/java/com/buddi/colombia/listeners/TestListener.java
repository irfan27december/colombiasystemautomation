package com.buddi.colombia.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;
import com.buddi.colombia.extentreports.ExtentManager;
import com.buddi.colombia.extentreports.ExtentTestManager;

public class TestListener implements ITestListener {
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
		ExtentTestManager.startTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
		ExtentTestManager.getTest().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("*** Test execution " + result.getMethod().getMethodName() + " failed...");
		//ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
		//Show exception in the report for the failed test cases
		//ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable().toString());
		ExtentTestManager.getTest().log(Status.FAIL, result.getName() + " Test is failed " +  result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
		//ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
		
		ExtentTestManager.getTest().log(Status.SKIP, result.getName() + " Test is skipped " +  result.getThrowable());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
	}

	@Override
	public void onStart(ITestContext context) {
		Reporter.log("Test started");
		System.out.println("*** Test Suite " + context.getName() + " started ***");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();		
	}


	/*public String getcurrentdateandtime(){
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
		;
		TakesScreenshot screen = (TakesScreenshot) driver;
		File src = screen.getScreenshotAs(OutputType.FILE);

		String reportFileName = "Test-Automaton-Report"+".html";
		String fileSeperator = System.getProperty("file.separator");
		String reportFilepath = System.getProperty("user.dir") +fileSeperator+ "TestReport";
		String reportFileLocation =  reportFilepath +fileSeperator+ reportFileName;


		String dest = reportFilepath+getcurrentdateandtime()+".png";
		File target = new File(dest);
		FileUtils.copyFile(src, target);
		return dest;
	}	*/
}

