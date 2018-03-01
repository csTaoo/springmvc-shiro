package com.shitao.foods.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.shitao.foods.entity.FoodSort;
import com.shitao.foods.entity.Foods;
import com.shitao.foods.service.FoodSortService;
import com.shitao.foods.service.FoodsService;
import com.shitao.foods.utils.NumberCreater;

@Controller
@RequestMapping(value="/foods")
public class FoodsController {
	
	@Autowired
	private FoodsService foodsService;
	@Autowired
	private FoodSortService foodSortService;
	
	@RequestMapping(value="list")
	public String index(HttpServletRequest req,Model model)
	{
		String foodname = req.getParameter("foodname");
		List<Foods> foods = foodsService.list(foodname);
		model.addAttribute("foods", foods);
		return "modules/foods/listFoods";
	}
	
	@RequestMapping(value="edit")
	public String editFoods(HttpServletRequest req,HttpServletResponse res,Model model)
	{
		
		String id = req.getParameter("foodsId");
		List<FoodSort> foodSorts = foodSortService.list();
		model.addAttribute("foodsort", foodSorts);
		if(id!=null)
		{
			Foods foods = foodsService.get(id);
			model.addAttribute("foods", foods);
			model.addAttribute("isUpdate", "true");
		}
		return "modules/foods/editFoods";
	}
	
	@RequestMapping(value="save")
	public String save(HttpServletRequest req,HttpServletResponse res,Foods foods,RedirectAttributes attr)
	{
		String isUpdate = req.getParameter("isUpdate");
		try
		{
			if(!"true".equals(isUpdate))
			{
				String number = NumberCreater.generateNumber("foods",foodsService.getLastestNumber());
				foods.setId(number);
				foodsService.save(foods);
			}
			else
			{
				foodsService.update(foods);
			}
			attr.addFlashAttribute("mes", "保存成功");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			attr.addFlashAttribute("mes", "保存失败");
		}
		
		return "redirect:/foods/list";
	}
	
	@RequestMapping(value="listFoodsBySort")
	public void listFoodsBySortId(HttpServletRequest req,HttpServletResponse res)
	{
		
		String sortid = req.getParameter("sortId");
		List<Foods> list = foodsService.listFoodsBySort(sortid);
		Map<String,List<Foods>> map = new HashMap<String,List<Foods>>();
		map.put("data", list);
		String result = JSON.toJSONString(map);
		PrintWriter writer;
		try {
			res.setContentType("text/plain");
			writer = res.getWriter();
			writer.write(result);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="upload")
	@ResponseBody
	public void upload(@RequestParam("file") MultipartFile file,HttpServletRequest req,HttpServletResponse rep)
	{
		StringBuilder path  = new StringBuilder();
		path.append(req.getSession().getServletContext().getRealPath("/"));
        path.append("/static/upload/foods/"+file.getOriginalFilename());
        File newFile=new File(path.toString());
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        try 
        {
			file.transferTo(newFile);
		} catch (IllegalStateException e) 
		{
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        try 
        {
			PrintWriter writer = rep.getWriter();
			writer.write(returnJson(file.getOriginalFilename()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	private String returnJson(String path)
	{
		StringBuilder result = new StringBuilder();
		result.append("{\"code\":200");
		result.append(",");
		result.append("\"msg\":\"上传成功\"");
		result.append(",");
		result.append("\"data\":{\"src\":\"");
		result.append(path);
		result.append("\"}}");
		
		return result.toString();
		
	}
	
	@RequestMapping("delete")
	public void delete(HttpServletRequest req,HttpServletResponse res)
	{
		String id = req.getParameter("foodsId");
		Foods foods = new Foods();
		foods.setId(id);
		foodsService.delete(foods);
	}
}
