package com.buddi.colombia.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import com.google.protobuf.TextFormat.ParseException;

import freemarker.template.utility.DateUtil;



public class DateToString{
	public static Date date = new Date();; 
	public static DateFormat dateFormat;

	//Method to return current date in the specified date format
	public static String returnDate(String strDateFormat){

		if(strDateFormat.equalsIgnoreCase("dd/MM/yyyy")){
			try{
				dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				//System.out.println("Date format is "+strDateFormat+ " and todays date is: "+dateFormat.format(date));
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}else if(strDateFormat.equalsIgnoreCase("dd/MM/yyyy HH:mm:ss")){
			try{
				dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				//System.out.println("Date format is "+strDateFormat+ " and todays date is: "+dateFormat.format(date));
				return dateFormat.format(date);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}else{
			return dateFormat.format(date);
		}
		return dateFormat.format(date);
	}



	//Method to return current date incremented by no. of days specified
	private static String getNextDate(int noOfDays) throws java.text.ParseException {
		String givenDate = returnDate("dd/MM/yyyy");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		String nextDaysDate = null;
		try {
			cal.setTime(dateFormat.parse(givenDate));
			cal.add(Calendar.DATE, noOfDays);
			nextDaysDate = dateFormat.format(cal.getTime());
		} catch (Exception ex) {
			//Logger.getLogger(GR_TravelRepublic.class.getName()).log(Level.SEVERE, null, ex);
		}finally{
			dateFormat = null;
			cal = null;
		}
		System.out.println("Incremented date is: "+nextDaysDate);
		return nextDaysDate;
	}

	public static void main(String[] args) throws java.text.ParseException {
		DateToString.returnDate("dd/MM/yyyy");
		DateToString.getNextDate(2);
	}

}
