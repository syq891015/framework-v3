package com.myland.framework.authority.pattern;

import java.text.DateFormat;
import java.util.Date;

public class DatePatternConverter extends PatternParser {
	private DateFormat df;
	private Date date = new Date();

	DatePatternConverter(DateFormat df) {
		this.df = df;
	}

	@Override
	public String format() {
		return df.format(date);
	}
}