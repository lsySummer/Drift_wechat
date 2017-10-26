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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.nju.entities.Device;
import edu.nju.entities.UserInfo;
import edu.nju.service.ReserveGetService;
import edu.nju.service.ReserveService;
import edu.nju.service.UserService;

@Controller
@RequestMapping(value="/order")
public class OrderController {
	
	@Autowired
	ReserveGetService getservice;
	
	@Autowired
	ReserveService service;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/get")
	public void getDetail(HttpSession session, HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			out.print(getservice.getOrder((String)session.getAttribute("openid")));
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/set")
	public String ZMXY(HttpSession session,Model model) {
		UserInfo user = userService.getUser((String)session.getAttribute("openid"));
		if(user == null){
			model.addAttribute("state", "no");
			return "jsp/MyIndex";
		}else if(user.getZmxyid() == null){
			return "jsp/index";
		}else if(!service.checkReserve((String)session.getAttribute("openid"))){
			return "jsp/Warn";
		}
		else{
			return "jsp/DateChoose";
		}
	}
	
	@RequestMapping(value = "/getDate")
	public void getDate(HttpSession session, HttpServletResponse response){
		Device device = service.reserveDevice((String)session.getAttribute("openid"), 0);
		JSONObject result=new JSONObject();
		try {
			PrintWriter out = response.getWriter();
			result.put("number", device.getNumber());
			result.put("data",getservice.getByDeviceId(device.getId()));
			out.print(result);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/date")
	public String confirm(String startDate, HttpSession session) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(startDate);
		service.makeOrder((String)session.getAttribute("openid"), 0, date);
		return "jsp/Result";
	}
}
