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

import edu.nju.service.ReserveService;

@Controller
@RequestMapping(value="/order")
public class OrderController {
	
	@Autowired
	ReserveService service;
	
	@RequestMapping(value = "/get")
	public void getDetail(HttpSession session, HttpServletResponse response){
		Gson result = new GsonBuilder().create();
//		String openid = (String) session.getAttribute("openid");
		try {
			PrintWriter out = response.getWriter();
			out.print(service.getOrder("hahaha"));
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
