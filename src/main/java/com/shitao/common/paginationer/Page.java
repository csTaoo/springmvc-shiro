package com.shitao.common.paginationer;

import javax.servlet.http.HttpServletRequest;

public class Page {
	
	private int currentPage; //当前页
	private int pageNum ;  //每页多少条
	private boolean  exePage; //是否执行分页
	
	
	
	public Page(HttpServletRequest req)
	{
		this.currentPage = Integer.parseInt(req.getParameter("currentPage"));
		this.pageNum = 10;
		prepareData();
	}
	
	public Page(int current)
	{
		this.currentPage = current; 
		this.pageNum = 3;
		prepareData();
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public int getPageNum() {
		return pageNum;
	}
	
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	
	public void setCount(int count)
	{
		
	}
	
	
	public int getStartRow()
	{
		return currentPage*pageNum;
	}
	
	private void prepareData()
	{
	
	}

	public boolean isExePage() {
		return exePage;
	}

	public void setExePage(boolean exePage) {
		this.exePage = exePage;
	}

}
