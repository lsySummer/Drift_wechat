package edu.nju.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping(value="/order")
public class OrderController {
	public class Orders{
		private int number;
		private String name;
		public Orders(int string, String string2) {
			// TODO Auto-generated constructor stub
			this.number = string;
			this.name = string2;
		}	
	}
	
	@RequestMapping(value = "/get")
	public void getDetail(HttpSession session, HttpServletResponse response){
		Orders orders = new Orders(1234,"test");
		Gson result = new GsonBuilder().create();
//		String openid = (String) session.getAttribute("openid");
		try {
			PrintWriter out = response.getWriter();
			out.print(result.toJson(orders));
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
