package edu.nju.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.nju.service.ReserveService;

@Controller
@RequestMapping(value="/test")
public class TestController {
	@Autowired
	ReserveService service;
	
	@RequestMapping(value = "/register")
	public String register() {
		System.out.println(service.getOrder("oRTgpwXFnHUxJVa1ttSC8Tu_edXw"));
		return "success";
	}
}
