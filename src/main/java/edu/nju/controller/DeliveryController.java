package edu.nju.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping(value="/delivery")
public class DeliveryController {

	@RequestMapping(value = "/get")
	public void getDelivery(HttpSession session, HttpServletResponse response){
//		String openid = (String) session.getAttribute("openid");
		Gson result = new GsonBuilder().create();
		try {
			PrintWriter out = response.getWriter();
			out.print(result.toJson(null));
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/set")
	public String setDelivery(String deliveryDate, String deliveryNum, String deliveryPerson, HttpSession session, HttpServletResponse response) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String openid = (String) session.getAttribute("openid");
		Date dateTemp = sdf.parse(deliveryDate);
		return "jsp/success";
	}
	
	@RequestMapping(value = "/confirm")
	public String deliveryConfirm(HttpSession session, HttpServletResponse response){
		return "jsp/success";
	}
}
