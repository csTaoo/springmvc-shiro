package com.shitao.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropsUtil {

	private PropsUtil() {

	}

	public static String getProperty(String propertyName) {
		Properties prop = new Properties();// 属性集合对象
		InputStream fis = null;
		try {
			fis = PropsUtil.class.getResourceAsStream("/shitao.properties");
			prop.load(fis);// 将属性文件流装载到Properties对象中
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			if(fis!=null)
			{
				try 
				{
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		String result = prop.getProperty(propertyName);
		return ("".equals(result) || result == null) ? "" : result;
	}

}
