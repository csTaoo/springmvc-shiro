package com.shitao.foods.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.shitao.common.utils.StringUtils;


/**
 * 生成编号类
 * @author ：shitao.Chen
 * @date：2017年11月7日下午7:05:26
 * @className：NumberCreater
 * TODO
 * @param <E>
 */
public class NumberCreater {
	//分割符
	private static final char NUMBER_SPLIT = '-';
	
	/*
	 * 生成编号
	 */
	public static String generateNumber(String prefix,String lastNum)
	{
		 if(!StringUtils.isBlank(lastNum))
		 {
			 return prefix+"-0001";
		 }
		 else
		 {
			 int num = Integer.valueOf(lastNum.split("-")[1]);
			 //获得位数
			 StringBuilder result = new StringBuilder();
			 result.append(++num);
			 int a = 4 - String.valueOf(num).length();
			 for(int i= 0;i<a;i++)
			 {
				 result.insert(0, '0');
			 }
			 result.insert(0, prefix+NUMBER_SPLIT);
			 return result.toString();
		 }
	}
	
	public static String replyFoodId(long id)
	{
		String prefix = "foods-";
		String sid = String.valueOf(id);
		int zero = 4-sid.length();
		String temp = "";
		for(int i=0;i<zero;i++)
		{
			temp+="0";
		}
		return prefix+temp+sid;
	}
	
	public static String getCurrentTime()
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date());
		
	}
	
}
