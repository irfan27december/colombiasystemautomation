package com.buddi.colombiaapp.tests;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.colombia.utilities.ReadProperties;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class AppiumBaseTest {
	ReadProperties readProperties = new ReadProperties();
	AppiumServerJava appiumServer;
	AndroidDriver<AndroidElement> driver;
	WebDriverWait wait;
	
	DesiredCapabilities cap;
	int time = 20;
	File appDir;
	File appName;
	
	public static final String testDataExcelFileName = "testdata.xlsx";	
	public int appiumServerPort = Integer.parseInt(readProperties.getPropertyValue("APPIUMSERVER_PORT"));
	public String appiumServerIPAddress = readProperties.getPropertyValue("APPIUMSERVER_IPADDRESS");
	String appiumServerURL;

	@BeforeTest
	public void startAppriumServer(){
		System.out.println("In startAppriumServer method....");
		appiumServer = new AppiumServerJava();

		//Check if Appium Server is running
		if(appiumServer.checkIfServerIsRunnning(appiumServerPort) == true){
			System.out.println("Appium Server already running on port - " + appiumServerPort);
			System.out.println("Stopping Appium Server running on port - " + appiumServerPort);
			appiumServer.stopServer();
		}else{
			System.out.println("Starting Appium Server running on port - " + appiumServerPort);
			appiumServer.startServer();		
		}
		launchColombiaApp();		
	}
	
	//Method to launch app
	public void launchColombiaApp(){
		appDir = new File(readProperties.getPropertyValue("APP_DIRECTORY"));
		appName = new File(readProperties.getPropertyValue("APP_DIRECTORY"), readProperties.getPropertyValue("APP_NAME"));

		cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, readProperties.getPropertyValue("ANDROID_EMULATOR_DEVICE_NAME"));
		cap.setCapability(MobileCapabilityType.APP, appName.getAbsolutePath());
		cap.setCapability("autoGrantPermissions", true);
		cap.setCapability("autoAcceptAlerts", true);
		cap.setCapability("noReset", true);
		cap.setCapability("fullReset", false);
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "100");
		cap.setCapability("automationName", "UiAutomator2");
		cap.setCapability("AndroidMobileCapabilityType.APP_PACKAGE", "***");
		cap.setCapability("AndroidMobileCapabilityType.APP_ACTIVITY","***");
		cap.setCapability("takesScreenshot", true); 
		cap.setCapability("appWaitActivity","***");		

		try {
			appiumServerURL = "http://"+appiumServerIPAddress+":"+appiumServerPort+"/wd/hub";
			driver = new AndroidDriver<AndroidElement>(new URL(appiumServerURL), cap);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		wait = new WebDriverWait(driver, time);
	}


	@AfterTest(alwaysRun = true)
	public void stopAppriumServer(){
		appiumServer.stopServer();
	}

}
