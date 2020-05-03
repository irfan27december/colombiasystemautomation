package com.buddi.colombiaapp;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.android.*;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class BaseTest {

	/*public static AndroidDriver<AndroidElement>  capabilities() throws MalformedURLException{
		File f = new File("src/test/java");
		File fs = new File(f, "APIDemos-debug.apk");

		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "SampleEmulator");
		cap.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
		//cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		//driver.execute(AndroidKey.HOME);
		return driver;
	}*/

	AndroidDriver<AndroidElement> driver;
	WebDriverWait wait;
	File appDir;
	File appName;
	DesiredCapabilities cap;

	AppiumDriverLocalService appiumService;
	private AppiumServiceBuilder builder;
	String appiumServiceUrl;


	private static String sdkPath = "C:\\Users\\irfanAppData\\Local\\Android\\Sdk\\";
	private static String adbPath = sdkPath + "platform-tools" + File.separator + "adb";
	private static String emulatorPath = sdkPath + "tools" + File.separator + "emulator";


	@BeforeTest
	public void setUpAndroidDriver(){
		//Reference: https://www.seleniumeasy.com/appium-tutorials/how-to-start-appium-server-programmatically
		/*appiumService = AppiumDriverLocalService.buildDefaultService();
		appiumService.start();*/
		AppiumServerJava appiumServer = new AppiumServerJava();
		int port = 4723;
		if(!appiumServer.checkIfServerIsRunnning(port)) {
			appiumServer.startServer();
			appiumServer.stopServer();
		} else {
			System.out.println("Appium Server already running on Port - " + port);
		}
		//Build the Appium service
		builder = new AppiumServiceBuilder();
		builder.withIPAddress("127.0.0.1");
		builder.usingPort(4723);
		builder.withCapabilities(cap);
		builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
		builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");


		//Start the server with the builder
		appiumService = AppiumDriverLocalService.buildService(builder);
		appiumService.start();

		appiumServiceUrl = appiumService.getUrl().toString();
		System.out.println("Appium Service Address : - "+ appiumServiceUrl);

		//launchEmulator("SampleEmulator");

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

	/* Starts an emulator for the provided AVD name
	 * 
	 * @param nameOfAVD
	 */
	public static void launchEmulator(String nameOfAVD) {
		System.out.println("Starting emulator for '" + nameOfAVD + "' ...");
		String[] aCommand = new String[] { emulatorPath, "-avd", nameOfAVD };
		try {
			Process process = new ProcessBuilder(aCommand).start();
			process.waitFor(180, TimeUnit.SECONDS);
			System.out.println("Emulator launched successfully!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public boolean checkIfServerIsRunnning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		} catch (IOException e) {
			//If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}	

	@AfterTest(alwaysRun = true)
	public void tearDown(){
		System.out.println("Stop driver");
		driver.quit();
		System.out.println("Stop appium service");
		appiumService.stop();
	}
}
