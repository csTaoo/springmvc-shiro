package com.shitao.sys.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shitao.sys.entity.Carousel;
import com.shitao.sys.service.BusinessService;

@Controller
@RequestMapping(value="/sys/carousel")
public class CarouselController {
	
	@Autowired
	private BusinessService businessService;
	
	@RequestMapping(value="index")
	public String index(HttpServletRequest req,Model model)
	{
		List<Carousel> carousels = businessService.list();
		model.addAttribute("carousels", carousels);
		return "modules/sys/carouselIndex";
	}
	
	@ResponseBody
	@RequestMapping(value="delete")
	public void delete(@RequestParam(required = true)String carouselId,HttpServletRequest req,HttpServletResponse response)
	{
		String imgPath  = businessService.getCarousel(carouselId).getImgPath();
		String filePath = req.getSession().getServletContext().getRealPath("/");
		filePath += "/static/upload/"+imgPath;
		File file = new File(filePath);
		file.delete();
		try
		{
			Carousel carousel = new Carousel();
			carousel.setId(carouselId);
			businessService.deleteCarousel(carousel);
			writeJson(response, "success");
		}catch(Exception e)
		{
			writeJson(response, "fail");
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value="update")
	public void update(@RequestParam(required = true)String carouselId,HttpServletResponse response)
	{
		try
		{
			Carousel carousel = new Carousel();
			carousel.setId(carouselId);
			businessService.updateCarousel(carousel);
			writeJson(response, "success");
		}catch(Exception e)
		{
			writeJson(response, "fail");
		}
	}
	
	
	private void writeJson(HttpServletResponse req,String result)
	{
		try 
		{
			PrintWriter out  = req.getWriter();
			out.write(result);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	

}
