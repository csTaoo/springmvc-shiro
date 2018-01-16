package com.shitao.orders.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value="/a/order")
public class OrdersController {
	
	
	
	@RequestMapping(value="listNonPayOrder")
	public void listNonPayOrder()
	{
		
	}

}
