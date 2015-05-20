package com.zsoft.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	public static Date prepareEndDate(Date endDate) {
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(endDate);

		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);

		return cal.getTime();
	}

	public static Date prepareStartDate(Date startDate) {
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(startDate);

		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}
	
	
}
