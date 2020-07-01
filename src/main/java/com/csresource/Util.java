package com.csresource;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyy HH:mm:ss.SSS");

	public static String convertCurrentDateIntoString() {
		
		return sdf.format(new Date());
	}
}
