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
		u.setOpenid("thisiscomponyinfomation");
		u.setNickName("Compony");
		userService.saveOrUpdate(u);
//		System.out.println(userService.getUser("thisiscomponyinfomation").toString());
		return "success";
	}
}
