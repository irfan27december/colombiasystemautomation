package com.buddi.colombia.testdata;

import java.util.Random;

public class TestData {

	// App related variables
	/*public static final String APP_EMAIL = "irfan.ahmed@buddi.co.uk";
	public static final String APP_PASSWORD = "MIA@27dec1";*/
	// create instance of Random class 
	static Random random = new Random(); 
	// Generate random integers in range 0 to 999 
	static int randomInt = random.nextInt(1000);

	public static final String APP_EMAIL = "idrees14september@gmail.com";
	public static final String APP_PASSWORD = "*****";


	// HD Portal related login variables
	public static final String HDPORTAL_EMAIL = "irfan.ahmed@buddi.co.uk";
	public static final String HDPORTAL_PASSWORD = "*****";

	//HD Portal Management related variables
	public static final String USERGROUP_NAME = "UG_AUTOMATION";
	public static final String DUPLICATE_USERGROUP_NAME = "DUPLICATE_UG_AUTOMATION";
	
	// Knowledge base variables
	public static final String KNOWLEDGEBASE_ITEMNAME = "KB_AUTOMATION_"+randomInt;
	public static final String KNOWLEDGEBASE_SUBJECT = "This is the subject for knowledge base...";
	public static final String KNOWLEDGEBASE_DESCRIPTION = "This is the description for knowledge base...";
	
	
	//Alert related variables
	public static final String WEARER_NUI = "9010203040";
	public static final String ALERT_WEARER_NAME = "Automation"; //Pacífica Rosalía Huerta, Automation-
	public static final String ALERT_SEVERITY = "Standard";
	public static final String ALERT_START_DATE_FORMAT = "dd/MM/yyyy";
	public static final String ALERT_START_HOUR = "20";
	public static final String ALERT_START_MINUTES = "50";
	public static final String ALERT_NOTES = "Manual alert is created...";
	
}
