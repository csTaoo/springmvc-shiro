package com.shitao.common.paginationer;

import javax.servlet.http.HttpServletRequest;

public class PageHelper {
	
	private static ThreadLocal<Page> param = new ThreadLocal<Page>();
	
	
	public static void startPage(HttpServletRequest req)
	{
		
		Page page = new Page(req);
		param.set(page);
	}
	
	public static void startPage(int current)
	{
		Page page = new Page(current);
		page.setExePage(true);
		param.set(page);
	}
	
	public static void endPage()
	{
		param.get().setExePage(false);
		param.remove();
	}
	
	public static boolean isExePage()
	{
		return param.get().isExePage();
	}

	public static Page getPage()
	{
		return param.get();
	}
}

