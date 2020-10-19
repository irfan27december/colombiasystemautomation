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
	public static final String APP_PASSWORD = "fo-ahm7529";


	// HD Portal related login variables
	public static final String HDPORTAL_EMAIL = "irfan.ahmed@buddi.co.uk";
	public static final String HDPORTAL_PASSWORD = "MIA@27dec1";
	public static final String HDPORTAL_EMAIL_02 = "sumaiah.mohammed@buddi.co.uk";
	public static final String HDPORTAL_PASSWORD_02 = "1234sumayya";

	//HD Portal Management related variables
	public static final String USERGROUP_NAME = "UG_AUTOMATION";
	public static final String DUPLICATE_USERGROUP_NAME = "DUPLICATE_UG_AUTOMATION";

	// Knowledge base variables
	public static final String KNOWLEDGEBASE_ITEMNAME = "KB_AUTOMATION_"+randomInt;
	public static final String KNOWLEDGEBASE_SUBJECT = "This is the subject for knowledge base...";
	public static final String KNOWLEDGEBASE_DESCRIPTION = "This is the description for knowledge base...";


	//Alert related variables
	public static final String WEARER_NUI = "9010203040";
	//"9010203040 ~ Pacífica Rosalía Huerta, Automation-";
	public static final String ALERT_WEARER_NAME = "Pacífica Rosalía Huerta, Automation-";
	public static final String ALERT_SEVERITY = "low";
	public static final String ALERT_START_DATE_FORMAT = "dd/MM/yyyy";
	public static final String ALERT_START_HOUR = "22";
	public static final String ALERT_START_MINUTES = "55";
	public static final String ALERT_NOTES = "Manual alert is created...";
	public static final String CLOSE_ALERT_NOTES = "Closing alert...";

	//Add New Visit variables
	public static final String ADD_VISIT_WEARER_NUI = "1075062";
	public static final String SELECT_JOBTYPE = "End of Monitoring";
	public static final String SELECT_WEARERGROUP = "CPAMS LA PAZ";
	//"1075062 ~ VIDAL  CEPEDA , JOHN JAIRO "(Add Visit automation Wearer)
	public static final String ADD_NEW_VISIT_WEARER=" ~ VIDAL  CEPEDA , JOHN JAIRO ";
	public static final String SELECT_FIELD_OFFICER = "Mohammed, Sumaiah (testbuddi1212@gmail.com)";
	public static final String VISIT_START_DATE_FORMAT = "dd/MM/yyyy";
	public static final String VISIT_START_HOURS = "20";
	public static final String VISIT_START_MINUTES = "30";
	public static final String VISIT_END_DATE_FORMAT = "dd/MM/yyyy";
	public static final String VISIT_END_HOURS= "22";
	public static final String VISIT_EMD_MINUTES= "40";
	public static final String ADD_VISIT_NOTES= "Creating new visit...!";

	//Cancelled Visit variables
	public static final String SELECT_CANCELLED_OUTCOME = "Cancelled";
	public static final String SELECT_CANCELLED_REASON = "No visit required";
	public static final String ENTER_CANCELLED_NOTES = "Visit is cancelled..!";

	//Completed Visit variables
	public static final String SELECT_COMPLETED_OUTCOME = " Completed";
	public static final String SELECT_COMPLETED_REASON = " Made without HD Management";
	public static final String COMPLETED_VISIT_START_DATE_FORMAT = "dd/MM/yyyy";
	public static final String COMPLETED_VISIT_START_HOURS = "20";
	public static final String COMPLETED_VISIT_START_MINUTES = "30";
	public static final String COMPLETED_VISIT_END_DATE_FORMAT = "dd/MM/yyyy";
	public static final String COMPLETED_VISIT_END_HOURS= "22";
	public static final String COMPLETED_VISIT_EMD_MINUTES= "40";
	public static final String ENTER_COMPLETED_NOTES = "Visit is completed...!";


	// Credit Note variable
	public static final String RECORDCREDITNOTE_UBIN_ITEMNUMBER = "TEST"+randomInt;
	public static final String RECORDCREDITNOTE_WEARER_NAME= "This is the subject for wearer name...";
	public static final String RECORDCREDITNOTE_NOTES= "This is the subject for notes...";
	public static final String RECORDCREDITNOTE_OBSERVATION= "This is the subject for Observation...";
	public static final String RECORDCREDITNOTE_START_DATE_FORMAT = "dd/MM/yyyy";
	public static final String RECORDCREDITNOTE_END_DATE_FORMAT = "dd/MM/yyyy";
	public static final String RECORDCREDITNOTEFINAL_NOTES = "Record is created...";



}
