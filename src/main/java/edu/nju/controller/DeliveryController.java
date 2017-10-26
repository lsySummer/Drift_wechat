package edu.nju.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.nju.entities.Device;
import edu.nju.service.ReserveGetService;
import edu.nju.service.ReserveService;

@Controller
@RequestMapping(value="/delivery")
public class DeliveryController {
	
	@Autowired
	ReserveGetService getservice;
	
	@Autowired
	ReserveService service;
	
	@RequestMapping(value = "/get")
	public void getDelivery(HttpSession session, HttpServletResponse response){
//		session.setAttribute("openid", "12345");
		JSONObject result=new JSONObject();
		Device device = getservice.getDeviceByOpenId((String)session.getAttribute("openid"));
		try{
			result.put("before", getservice.getBefore((String)session.getAttribute("openid")).getName());
			result.put("after", getservice.getAfter((String)session.getAttribute("openid")).getName());
			result.put("deviceId", device.getNumber());
		}catch (Exception e){
			result.put("before", "null");
		}
		result.put("receive", getservice.getRecDid((String)session.getAttribute("openid")));
		result.put("send", getservice.getSendDid((String)session.getAttribute("openid")));
		result.put("enable", getservice.getOrderState((String)session.getAttribute("openid")));
	
		try {
			PrintWriter out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/set")
	public String setDelivery(String deliveryNum, HttpSession session, HttpServletResponse response) throws ParseException{
		service.saveDelInfo((String) session.getAttribute("openid"),deliveryNum);
		return "jsp/Delivery";
	}
	
	@RequestMapping(value = "/confirm")
	public String deliveryConfirm(HttpSession session, HttpServletResponse response){
		service.confirm((String)session.getAttribute("openid"));
		return "jsp/Orders";
	}
}
