package edu.nju.controller;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	@RequestMapping(value = "/setZMInfo")
	public String setZMInfo(String openId,String transactionid,int score) {
		service.setZMInfo(openId,transactionid,score);
		return "jsp/index";
	}
	
	@RequestMapping(value = "/setAddress")
	public String setAddress(String openid,String address,String phone,Date startDate,Date endDate) {
		service.setAddress(openid,address,phone,startDate,endDate);
		return "jsp/index";
	}
	
	@RequestMapping(value = "/getUser")
	public String getUser(String openid) {
		service.getUser(openid);
		return "jsp/index";
	}
}

