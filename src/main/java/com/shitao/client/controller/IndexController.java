package com.shitao.client.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shitao.client.business.ClientBusiness;
import com.shitao.foods.entity.FoodSort;
import com.shitao.sys.entity.Carousel;

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
	
	@RequestMapping("mine")
	public String mine()
	{
		return "modules/client/mine";
	}
}
