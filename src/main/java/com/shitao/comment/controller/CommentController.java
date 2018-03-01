package com.shitao.comment.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shitao.mongodb.operator.OrdersMongoDBOperator;

@Controller
@RequestMapping(value="/comment")
public class CommentController {
	
	@RequestMapping(value="index")
	public String index()
	{
		return "modules/comment/listComment";
	}
	
	@RequestMapping(value="listComment")
	public void listComment(HttpServletResponse res)
	{
		OrdersMongoDBOperator operator = new OrdersMongoDBOperator();
		String result = operator.listOrders();
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
	
	@RequestMapping(value="delComment")
	public void delComment(HttpServletRequest req,HttpServletResponse res)
	{
		String orderId = req.getParameter("orderId");

		OrdersMongoDBOperator operator = new OrdersMongoDBOperator();
		try {
			operator.delComment(orderId);
		}finally
		{
			operator.close();
		}
		return;
		
	}
	
	@RequestMapping(value="listCommentByFoodId")
	public void listCommentByFoodId(HttpServletRequest req,HttpServletResponse res,String foodId)
	{
		OrdersMongoDBOperator operator = new OrdersMongoDBOperator();
		String result = operator.getCommentDataByFoodId(foodId);
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
