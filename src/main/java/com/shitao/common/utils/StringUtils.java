package com.shitao.common.utils;

import java.util.UUID;

/**
 * 
 * @author ：shitao.Chen
 * @date：2017年8月17日下午5:11:11
 * @className：StringUtils
 * TODO
 */
public class StringUtils {
	
	/**
	 * 2017年8月17日
	 * @return 唯一id
	 * author：shitao.Chen
	 */
	public static String generalUUID()
	{
		return UUID.randomUUID().toString();
	}
	
}
