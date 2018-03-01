package com.shitao.sys.controller;

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

import com.shitao.common.utils.StringUtils;
import com.shitao.sys.entity.Func;
import com.shitao.sys.service.SystemService;

@Controller
@RequestMapping(value="/sys/func")
public class FuncController {
	
	@Autowired
	private SystemService systemService;
	
	
	@RequestMapping(value="funcIndex")
	public String funcIndex(HttpServletRequest res,HttpServletResponse re,Model model){
		
		String funcname = res.getParameter("funcname");
		List<Func> funcs = systemService.getAllFunc(funcname);
		model.addAttribute("funcs", funcs);
		
		
		
		return "modules/sys/funcIndex";
	}
	
	
	@RequestMapping(value="update")
	@ResponseBody
	public void update(@RequestParam(required=true) String id,HttpServletResponse res)
	{
		if(StringUtils.isBlank(id))
		{
			systemService.startStopFunc(id);
		}
		PrintWriter write = null;
		try
		{
			write = res.getWriter();
			write.write("success");
		}catch(IOException e)
		{
			e.printStackTrace();
		}finally
		{
			if(null != write)
			{
				write.close();
			}
		}
		
	}

}
