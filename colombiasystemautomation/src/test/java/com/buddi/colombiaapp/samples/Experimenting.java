package com.buddi.colombiaapp.samples;


import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Experimenting {

	private static AndroidDriver driver;
	public static void main(String[] args) throws MalformedURLException, InterruptedException {

		/*			File classpathRoot = new File(System.getProperty("user.dir"));
			File appDir = new File(classpathRoot, "/Apps/Amazon/");
			File app = new File(appDir, "in.amazon.mShop.android.shopping.apk");

			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
			capabilities.setCapability("deviceName", "Micromax A311");
			capabilities.setCapability("platformVersion", "4.4.2");
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("app", app.getAbsolutePath());
			capabilities.setCapability("appPackage", "in.amazon.mShop.android.shopping");
			capabilities.setCapability("appActivity", "com.amazon.mShop.home.HomeActivity");

			driver = new AndroidDriver(new URL("https://127.0.0.1:4723/wd/hub"), capabilities);
			driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
			Thread.sleep(10000);
			driver.quit();*/

		File f = new File("src/test/java");
		File fs = new File(f, "columbia-debug.apk");

		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "SampleEmulator");
		cap.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
		cap.setCapability("autoGrantPermissions", true);
		//caps.setCapability("autoAcceptAlerts", true);
		//cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		cap.setCapability("noReset", true);
	    cap.setCapability("fullReset", false);
	    cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "100");
	    cap.setCapability("automationName", "UiAutomator2");
		cap.setCapability("AndroidMobileCapabilityType.APP_PACKAGE", "***");
		cap.setCapability("AndroidMobileCapabilityType.APP_ACTIVITY","***");
		cap.setCapability("takesScreenshot", true); 
		cap.setCapability("appWaitActivity","***");
		
		
		
		AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		WebDriverWait wait= new WebDriverWait(driver,20);
		//driver.execute(AndroidKey.HOME);
		Thread.sleep(10000);
		/*if(driver.findElementByXPath("//android.widget.TextView[@text='Colombia Test']").isDisplayed()){
			driver.findElementByXPath("//android.widget.TextView[@text='Colombia Test']").click();
			System.out.println("Clicked Colombia Test");
			driver.findElementByXPath("//android.widget.button[@text='ALLOW']").click();
			driver.findElementByXPath("//android.widget.button[@text='ALLOW']").click();
			driver.findElementByXPath("//android.widget.button[@text='ALLOW']").click();*/
		/*driver.findElementByXPath("//android.widget.EditText[@index='4']").click();
			driver.findElementByXPath("//android.widget.EditText[@index='4']").sendKeys("irfan.ahmed@buddi.co.uk");
			driver.findElementByXPath("//android.widget.EditText[@index='5']").click();
			driver.findElementByXPath("//android.widget.EditText[@index='5']").sendKeys("MIA@27dec");
			driver.findElementByXPath("//android.widget.button[@text='LOGIN']").click();*/
		/*driver.findElementByXPath("//android.widget.EditText[4]").sendKeys("irfan.ahmed@buddi.co.uk");
		driver.findElementById("com.buddi.columbia.debug:id/edit_email").click();
		driver.findElementById("com.buddi.columbia.debug:id/edit_email").setValue("irfan.ahmed@buddi.co.uk");*/
		
		
		
		//driver.findElement(By.xpath("//*[@text='Email']")).setValue("irfan.ahmed@buddi.co.uk");
		/*driver.findElement(By.id("edit_email")).setValue("irfan.ahmed@buddi.co.uk");
		Thread.sleep(5000);
		driver.findElement(By.id("edit_password")).setValue("MIA@27dec");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@text='LOGIN']")).click();*/
		/*driver.findElement(By.id("com.buddi.columbia.debug:id/edit_email")).click();
		driver.findElement(By.id("com.buddi.columbia.debug:id/edit_email")).setValue("irfan.ahmed@buddi.co.uk");
		driver.findElement(By.id("com.buddi.columbia.debug:id/edit_password")).click();
		driver.findElement(By.id("com.buddi.columbia.debug:id/edit_password")).setValue("MIA@27dec");
		driver.findElementByXPath("//android.widget.button[@text='LOGIN']").click();*/
		//Thread.sleep(10000);
		
		
		//driver.findElement(By.xpath("//*[@class='android.widget.EditText'][@index='0']")).sendKeys("irfan.ahmed@buddi.co.uk");
		/*if(driver.findElement(By.xpath("//android.widget.EditText[@clickable='true']")).isDisplayed()){
			driver.findElement(By.xpath("//android.widget.EditText[@clickable='true']")).setValue("irfan.ahmed@buddi.co.uk");
			System.out.println("Element found....");
			Thread.sleep(10000);
		}else{
			System.out.println("Element not found....");
		}*/
		
		/*Thread.sleep(5000);
		// wait 5 - 10 sec here before check as far as 'driver.findElement' is immediate command that does not wait element to appear 
		System.out.println("Elemnt present: "+driver.findElement(MobileBy.id("edit_email")).isDisplayed());
		List<AndroidElement> elements = driver.findElements(MobileBy.id("edit_email"));
		System.out.println("elements size: " + elements.size());*/
		driver.findElement(MobileBy.id("edit_email")).click();
		driver.findElement(MobileBy.id("edit_email")).sendKeys("irfan.ahmed@buddi.co.uk");
		Thread.sleep(5000);
		driver.findElement(MobileBy.id("edit_password")).click();
		driver.findElement(MobileBy.id("edit_password")).sendKeys("MIA@27dec");
		Thread.sleep(5000);
		//driver.findElement(MobileBy.xpath("//android.widget.button[@text='LOGIN']")).click();
		driver.findElement(MobileBy.id("button")).click();
		Thread.sleep(5000);
		
		/*}else{
			System.out.println("Unable to find Colombia Test");
		}*/
		Thread.sleep(5000);
		//driver.findElementByXPath("//android.widget.TextView[@text='Colombia Test']").click();
		//Thread.sleep(10000);
		driver.quit();


	}

}
