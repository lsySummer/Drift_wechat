package edu.nju.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.nju.entities.UserInfo;
import edu.nju.service.ReserveService;
import edu.nju.service.UserService;

@Controller
@RequestMapping(value="/test")
public class TestController {
	@Autowired
	ReserveService service;
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/insert")
	public String register() {
		UserInfo u = new UserInfo();
		u.setOpenid("hahaha");
		u.setAddress("北京");
		u.setPhone("15850512345");
		u.setName("公司");
		userService.saveOrUpdate(u);
//		System.out.println(userService.getUser("thisiscomponyinfomation").toString());
		return "success";
	}
}
