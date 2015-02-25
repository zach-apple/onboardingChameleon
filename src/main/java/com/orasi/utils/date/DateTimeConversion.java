package com.orasi.utils.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTimeConversion {

	public DateTimeConversion() {
		// TODO Auto-generated constructor stub
	}
	
	public static String ConvertToDate(String daysOut){
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, Integer.parseInt(daysOut));
		String convertedDate=dateFormat.format(cal.getTime());    
		
		return convertedDate;
	}
	
	public String ConvertToDateMMDDYY(String daysOut){
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, Integer.parseInt(daysOut));
		String convertedDate=dateFormat.format(cal.getTime());    
		
		return convertedDate;
	}
	
	public static String format(String date, String format){
		return new SimpleDateFormat(format).format(date);
	}


	
	public String ConvertToDateYYYYMMDD(String daysOut){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, Integer.parseInt(daysOut));
		String convertedDate=dateFormat.format(cal.getTime());    
		return convertedDate;
	}
	
	/**
	 * 
	 * @param format ex: 'MM/dd/yyyy', 'yyyy-MM-dd'
	 * @return Formatted date as String
	 */
	public String formatDate(String format){
	    return formatDate(format, 0);
	}
	
	/**
	 * 
	 * @param format ex: 'MM/dd/yyyy', 'yyyy-MM-dd'
	 * @param daysOut as any Integer
	 * @return Formatted date as String with adjusted amount of days
	 */
	public String formatDate(String format, String daysOut){
	    return formatDate(format, Integer.parseInt(daysOut));
	}
	
	/**
	 * 
	 * @param format ex: 'MM/dd/yyyy', 'yyyy-MM-dd'
	 * @param daysOut as any Integer
	 * @return Formatted date as String with adjusted amount of days
	 */
	public String formatDate(String format, int daysOut){
	    DateFormat dateFormat = new SimpleDateFormat(format);
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, daysOut);
	    return dateFormat.format(cal.getTime());    
	}
	
}
