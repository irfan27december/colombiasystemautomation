package com.buddi.hdportal.tests;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.buddi.hdportal.pages.HDPortalHomePage;
import com.buddi.hdportal.pages.HDPortalLoginPage;
import com.colombia.utilities.ReadProperties;

import io.github.bonigarcia.wdm.WebDriverManager;



/**
 * @author irfan
 *
 */


public class HDPortalBaseTest {
	//ExtentTest extentLogger;
	//protected ExtentReports extent;
	public static WebDriver driver = null;
	ReadProperties properties = new ReadProperties();
	protected HDPortalLoginPage hdPortalLoginPage;	
	protected HDPortalHomePage hdPortalHomePage;
	
	public static final String testDataExcelFileName = "testdata.xlsx";


	//Set driver binary automatically using WebDriverManager
	@Parameters({"browser", "SuiteName"})
	@BeforeSuite
	public void setUp(@Optional("chromedriver") String browser, @Optional("ChromeSuite") String SuiteName) throws IOException{

		if(browser.equalsIgnoreCase("chromedriver")){
			//setup the chromedriver using WebDriverManager
			WebDriverManager.chromedriver().setup();

			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--start-maximized");
			chromeOptions.addArguments("--disable-web-security");
			chromeOptions.addArguments("--no-proxy-server");

			chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
			chromeOptions.addArguments("enable-automation"); 
			chromeOptions.addArguments("--no-sandbox"); 
			chromeOptions.addArguments("--disable-infobars"); 
			chromeOptions.addArguments("--disable-dev-shm-usage"); 
			chromeOptions.addArguments("--disable-browser-side-navigation"); 
			chromeOptions.addArguments("--disable-gpu");

			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			chromeOptions.setExperimentalOption("prefs", prefs);
			driver = new ChromeDriver(chromeOptions);
		}else if(browser.equalsIgnoreCase("firefoxdriver")){
			//setup the firefoxdriver using WebDriverManager
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}else{
			/*driver = new RemoteWebDriver(new URL(properties.getPropertyValue("eaglePortalURL")),
					getBrowserCapabilities(browser, SuiteName));
			driver.manage().window().maximize();*/	
		}
		
		driver.get(properties.getPropertyValue("HDPORTAL_URL"));			
		driver.manage().window().maximize();			
		driver.manage().timeouts().pageLoadTimeout(80, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	/*

	 *//**
	 * @author irfan
	 * Method to setup browser
	 * @throws IOException 
	 *//*
	@Parameters({"brower", "driverPath", "SuiteName"})
	@BeforeSuite
	public void setUp(@Optional("chrome") String browser, @Optional("drivers/chromedriver.exe") String driverPath, 
			@Optional("ChromeSuite") String SuiteName) throws IOException{
		if(browser.equalsIgnoreCase(browser)){
			System.setProperty("webdriver.chrome.driver", driverPath);
			//driver = new ChromeDriver();
			System.setProperty("org.uncommons.reportng.escape-output", "false");
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--start-maximized");
			chromeOptions.addArguments("--disable-web-security");
			chromeOptions.addArguments("--no-proxy-server");

			chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
			chromeOptions.addArguments("enable-automation"); 
			chromeOptions.addArguments("--no-sandbox"); 
			chromeOptions.addArguments("--disable-infobars"); 
			chromeOptions.addArguments("--disable-dev-shm-usage"); 
			chromeOptions.addArguments("--disable-browser-side-navigation"); 
			chromeOptions.addArguments("--disable-gpu");

			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			chromeOptions.setExperimentalOption("prefs", prefs);
			driver = new ChromeDriver(chromeOptions);
			driver.get(properties.getPropertyValue("eaglePortalURL"));			
			driver.manage().window().maximize();			
			driver.manage().timeouts().pageLoadTimeout(80, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}else{
			driver = new RemoteWebDriver(new URL(properties.getPropertyValue("eaglePortalURL")),
					getBrowserCapabilities(browser, SuiteName));
			driver.manage().window().maximize();	
		}

	}*/


	protected static DesiredCapabilities getBrowserCapabilities(String browserType, String SuiteName) {
		switch (browserType) {
		case "firefox":
			//logger.info("Opening firefox driver");
			return DesiredCapabilities.firefox();
		case "chrome":
			//logger.info("Opening chrome driver");
			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
			DesiredCapabilities chromeCaps = DesiredCapabilities.chrome();
			return chromeCaps;
		case "IE":
			//logger.info("Opening IE driver");
			return DesiredCapabilities.internetExplorer();
		default:
			//logger.info("browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
			return DesiredCapabilities.chrome();
		}
	}

	@Parameters({ "SuiteName" })
	@BeforeClass
	public void initialize(@Optional("ChromeSuite") String SuiteName) {
		hdPortalLoginPage = new HDPortalLoginPage(driver);
		hdPortalHomePage = new HDPortalHomePage(driver);
		
	}



	/*//Method to close browser
	@AfterSuite(alwaysRun=true)
	public void tearDown(){
		driver.manage().deleteAllCookies();
		driver.close();
		driver.quit();
	}*/

	public void cleanup() {
		hdPortalLoginPage.logoutHDPortal();	
	}

	@AfterSuite(alwaysRun = true)
	public void clean() throws InterruptedException {
		//logger.debug("after suite has been called");
		try {
			//cleanup();
		} catch (Exception e) {
			Reporter.log(e.getMessage());
			//logger.error("cleanup failed due to the error", e);
		} finally {
			driver.manage().deleteAllCookies();
			driver.close();
			driver.quit();
		}
	}
}