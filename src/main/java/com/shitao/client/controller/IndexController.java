package com.shitao.client.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shitao.client.business.ClientBusiness;
import com.shitao.comment.entity.Comment;
import com.shitao.common.utils.UserUtils;
import com.shitao.foods.entity.FoodSort;
import com.shitao.foods.entity.Foods;
import com.shitao.foods.utils.NumberCreater;
import com.shitao.mongodb.operator.OrdersMongoDBOperator;
import com.shitao.mongodb.operator.ShopCarOperator;
import com.shitao.sys.entity.Carousel;
import com.shitao.sys.entity.User;
import com.shitao.sys.message.websocket.PayMessageHandler;
import com.shitao.usercf.map.UserCF;

@Controller
@RequestMapping(value="/a/index")
public class IndexController {
	
	@Autowired
	private ClientBusiness clientBusiness;
	
	@Autowired
	private UserCF userCF;
	
	@RequestMapping("websocket")
	public String webSocketTest()
	{
		return "modules/client/websocket";
	}
	
	@RequestMapping("indexShow")
	public String indexShow(HttpServletRequest req ,Model model)
	{	long recid = 0;
		try {
			 recid = userCF.getRecomend(UserUtils.getCurrentUserId());
		} catch (TasteException e) {
			e.printStackTrace();
		}
		if(recid != 0)
		{
			String fid  = NumberCreater.replyFoodId(recid);
			Foods food = clientBusiness.getFoodsById(fid);
			model.addAttribute("food", food);
		}
		List<Carousel> list = clientBusiness.listCarousel();
		String table = req.getParameter("table_num");
		model.addAttribute("list", list);
		model.addAttribute("table_num",table);
		return "modules/client/index";
	}
	
	@RequestMapping("foodclassify")
	public String foodClassify(HttpServletRequest req,HttpServletResponse res,Model model)
	{
		
		List<FoodSort> foodSorts = clientBusiness.listFoodSort();
		model.addAttribute("sorts", foodSorts);
		return "modules/client/foodclassify";
	}
	
	@RequestMapping("shopcar")
	public void listShopCar(HttpServletRequest req,HttpServletResponse res)
	{
		ShopCarOperator operator = new ShopCarOperator();
		String car = operator.get();
		PrintWriter writer;
		try 
		{
			writer = res.getWriter();
			writer.write(car);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("pay")
	public void pay(HttpServletRequest req,HttpServletResponse res)
	{
		String shopCar = req.getParameter("order");
		JSONObject json = JSON.parseObject(shopCar);
		double money =  json.getDoubleValue("money");
		User user = UserUtils.gerCurrentUser();
		StringBuffer payResult = new StringBuffer();
		double userMoney = clientBusiness.getUserMoney();
		if(userMoney>money)
		{
			//扣减用户余额
			clientBusiness.pay(user.getName(), money);
			//插入已付账订单
			if(json.containsKey("_id"))
			{
				json.remove("_id");
			}
			OrdersMongoDBOperator orderOp = new OrdersMongoDBOperator();
			orderOp.insert(shopCar);
			orderOp.close();
			//通知有新订单要处理
			PayMessageHandler.notifyPayMes(shopCar);
			payResult.append("下单成功");
		}
		else
		{
			payResult.append("余额不足");
		}
		PrintWriter writer;
		try {
			writer = res.getWriter();
			writer.write(payResult.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping("mine")
	public String mine(HttpServletRequest req,HttpServletResponse res,Model model)
	{
		double money = clientBusiness.getUserMoney();
		String username = UserUtils.getCurrentUserName();
		model.addAttribute("money", money);
		model.addAttribute("username", username);
		return "modules/client/mine";
	}
	
	@RequestMapping("order")
	public String order()
	{
		return "modules/client/order";
	}
	
	@RequestMapping("getUserOrders")
	public void getUserOrders(HttpServletResponse res)
	{
		OrdersMongoDBOperator operator = new OrdersMongoDBOperator();
		String result = operator.getUserOrders();
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
	
	@RequestMapping("comment")
	public void comment(HttpServletRequest req,HttpServletResponse res,Comment comment)
	{
		OrdersMongoDBOperator operator = new OrdersMongoDBOperator();
		PrintWriter writer;
		try 
		{
			operator.commentOrder(comment);
			writer = res.getWriter();
			writer.write("success");
		} catch (IOException e) {
			e.printStackTrace();
		}finally
		{
			operator.close();
		}
		return;
		
	}
}
