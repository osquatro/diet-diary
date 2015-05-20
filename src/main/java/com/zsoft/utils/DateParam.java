package com.zsoft.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;

import com.zsoft.constants.Constants;
import com.zsoft.exception.ParseDateException;

public class DateParam {
	
	private final Date date;
	final DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT_NO_TIME);
	
	public DateParam(String dateStr) {
		if (StringUtils.isEmpty(dateStr)) {
			this.date = null;
			return;
		}
		
		try {
			this.date = dateFormat.parse(dateStr);
		} catch (ParseException e) {
			throw new ParseDateException(e);
		}
	}

	public Date getDate() {
		return date;
	}

}
