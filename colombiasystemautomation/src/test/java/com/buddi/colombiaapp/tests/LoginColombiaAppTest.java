package com.buddi.colombiaapp.tests;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class LoginColombiaAppTest extends AppiumBaseTest{

	//private static AndroidDriver driver;
	//AndroidDriver<AndroidElement> driver;
	/*WebDriverWait wait;
	File appDir;
	File appName;
	DesiredCapabilities cap;*/

	/*@BeforeTest
		public void setUp() throws MalformedURLException, InterruptedException{
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
		}*/

	@Test(priority = 0)
	public void loginColombiaApp() throws InterruptedException{
		String phoneID = driver.findElement(MobileBy.id("txt_phone_id")).getAttribute("text");
		System.out.println("Phone ID is " +phoneID );

		driver.findElement(MobileBy.id("edit_email")).click();
		driver.findElement(MobileBy.id("edit_email")).sendKeys("irfan.ahmed@buddi.co.uk");
		driver.findElement(MobileBy.id("edit_password")).click();
		driver.findElement(MobileBy.id("edit_password")).sendKeys("MIA@27dec");
		driver.findElement(MobileBy.id("button")).click();
		Thread.sleep(5000);
		driver.findElement(MobileBy.className("android.widget.ImageView")).click();
		Thread.sleep(5000);
	}

	@Test(priority = 1)
	public void logOutColombiaApp() throws InterruptedException{
		driver.findElement(MobileBy.className("android.widget.ImageView")).click();
		Thread.sleep(5000);
	
	}


}
