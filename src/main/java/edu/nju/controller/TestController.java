package edu.nju.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.nju.service.TestService;


@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired
	private TestService testService;
	
	@ResponseBody
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert()
	{
		testService.test();
		return "{\"success\"}";
	}
}