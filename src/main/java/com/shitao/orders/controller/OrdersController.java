package com.shitao.orders.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shitao.mongodb.operator.OrdersMongoDBOperator;


@Controller
@RequestMapping(value="/order")
public class OrdersController {
	
	@RequestMapping(value="index")
	public String index()
	{
		return "modules/order/listOrder";
	}
	
	@RequestMapping(value="listOrder")
	public void listComment(HttpServletResponse res)
	{
		OrdersMongoDBOperator operator = new OrdersMongoDBOperator();
		String result = operator.listOrdersIgComment();
		PrintWriter writer;
		try {
			writer = res.getWriter();
			writer.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}finally
		{
			operator.close();
		}
		return;
	}
	
	
}
