package com.buddi.colombiaapp.tests;

import java.io.IOException;
import java.net.ServerSocket;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.buddi.colombia.utilities.ReadProperties;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumServerJava {

	private AppiumDriverLocalService service;
	private AppiumServiceBuilder builder;
	private DesiredCapabilities capabilities;
	/*public String appiumServerIPAddress = "127.0.0.1";
	public int appiumServerPort = 4723;*/
	static ReadProperties readProperties = new ReadProperties();
	public static int appiumServerPort = Integer.parseInt(readProperties.getPropertyValue("appium.server.port"));

	//Method to start Appium server 
	public void startAppiumServer() {
		//Set Capabilities
		capabilities = new DesiredCapabilities();
		capabilities.setCapability("noReset", "false");

		//Build the Appium service
		builder = new AppiumServiceBuilder();
		builder.withIPAddress(readProperties.getPropertyValue("appium.server.ipaddress"));		
		builder.usingPort(Integer.parseInt(readProperties.getPropertyValue("appium.server.port")));
		builder.withCapabilities(capabilities);
		builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
		builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");

		//Start the server with the builder
		service = AppiumDriverLocalService.buildService(builder);
		service.start();
	}

	//Stop the Appium server service 
	public void stopAppiumServer() {
		service.stop();
	}

	//Method to check if Appium server status
	public boolean checkIfAppiumServerIsRunnning(int port) {		
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

	public static void main(String[] args) {
		AppiumServerJava appiumServer = new AppiumServerJava();
		//int port = 4723;
		if(!appiumServer.checkIfAppiumServerIsRunnning(appiumServerPort)) {
			appiumServer.startAppiumServer();
			appiumServer.stopAppiumServer();
		} else {
			System.out.println("Appium Server already running on port - " + appiumServerPort);
		}
	}
}

	/*public class AppiumServerJava {
	
		public void startServer() {
			Runtime runtime = Runtime.getRuntime();
			try {
				runtime.exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723 --session-override -dc \"{\"\"noReset\"\": \"\"false\"\"}\"\"");
				Thread.sleep(10000);
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	
		public void stopServer() {
			Runtime runtime = Runtime.getRuntime();
			try {
				runtime.exec("taskkill /F /IM node.exe");
				runtime.exec("taskkill /F /IM cmd.exe");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
		public static void main(String[] args) {
			AppiumServerJava appiumServer = new AppiumServerJava();
			appiumServer.startServer();
	
			appiumServer.stopServer();
		}
	}*/