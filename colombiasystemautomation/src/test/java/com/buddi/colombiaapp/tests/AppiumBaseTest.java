package com.buddi.colombiaapp.tests;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.buddi.colombia.testdata.StringConstants;
import com.buddi.colombia.utilities.ReadProperties;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class AppiumBaseTest {
	ReadProperties readProperties = new ReadProperties();
	AppiumServerJava appiumServer;
	AndroidDriver<AndroidElement> driver;
	WebDriverWait wait;

	DesiredCapabilities capabilities;
	int TIME_SECONDS = 20;
	File appDir;
	File appName;

	//public static final String testDataExcelFileName = "testdata.xlsx";	
	public int appiumServerPort = Integer.parseInt(readProperties.getPropertyValue("appium.server.port"));
	public String appiumServerIPAddress = readProperties.getPropertyValue("appium.server.ipaddress");
	String appiumServerURL;

	@BeforeSuite
	public void startAppriumServer(){
		System.out.println("In startAppriumServer method....");
		appiumServer = new AppiumServerJava();

		//Check if Appium Server is running
		if(!appiumServer.checkIfAppiumServerIsRunnning(appiumServerPort)) {
			System.out.println("Appium Server is running on port - " + appiumServerPort);
			appiumServer.startAppiumServer();
			//appiumServer.stopAppiumServer();
		} else {
			System.out.println("Appium Server is already running on port - " + appiumServerPort);
		}
		launchColombiaApp();		
	}

	//Method to launch app
	public void launchColombiaApp(){
		appDir = new File(readProperties.getPropertyValue("app.directory"));
		appName = new File(readProperties.getPropertyValue("app.directory"), readProperties.getPropertyValue("app.name"));
		//Create object of DesiredCapabilities class
		capabilities = new DesiredCapabilities();	
		//http://appium.io/docs/en/writing-running-appium/caps/
		// Set android deviceName desired capability. Set your device name.
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, readProperties.getPropertyValue("android.emulator.device.name"));
		// Set android VERSION desired capability. Set your mobile device’s OS version.
		//capabilities.setCapability(CapabilityType.VERSION, “6.0.1”);
		// Set android platformName desired capability. It’s Android in our case here.
		//capabilities.setCapability("platformName", "Android");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, readProperties.getPropertyValue("emulator.platform"));
		capabilities.setCapability(MobileCapabilityType.APP, appName.getAbsolutePath());
		
		/*Grant permissions your app requires and grant them to the app on install. 
		Defaults to false. If noReset is true, this capability doesn't work.*/
		capabilities.setCapability("autoGrantPermissions", true);
		/*Accept all iOS alerts automatically if they pop up. 
		This includes privacy access permission alerts (e.g., location, contacts, photos). Default is false*/
		capabilities.setCapability("autoAcceptAlerts", true);
		// reset app state before this session
		capabilities.setCapability("noReset", true);
		//capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
		//Perform a complete reset
		capabilities.setCapability("fullReset", false);
		//capabilities.setCapability(MobileCapabilityType.FULL_RESET, true);
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "100");
		//Which automation engine to use, Appium (default), or UiAutomator2, Espresso, or UiAutomator1 for Android
		//capabilities.setCapability("automationName", "UiAutomator2");
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, readProperties.getPropertyValue("emulator.automation.name"));
		/*Set your application’s appPackage if you are using any other app
		Java package of the Android app you want to run. 
		By default this capability is received from the package manifest (@package attribute value)*/
		/*capabilities.setCapability("AndroidMobileCapabilityType.APP_PACKAGE", "com.google.android.apps.nexuslauncher");*/
		capabilities.setCapability("AndroidMobileCapabilityType.APP_PACKAGE", "***");
		/*Activity name for the Android activity you want to launch from your package. 
		This often needs to be preceded by a . (e.g., .MainActivity instead of MainActivity).*/
		capabilities.setCapability("AndroidMobileCapabilityType.APP_ACTIVITY","***");
		//capabilities.setCapability("AndroidMobileCapabilityType.APP_ACTIVITY",".MainActivity");
		capabilities.setCapability("takesScreenshot", true); 
		/*Activity name/names, comma separated, for the Android activity you want to wait for. 
		By default the value of this capability is the same as for appActivity.*/
		capabilities.setCapability("appWaitActivity","***");		

		try {
			appiumServerURL = "http://"+appiumServerIPAddress+":"+appiumServerPort+"/wd/hub";
			driver = new AndroidDriver<AndroidElement>(new URL(appiumServerURL), capabilities);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		wait = new WebDriverWait(driver, TIME_SECONDS);
	}



	@AfterSuite(alwaysRun = true)
	public void stopAppiumServer(){	
		//capabilities.setCapability("noResetValue","false");
		//driver.removeApp(readProperties.getPropertyValue("APP_PACKAGE_NAME"));
		System.out.println("Stop driver");
		driver.quit();
		appiumServer.stopAppiumServer();
	}

}
