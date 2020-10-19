package com.buddi.hdportal.tests;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.buddi.colombia.utilities.Log;
import com.buddi.colombia.utilities.ReadProperties;
import com.buddi.hdportal.pages.HDPortalHomePage;
import com.buddi.hdportal.pages.HDPortalLoginPage;
import com.buddi.hdportal.pages.HDPortalManageAddNewVisitPage;
import com.buddi.hdportal.pages.HDPortalManageAlertHistoryPage;
import com.buddi.hdportal.pages.HDPortalManageCancelVisitsPage;
import com.buddi.hdportal.pages.HDPortalManageCompleteVisitsPage;
import com.buddi.hdportal.pages.HDPortalManageCreditNotePage;
import com.buddi.hdportal.pages.HDPortalManageInProgressAlertsPage;
import com.buddi.hdportal.pages.HDPortalManageKnowledgeBasePage;
import com.buddi.hdportal.pages.HDPortalManageNewManualAlertsPage;
import com.buddi.hdportal.pages.HDPortalManagePendingVisitsPage;
import com.buddi.hdportal.pages.HDPortalManageUserGroupsPage;
import com.buddi.hdportal.pages.HDPortalManageVisitHistoryPage;

import io.github.bonigarcia.wdm.WebDriverManager;



/**
 * @author irfan
 *
 */


public class HDPortalBaseTest {

	public static WebDriver driver = null;
	ReadProperties properties = new ReadProperties();
	protected HDPortalLoginPage hdPortalLoginPage;	
	protected HDPortalHomePage hdPortalHomePage;
	protected HDPortalManageNewManualAlertsPage hdPortalManageNewManualAlertsPage;
	protected HDPortalManageInProgressAlertsPage hdPortalManageInProgressAlertsPage;
	protected HDPortalManageAlertHistoryPage hdPortalManageAlertHistoryPage;
	protected HDPortalManageUserGroupsPage hdPortalManageUserGroupsPage;
	protected HDPortalManageKnowledgeBasePage hdPortalManageKnowledgeBasePage;
	protected HDPortalManagePendingVisitsPage hdPortalManagePendingVisitsPage;
	protected HDPortalManageAddNewVisitPage hdPortalManageAddNewVisitPage;
	protected HDPortalManageCompleteVisitsPage hdPortalManageCompleteVisitsPage;
	protected HDPortalManageCancelVisitsPage hdPortalManageCancelVisitsPage;
	protected HDPortalManageVisitHistoryPage hdPortalManageVisitHistoryPage;
	protected HDPortalManageCreditNotePage hdPortalManageCreditNotePage;

	public static final String testDataExcelFileName = "testdata.xlsx";
	private static String log4jFileName = "log4.xml";
	private static String fileSeperator = System.getProperty("file.separator");
	private static String log4jFileFolderPath = System.getProperty("user.dir") +fileSeperator+ "resources";
	private static String log4jFileLocation =  log4jFileFolderPath +fileSeperator+ log4jFileName;


	//Set driver binary automatically using WebDriverManager
	@Parameters({"browser", "SuiteName"})
	@BeforeSuite
	public void setUp(@Optional("chromedriver") String browser, @Optional("ChromeSuite") String SuiteName) throws IOException, InterruptedException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		System.setProperty("currenttime", dateFormat.format(new Date()));
		// Provide Log4j configuration settings	 
		DOMConfigurator.configure(log4jFileLocation);
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
			Log.info("Chrome driver instantiated...");
		}else if(browser.equalsIgnoreCase("firefoxdriver")){
			//setup the firefoxdriver using WebDriverManager
			WebDriverManager.firefoxdriver().setup();
			/*FirefoxOptions fireFoxOptions = new FirefoxOptions();
			FirefoxProfile profile = new FirefoxProfile();
			//Set a preference for this particular profile.
			profile.setPreference("dom.ipc.plugins.enabled.libflashplayer.so", false);
			fireFoxOptions.setProfile(profile);
			fireFoxOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);*/
			driver = new FirefoxDriver();
			Log.info("Firefox driver instantiated...");
		}else if(browser.equalsIgnoreCase("iedriver")){
			//setup the iedriver using WebDriverManager
			WebDriverManager.iedriver().setup();
			// Set desired capabilities to Ignore IEDriver zoom level settings and disable native events.
			DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			caps.setCapability("EnableNativeEvents", false);
			caps.setCapability("ignoreZoomSetting", true);
			driver = new InternetExplorerDriver(caps);
			Log.info("IE driver instantiated...");
		}else{
			/*driver = new RemoteWebDriver(new URL(properties.getPropertyValue("hdportal.url")),
					getBrowserCapabilities(browser, SuiteName));
			driver.manage().window().maximize();*/	
		}

		try{	
			driver.get(properties.getPropertyValue("hdportal.url"));	
			Log.info("Web application launched and URL is: "+properties.getPropertyValue("hdportal.url"));
		}catch(Exception e){
			Log.error("Network server is slow, check internet connection");
			throw new Error("Network server is slow, check internet connection");
		}
		driver.manage().window().maximize();			
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Log.info("Implicit wait applied on the driver for 30 seconds");

		/*WebElement error = driver.findElement(By.className("error-code"));

		if(error.getText().equals("DNS_PROBE_FINISHED_NO_INTERNET") || error.getText().equals("ERR_NAME_NOT_RESOLVED")
				|| error.getText().equals("ERR_INTERNET_DISCONNECTED")) {
			Log.error("No Internet Connection");
			driver.manage().deleteAllCookies();
			driver.close();
			driver.quit();
		} else {
			Log.info("Internet Connected");
		}*/
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

	//Method to related capabilities
	protected static DesiredCapabilities getBrowserCapabilities(String browserType, String SuiteName) {
		switch (browserType) {
		case "firefox":
			Log.info("Opening firefox browser...");
			return DesiredCapabilities.firefox();
		case "chrome":
			Log.info("Opening chrome browser...");
			//System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
			DesiredCapabilities chromeCaps = DesiredCapabilities.chrome();
			return chromeCaps;
		case "IE":
			Log.info("Opening IE browser...");
			return DesiredCapabilities.internetExplorer();
		default:
			Log.info("Browser : " + browserType + " is invalid, launching Firefox as browser of choice..");
			return DesiredCapabilities.firefox();
		}
	}


	//Method to initialize all the page classes
	@Parameters({ "SuiteName" })
	@BeforeClass
	public void initialize(@Optional("ChromeSuite") String SuiteName) {
		hdPortalLoginPage = new HDPortalLoginPage(driver);
		hdPortalHomePage = new HDPortalHomePage(driver);
		hdPortalManageUserGroupsPage = new HDPortalManageUserGroupsPage(driver);
		hdPortalManageNewManualAlertsPage = new HDPortalManageNewManualAlertsPage(driver);
		hdPortalManageKnowledgeBasePage = new HDPortalManageKnowledgeBasePage(driver);
		hdPortalManagePendingVisitsPage = new HDPortalManagePendingVisitsPage(driver);
		hdPortalManageInProgressAlertsPage = new HDPortalManageInProgressAlertsPage(driver);
		hdPortalManageAlertHistoryPage = new HDPortalManageAlertHistoryPage(driver);
		hdPortalManageAddNewVisitPage = new HDPortalManageAddNewVisitPage(driver);
		hdPortalManageCompleteVisitsPage = new HDPortalManageCompleteVisitsPage(driver);
		hdPortalManageCancelVisitsPage = new HDPortalManageCancelVisitsPage(driver);
		hdPortalManageVisitHistoryPage = new HDPortalManageVisitHistoryPage(driver);	
		hdPortalManageCreditNotePage = new HDPortalManageCreditNotePage(driver);
	}


	//Method to log out HD portal
	public void logoutHDPortal() {
		hdPortalLoginPage.logoutHDPortal();	
	}

	@AfterSuite(alwaysRun = true)
	public void clean() throws InterruptedException {
		Log.debug("after suite has been called");
		try {
			logoutHDPortal();
			//Log.info("Logged out HD portal...");
		} catch (Exception e) {
			Reporter.log(e.getMessage());
			Log.error("Cleanup failed due to the error" +e.getMessage());
		} finally {
			driver.manage().deleteAllCookies();
			driver.close();
			driver.quit();
			Log.info("Closing browser...");		
		}
	}
}