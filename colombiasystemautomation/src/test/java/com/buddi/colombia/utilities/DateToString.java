package com.buddi.colombia.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



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
				Log.error(e.getMessage());
			}
		}else if(strDateFormat.equalsIgnoreCase("dd/MM/yyyy HH:mm:ss")){
			try{
				dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				//System.out.println("Date format is "+strDateFormat+ " and todays date is: "+dateFormat.format(date));
				return dateFormat.format(date);
			}catch(Exception e){
				Log.error(e.getMessage());
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
		Calendar calender = Calendar.getInstance();
		String nextDaysDate = null;
		try {
			calender.setTime(dateFormat.parse(givenDate));
			calender.add(Calendar.DATE, noOfDays);
			nextDaysDate = dateFormat.format(calender.getTime());
		} catch (Exception ex) {
			Log.error(ex.getMessage());
		}finally{
			dateFormat = null;
			calender = null;
		}
		//System.out.println("Incremented date is: "+nextDaysDate);
		return nextDaysDate;
	}

	public static void main(String[] args) throws java.text.ParseException {
		DateToString.returnDate("dd/MM/yyyy");
		DateToString.getNextDate(2);
	}
}
