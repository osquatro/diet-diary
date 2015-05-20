package com.zsoft.jackson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.zsoft.exception.ParseDateException;

import static com.zsoft.constants.Constants.DATE_FORMAT;

/**
 *
 * @author zhosan
 */
public class CustomDateSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
			String formattedDate = formatter.format(value);

			gen.writeString(formattedDate);
		} catch (IOException e) {
			throw new ParseDateException(e);
		}
	}

}
