package com.shitao.common.utils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class ShiroFilterUtils {
	
	public static boolean isAjax(ServletRequest request){
		return "XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"));
	}
	
}
