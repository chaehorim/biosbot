package com.chochae.afes.service.trigger;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class RunningTimeTrigger {

	public static int	OFFER_START_HOUR = 	10;
	public static int	OFFER_END_HOUR	 =  16;
	
	public static boolean isRunningTime() {
		Date date = new Date();   // given date
		Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
		calendar.setTime(date);   // assigns calendar to given date 
		int hour = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
		
		if (hour >= OFFER_START_HOUR && hour <= OFFER_END_HOUR) {
			return true;
		}
		return false;
	}
}
