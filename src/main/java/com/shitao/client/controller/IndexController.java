package com.shitao.client.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shitao.client.business.ClientBusiness;
import com.shitao.common.utils.UserUtils;
import com.shitao.foods.entity.FoodSort;
import com.shitao.mongodb.operator.OrdersMongoDBOperator;
import com.shitao.mongodb.operator.ShopCarOperator;
import com.shitao.sys.entity.Carousel;
import com.shitao.sys.entity.User;
import com.shitao.sys.message.websocket.PayMessageHandler;

@Controller
@RequestMapping(value="/a/index")
public class IndexController {
	
	@Autowired
	private ClientBusiness clientBusiness;
	
	@RequestMapping("websocket")
	public String webSocketTest()
	{
		return "modules/client/websocket";
	}
	
	@RequestMapping("indexShow")
	public String indexShow(HttpServletRequest req ,Model model)
	{
		List<Carousel> list = clientBusiness.listCarousel();
		model.addAttribute("list", list);
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
		if(user.getMoney()>money)
		{
			ShopCarOperator operator = new ShopCarOperator();
			//删除购物车
			operator.delete();
			//减少菜品数量
			JSONArray items = json.getJSONArray("foods");
			JSONObject temp;
			for (Object object : items) {
				 temp = (JSONObject)object;
				 String id = (String)temp.get("id");
				 Integer count = (Integer)temp.get("count");
				 clientBusiness.decreaseFoodCount(id, count);
			}
			//扣减用户余额
			clientBusiness.pay(user.getName(), money);
			//插入已付账订单
			if(json.containsKey("_id"))
			{
				json.remove("_id");
			}
			OrdersMongoDBOperator orderOp = new OrdersMongoDBOperator();
			orderOp.insert(shopCar);
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
	public String mine()
	{
		return "modules/client/mine";
	}
}
