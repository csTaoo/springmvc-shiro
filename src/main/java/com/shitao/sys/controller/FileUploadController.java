package com.shitao.sys.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.shitao.sys.entity.Carousel;
import com.shitao.sys.service.BusinessService;


@Controller
@RequestMapping(value="sys/file")
public class FileUploadController {
	
	@Autowired
	private BusinessService businessService;
	
	
	@RequestMapping(value="upload")
	@ResponseBody
	public void upload(@RequestParam("file") MultipartFile file,HttpServletRequest req,HttpServletResponse rep)
	{
		StringBuilder path  = new StringBuilder();
		path.append(req.getSession().getServletContext().getRealPath("/"));
        path.append("/static/upload/carousel/"+file.getOriginalFilename());
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
        
        //插入数据库
        Carousel coursel = new Carousel();
        coursel.setImgPath(file.getOriginalFilename());
        coursel.setCreate_time(new Date(System.currentTimeMillis()));
        coursel.setIsVaild((short)0);
        businessService.save(coursel);
        
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
}
