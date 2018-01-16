package com.shitao.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
	
	
	public static Date formatDate(String s)
	{
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
		Date date = null;
		try 
		{
			date =  fmt.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}
