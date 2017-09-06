package com.shitao.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shitao.sys.entity.Func;
import com.shitao.sys.service.FuncService;

@Controller
@RequestMapping(value="/sys/func")
public class FuncController {
	
	@Autowired
	private FuncService funcService;
	
	
	@RequestMapping(value="funcIndex")
	public String funcIndex(HttpServletRequest res,HttpServletResponse re,Model model){
		
		List<Func> funcs = funcService.getAllFunc();
		model.addAttribute("funcs", funcs);
		
		
		
		return "modules/sys/funcIndex";
	}
	

}
