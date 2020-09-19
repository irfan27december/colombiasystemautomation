package com.buddi.colombia.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateToString{
	public static Date todaysDate; 
	public static DateFormat dateFormat;

	public static String returnDate(String strDateFormat){
		todaysDate = new Date();
		if(strDateFormat.equalsIgnoreCase("dd/MM/yyyy")){
			try{
				dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				System.out.println("Date format is "+strDateFormat+ " and todays date is: "+dateFormat.format(todaysDate));
				return dateFormat.format(todaysDate);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}else if(strDateFormat.equalsIgnoreCase("dd/MM/yyyy HH:mm:ss")){
			try{
				dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				System.out.println("Date format is "+strDateFormat+ " and todays date is: "+dateFormat.format(todaysDate));
				return dateFormat.format(todaysDate);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}else{
			return dateFormat.format(todaysDate);
		}
		return dateFormat.format(todaysDate);

	}

	public static void main(String[] args) {
		DateToString.returnDate("dd/MM/yyyy");

	}

}
