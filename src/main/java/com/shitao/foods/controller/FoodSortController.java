package com.shitao.foods.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shitao.foods.entity.FoodSort;
import com.shitao.foods.service.FoodSortService;
import com.shitao.foods.utils.NumberCreater;

@Controller
@RequestMapping(value="/foodsort")
public class FoodSortController {
	
	@Autowired
	private FoodSortService foodSortService;
	
	@RequestMapping(value="list")
	public String index(HttpServletRequest req,Model model)
	{
		String sortname = req.getParameter("sortname");
		List<FoodSort> foodSorts = foodSortService.list(sortname);
		model.addAttribute("foodSorts", foodSorts);
		return "modules/foods/listFoodSort";
	}
	
	@RequestMapping(value="save")
	@ResponseBody
	public void save(HttpServletRequest req,HttpServletResponse res,FoodSort foodSort) throws IOException
	{
		String isUpdate = req.getParameter("isUpdate");
		res.setContentType("text/html");
		res.setCharacterEncoding("utf-8");
		PrintWriter write = res.getWriter();
		try
		{
			if(!"true".equals(isUpdate))
			{
				String number = NumberCreater.generateNumber("sort",foodSortService.getLastestNumber());
				foodSort.setId(number);
				foodSortService.save(foodSort);
			}
			else
			{
				foodSortService.update(foodSort);
			}
			write.write("保存成功");
		}
		catch(Exception e)
		{
			write.write("保存失败");;
		}
		write.close();
		
	}
	
	@RequestMapping("delete")
	public void delete(HttpServletRequest req,HttpServletResponse res)
	{
		String id = req.getParameter("foodSortId");
		FoodSort foodsort = new FoodSort();
		foodsort.setId(id);
		foodSortService.delete(foodsort);
	}
}
