package com.buddi.colombiaapp;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class AppiumBaseTest {
	AppiumServerJava appiumServer;
	AndroidDriver<AndroidElement> driver;
	WebDriverWait wait;
	File appDir;
	File appName;
	DesiredCapabilities cap;

	@BeforeTest
	public void startAppriumServer(){
		System.out.println("In startAppriumServer method....");
		appiumServer = new AppiumServerJava();
		//Check if Appium Server is running
		if(appiumServer.checkIfServerIsRunnning(appiumServer.appiumServerPort) == true){
			System.out.println("Appium Server already running on port - " + appiumServer.appiumServerPort);
			System.out.println("Stopping Appium Server running on port - " + appiumServer.appiumServerPort);
			appiumServer.stopServer();
		}else{
			System.out.println("Starting Appium Server running on port - " + appiumServer.appiumServerPort);
			appiumServer.startServer();		
		}

		appDir = new File("src/test/java/apps");
		appName = new File(appDir, "columbia-debug.apk");

		cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "SampleEmulator");
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
			driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait = new WebDriverWait(driver, 20);
	}


	@AfterTest(alwaysRun = true)
	public void stopAppriumServer(){
		appiumServer.stopServer();
	}

}
