package io.github.r_h.wicket_notes_demo.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.wicket.util.convert.converters.DateConverter;

@SuppressWarnings("serial")
public class DefaultDateTimeConverter extends DateConverter {

	private static final String JAVA_PATTERN = "yyyy-MM-dd HH:mm:ss";

	public static final String PICKER_PATTERN = "yy-mm-dd";

	@Override
	public Date convertToObject(String value, Locale locale) {
		String newValue = value;
		if (value != null && value.length() <= 10) {
			// allow dates without time
			newValue += " 00:00:00";
		}
		return super.convertToObject(newValue, locale);
	}

	@Override
	public DateFormat getDateFormat(Locale locale) {
		return new SimpleDateFormat(JAVA_PATTERN);
	}

}
