package edu.nju.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.nju.service.ManageService;
import edu.nju.service.ReserveGetService;
import edu.nju.service.ReserveService;
import edu.nju.service.UserService;

@Controller
@RequestMapping(value="/test")
public class TestController {
	@Autowired
	ReserveService service;
	@Autowired
	UserService userService;
	@Autowired
	ManageService mservice;
	@Autowired
	ReserveGetService gservice;
	
	@RequestMapping(value = "/insert")
	public String register() {
//		service.confirm("hahaha");
//		System.out.println(service.getBefore("hahaha").toString());
//		System.out.println(userService.getUser("thisiscomponyinfomation").toString());
//		userService.setZMXY("ZMXYopenID", "UPDATEzmxyid");
//		service.saveDelInfo("hahaha","7654");
//		service.makeOrder("qwertyu", 0, 2);
//		System.out.println(service.checkReserve("12345"));
//		System.out.println(service.checkReserve("hahaha"));
//		System.out.println(service.getBefore("hahaha").toString());
//		System.out.println(service.getOrderState("oRTgpweSZbOxfrg9H57JwuPwMJLo"));
//		service.confirm("oRTgpweSZbOxfrg9H57JwuPwMJLo");
//		System.out.println(service.checkReserve("oRTgpweSZbOxfrg9H57JwuPwMJLo"));
//		System.out.println(mservice.getUsers().size());
		List<String> list = gservice.getByDeviceId(1+"");
		for(int i = 0 ;i<list.size();i++){
			System.out.println(list.get(i));
		}
		return "success";
	}
}
