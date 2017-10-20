package edu.nju.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.nju.entities.UserInfo;
import edu.nju.service.ReserveService;
import edu.nju.service.UserService;

@Controller
@RequestMapping(value="/order")
public class OrderController {
	
	@Autowired
	ReserveService service;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/get")
	public void getDetail(HttpSession session, HttpServletResponse response){
		Gson result = new GsonBuilder().create();
//		String openid = (String) session.getAttribute("openid");
		try {
			PrintWriter out = response.getWriter();
			out.print(service.getOrder((String)session.getAttribute("openid")));
//			out.print(service.getOrder("hahaha"));
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/set")
	public String ZMXY(HttpSession session) {
		UserInfo user = userService.getUser((String)session.getAttribute("openid"));
//		UserInfo user = userService.getUser("hahaha");
		if(user == null){
			return "jsp/MyIndex";
		}else if(user.getZmxyid() == null){
			return "jsp/index";
		}else if(service.checkReserve((String)session.getAttribute("openid"))){
			return "jsp/Warn";
		}
		else{
			service.makeOrder((String)session.getAttribute("openid"), 0, 0);
//			service.makeOrder("hahaha", 0, 0);
			return "jsp/Result";
		}
	}
	
}
