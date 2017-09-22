package edu.nju.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.nju.service.TestService;


@Controller
@RequestMapping("/test")
public class TestController {

	private Logger log = Logger.getLogger(UserController.class);
	@Autowired
	private TestService testService;
	
	@RequestMapping("/insert")
	public String insert(String name)
	{
		System.out.println("name");
//		testService.test();
		log.info("this is a log!!!!!!!!!");
		return "jsp/index";
	}
}