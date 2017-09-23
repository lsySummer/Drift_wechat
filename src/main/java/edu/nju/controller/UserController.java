package edu.nju.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

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
	public String setAddress(String address,String startDate,String endDate,String address_detail,HttpSession session) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		session.setAttribute("openid", "test");
		session.setAttribute("name", "test");
		session.setAttribute("phone", "test");
		session.setAttribute("zmxyid", "test");
		String openid = (String) session.getAttribute("openid");
		String name = (String) session.getAttribute("name");
		String phone = (String) session.getAttribute("phone");
		String zmxyid = (String) session.getAttribute("zmxyid");
		String addressTemp = address + address_detail;
		Date startTemp = sdf.parse(startDate);
		Date endTemp = sdf.parse(endDate);
		service.setAddress(openid,zmxyid,addressTemp,phone,startTemp,endTemp,name);
		return "jsp/result";
	}
	
	@RequestMapping(value = "/getUser")
	public String getUser(String openid) {
		service.getUser(openid);
		return "jsp/index";
	}
}

