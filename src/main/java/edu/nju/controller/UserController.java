package edu.nju.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.nju.service.UserService;


@Controller
@RequestMapping(value="/user")
public class UserController {
	@Autowired
	UserService service;
	private Logger log = Logger.getLogger(UserController.class);
	
	@RequestMapping(value = "/register")
	public String register(String openId,String nickName) {
		log.info("openId"+openId);
		log.info("nickName"+nickName);
//				service.register(openId,nickName);
		return "jsp/index";
	}
}

