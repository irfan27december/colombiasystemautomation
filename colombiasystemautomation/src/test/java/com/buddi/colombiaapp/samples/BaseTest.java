package com.buddi.colombiaapp.samples;

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

import com.buddi.colombiaapp.tests.AppiumServerJava;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
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
	DesiredCapabilities capabilities;

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
		//https://nishantverma.gitbooks.io/appium-for-android/content/starting_appium_server.html
		String osName = System.getProperty("os.name");
		System.out.println("OS name: "+osName);
		AppiumServerJava appiumServer = new AppiumServerJava();
		int port = 4723;
		if(!appiumServer.checkIfAppiumServerIsRunnning(port)) {
			appiumServer.startAppiumServer();
			appiumServer.stopAppiumServer();
		} else {
			System.out.println("Appium Server already running on Port - " + port);
		}
		//Build the Appium service
		builder = new AppiumServiceBuilder();
		builder.withIPAddress("127.0.0.1");
		builder.usingPort(4723);
		builder.withCapabilities(capabilities);
		builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
		builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");


		//Start the server with the builder
		appiumService = AppiumDriverLocalService.buildService(builder);
		appiumService.start();

		appiumServiceUrl = appiumService.getUrl().toString();
		System.out.println("Appium Service Address : - "+ appiumServiceUrl);

		//launchEmulator("SampleEmulator");

		appDir = new File("src/test/java/apps");
		//appName = new File(appDir, "columbia-debug.apk");
		appName = new File(appDir, "colombia-v100.apk");
		// Create object of DesiredCapabilities class
		capabilities = new DesiredCapabilities();
		// Set android deviceName desired capability. Set your device name.
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "SampleEmulator");
		
		// Set android VERSION desired capability. Set your mobile device’s OS version.
		//cap.setCapability(CapabilityType.VERSION, “6.0.1”);
		// Set android platformName desired capability. It’s Android in our case here.
		capabilities.setCapability("platformName", "Android");
		
		// Install app on the device.
		driver.installApp("/src/test/java/colombia-v100.apk");
		//capabilities.setCapability(MobileCapabilityType.APP, appName.getAbsolutePath());
		
		capabilities.setCapability("autoGrantPermissions", true);
		capabilities.setCapability("autoAcceptAlerts", true);
		capabilities.setCapability("noReset", true);
		capabilities.setCapability("fullReset", false);
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "100");
		capabilities.setCapability("automationName", "UiAutomator2");
		// Set your application’s appPackage if you are using any other app.
		capabilities.setCapability("AndroidMobileCapabilityType.APP_PACKAGE", "***");
		//capabilities.setCapability("AndroidMobileCapabilityType.APP_PACKAGE", "com.google.android.apps.nexuslauncher");
		capabilities.setCapability("AndroidMobileCapabilityType.APP_ACTIVITY","***");
		//capabilities.setCapability("AndroidMobileCapabilityType.APP_ACTIVITY",".MainActivity, .Settings");
		capabilities.setCapability("takesScreenshot", true); 
		capabilities.setCapability("appWaitActivity","***");		


		try {
			// It will launch app in android device
			driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		} catch (MalformedURLException e) {
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

	//Method to check if APPIUM server is running
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
		driver.removeApp("com.google.android.apps.nexuslauncher");
		System.out.println("Stop driver");
		driver.quit();
		System.out.println("Stop appium service");
		appiumService.stop();
	}
}
