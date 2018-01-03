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
import edu.nju.entities.UserInfo;
import edu.nju.service.ReserveGetService;
import edu.nju.service.ReserveService;

@Controller
@RequestMapping(value="/delivery")
public class DeliveryController {
	
	@Autowired
	ReserveGetService getservice;
	
	@Autowired
	ReserveService service;
	
	@RequestMapping(value = "/step2")
	public void getStep2(HttpSession session, HttpServletResponse response){
		session.setAttribute("openid", "test");
		JSONObject result=new JSONObject();
		Device device = getservice.getDeviceByOpenId((String)session.getAttribute("openid"));
		UserInfo before = getservice.getBefore((String)session.getAttribute("openid"));
		try{
			result.put("nickName", before.getNickName());
			result.put("deviceId", device.getNumber());
		}catch (Exception e){
			result.put("nickName", "null");
		}
		result.put("receive", getservice.getRecDid((String)session.getAttribute("openid")));
//		result.put("enable", getservice.getOrderState((String)session.getAttribute("openid")));
	
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
	
	@RequestMapping(value = "/step4")
	public void getStep4(HttpSession session, HttpServletResponse response){
		session.setAttribute("openid", "test");
		JSONObject result=new JSONObject();
		Device device = getservice.getDeviceByOpenId((String)session.getAttribute("openid"));
		UserInfo after = getservice.getAfter((String)session.getAttribute("openid"));
		try{
			result.put("name", after.getName());
			result.put("address", after.getAddress());
			result.put("phone", after.getPhone());
			result.put("deviceId", device.getNumber());
		}catch (Exception e){
			result.put("name", "null");
		}
		result.put("send", getservice.getSendDid((String)session.getAttribute("openid")));
//		result.put("enable", getservice.getOrderState((String)session.getAttribute("openid")));
	
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
		return "jsp/Orders/Step5.jsp";
	}
	
	@RequestMapping(value = "/confirm")
	public String deliveryConfirm(HttpSession session, HttpServletResponse response){
		service.confirm((String)session.getAttribute("openid"));
		return "jsp/Orders/Step3.jsp";
	}
}
