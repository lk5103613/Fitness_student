package com.like.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

	public static int getDif(String dateStr) {
		Date begin = getDate(dateStr); 
		Date end = new Date();  
		long between=(end.getTime()-begin.getTime())/1000;//除以1000转换成秒  
		int dayDif=(int)between/(24*3600);  
		return dayDif;
	}

	public static Date getDate(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.getDefault());
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;

	}
	

}
