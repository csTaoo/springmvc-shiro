package com.shitao.table.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shitao.foods.utils.NumberCreater;
import com.shitao.qrcode.factory.QRCodeFactory;
import com.shitao.table.entity.Table;
import com.shitao.table.service.TableService;

@Controller
@RequestMapping(value="/table")
public class TableController {
	
	@Autowired
	private TableService tableService;
	
	
	@RequestMapping(value="list")
	public String list(HttpServletRequest req,HttpServletResponse res,Model model)
	{
		List<Table> tables = tableService.list();
		
		model.addAttribute("tables", tables);
		return "modules/table/listTable";
	}
	
	
	@RequestMapping(value="save")
	public void save(HttpServletRequest req,HttpServletResponse res,Model model,Table table)
	{
		
		String isUpdate = (String)req.getParameter("isUpdate");
		res.setContentType("text/html");
		res.setCharacterEncoding("utf-8");
		PrintWriter writer = null;
		try 
		{
			writer = res.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if("true".equals(isUpdate))
		{
			tableService.update(table);
		}
		else
		{
			if(tableService.getTableByNumber(table.getTable_num())==null)
			{
				String prefixId = tableService.getlastestNumber();
				String tableId = NumberCreater.generateNumber("table", prefixId);
				table.setId(tableId);
				table.setCreate_time(NumberCreater.getCurrentTime());
				
				String urlPrefix = "http://localhost:8080/table/order?table_num=";
				//生成网址
				QRCodeFactory factory = new QRCodeFactory();
				
				String tableNumStr = String.valueOf(table.getTable_num());
				String qrCodePath = factory.createQRcode(urlPrefix+tableNumStr, tableNumStr);
				table.setTable_vrCode(qrCodePath);
				tableService.save(table);
				writer.write("保存成功");
			}
			else
			{
				
				writer.write("已有桌号为"+table.getTable_num()+"的桌子");
			}
		}
	}
	
	@RequestMapping(value="delete")
	public void delete(HttpServletRequest req,HttpServletResponse res)
	{
		res.setContentType("text/html");
		res.setCharacterEncoding("utf-8");
		PrintWriter writer = null;
		try
		{
			writer = res.getWriter();
			String tableId = req.getParameter("tableId");
			Table table = tableService.get(tableId);
			if(table.getTable_vrCode()!=null)
			{
				File file = new File(table.getTable_vrCode());
				file.delete();
			}
			tableService.delete(table);
			writer.print("删除成功");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			writer.print("删除失败");
		}
		
		
	}
}
