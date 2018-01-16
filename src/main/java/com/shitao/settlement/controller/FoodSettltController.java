package com.shitao.settlement.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shitao.mongodb.operator.OrdersMongoDBOperator;

@Controller
@RequestMapping(value="/sys")
public class FoodSettltController {
	
	
	@RequestMapping(value="foodsettle")
	public String daySettle()
	{
		return "modules/settlement/foodsettlement";
	}
	
	@RequestMapping(value="moneysettle")
	public String moneySettle()
	{
		return "modules/settlement/moneysettlement";
	}
	
	@ResponseBody
	@RequestMapping(value="getSpecifyDaySettle")
	public void getSpecifyDaySettle(HttpServletRequest req,HttpServletResponse res)
	{
		String date = req.getParameter("date");
		OrdersMongoDBOperator operator = new OrdersMongoDBOperator();
		String result = operator.listSpecifyDayData(date);
		res.setContentType("text/plain");
		PrintWriter writer;
		try 
		{
			writer = res.getWriter();
			writer.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="getMoneySettleData")
	public void getMoneySettleData(HttpServletRequest req,HttpServletResponse res)
	{
		OrdersMongoDBOperator operator = new OrdersMongoDBOperator();
		String result = operator.getMoneySettleData();
		res.setContentType("text/plain");
		PrintWriter writer;
		try 
		{
			writer = res.getWriter();
			writer.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
