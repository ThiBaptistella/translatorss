package au.com.translatorss;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Solution8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date datePast= getPastDate(7);
		System.out.println(datePast);
	}

	 private static Date getPastDate(int days) {
	    	Date today = new Date();
	    	Calendar cal = new GregorianCalendar();
	    	cal.setTime(today);
	    	cal.add(Calendar.DAY_OF_MONTH, -days);
	    	Date today30 = cal.getTime();
	    	return today30;
	    } 
}
