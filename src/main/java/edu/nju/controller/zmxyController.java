package edu.nju.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.nju.service.zmxyService;

@Controller
@RequestMapping("/zmxy")
public class zmxyController {

	@Autowired
	private zmxyService zmxyService;
	
	@ResponseBody
	@RequestMapping(value = "/cb", method = RequestMethod.GET)
	public String insert()
	{
		zmxyService.cb();
		return "{\"success\"}";
	}
	
	
}