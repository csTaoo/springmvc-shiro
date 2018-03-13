package com.shitao.pay.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.shitao.pay.dao.PayCodeDAO;
import com.shitao.pay.entity.PayCode;

@Controller
@RequestMapping(value="/paycode")
public class PayCodeController {
	
	@Autowired
	private PayCodeDAO payCodeDao;
	
	@RequestMapping(value="list")
	public String list(HttpServletRequest req,HttpServletResponse res,Model model)
	{
		List<PayCode> tables = payCodeDao.list();
		
		model.addAttribute("paycodes", tables);
		return "modules/paycode/listPayCode";
	}
	
	@RequestMapping(value="upload")
	@ResponseBody
	public void upload(@RequestParam("file") MultipartFile file,HttpServletRequest req,HttpServletResponse rep)
	{
		StringBuilder path  = new StringBuilder();
		path.append(req.getSession().getServletContext().getRealPath("/"));
        path.append("/static/upload/heads/"+file.getOriginalFilename());
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
        	PayCode code = new PayCode();
        	code.setPath(newFile.getName());
        	code.setStatus((short)1);
        	payCodeDao.save(code);
			PrintWriter writer = rep.getWriter();
			writer.write(returnJson(file.getOriginalFilename()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="delete")
	public void delete(HttpServletRequest req,HttpServletResponse res)
	{
		res.setContentType("text/html");
		res.setCharacterEncoding("utf-8");
		PrintWriter writer = null;
		try
		{
			writer = res.getWriter();
			String Id = req.getParameter("paycodeId");
			PayCode code = payCodeDao.get(Id);
			String filePath = req.getSession().getServletContext().getRealPath("/");
			filePath += "/static/upload/heads/"+code.getPath();
			File file = new File(filePath);
			file.delete();
			payCodeDao.delete(code);
			writer.print("success");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			writer.print("fail");
		}
		
		
	}
	
	@ResponseBody
	@RequestMapping(value="update")
	@RequiresPermissions(value={"paycode:update"})
	public void update(@RequestParam(required = true)String paycodeId,HttpServletResponse response)
	{
		try
		{
			PayCode code = new PayCode();
			code.setId(paycodeId);
			PayCode s = payCodeDao.get(paycodeId);
			if(s.getStatus() == 1)
			{
				if(payCodeDao.checkActive() != null)
				{
					writeJson(response, "isActive");
				}
				else
				{
					payCodeDao.update(code);
				}
			}else
			{
				payCodeDao.update(code);
			}
			writeJson(response, "success");
		}catch(Exception e)
		{
			writeJson(response, "fail");
		}
	}
	
	@RequestMapping(value="getActive")
	@ResponseBody
	public String getActive(HttpServletRequest req,HttpServletResponse res)
	{
		try
		{
			PayCode code = payCodeDao.checkActive();
			return code.getPath();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "fail";
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
