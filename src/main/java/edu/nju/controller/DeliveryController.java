package edu.nju.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.nju.entities.Device;
import edu.nju.service.ReserveService;

@Controller
@RequestMapping(value="/delivery")
public class DeliveryController {
	
	@Autowired
	ReserveService service;
	
	@RequestMapping(value = "/get")
	public void getDelivery(HttpSession session, HttpServletResponse response){
		JSONObject result=new JSONObject();
//		Device device = service.getDeviceByOpenId((String)session.getAttribute("openid"));
//		result.put("before", service.getBefore((String)session.getAttribute("openid")));
//		result.put("after", service.getAfter((String)session.getAttribute("openid")));
//		result.put("receive", service.getRecDid((String)session.getAttribute("openid")));
//		result.put("send", service.getSendDid((String)session.getAttribute("openid")));
		Device device = service.getDeviceByOpenId("oRTgpwXFnHUxJVa1ttSC8Tu_edXw");
		result.put("before", service.getBefore("oRTgpwXFnHUxJVa1ttSC8Tu_edXw"));
		result.put("after", service.getAfter("oRTgpwXFnHUxJVa1ttSC8Tu_edXw"));
		result.put("receive", service.getRecDid("oRTgpwXFnHUxJVa1ttSC8Tu_edXw"));
		result.put("send", service.getSendDid("oRTgpwXFnHUxJVa1ttSC8Tu_edXw"));
		result.put("deviceId", device.getId());
		System.out.println(result);
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
		String openid = (String) session.getAttribute("openid");
		boolean result = service.saveDelInfo(openid,deliveryNum);
		return "jsp/Delivery";
	}
	
	@RequestMapping(value = "/confirm")
	public String deliveryConfirm(HttpSession session, HttpServletResponse response){
		service.confirm((String)session.getAttribute("openid"));
		return "jsp/Delivery";
	}
}
