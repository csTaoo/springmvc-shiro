package com.shitao.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		
		List<Func> funcs = systemService.getAllFunc();
		model.addAttribute("funcs", funcs);
		
		
		
		return "modules/sys/funcIndex";
	}
	
	@RequestMapping(value="modifyFunc")
	public String modifyFunc(@RequestParam(required=true) String id,HttpServletRequest req,Model model)
	{
		if(StringUtils.isBlank(id))
		{
			Func func = systemService.getFunc(id);
			model.addAttribute("func", func);
		}
		return "/modules/sys/modifyFunc";
		
	}

}
