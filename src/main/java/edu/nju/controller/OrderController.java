package edu.nju.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.nju.dao.impl.UserDaoImpl;
import edu.nju.entities.Device;
import edu.nju.entities.UserInfo;
import edu.nju.service.ReserveGetService;
import edu.nju.service.ReserveService;
import edu.nju.service.UserService;

@Controller
@RequestMapping(value="/order")
public class OrderController {
	
	private static final Logger log = Logger.getLogger(UserDaoImpl.class);
	
	@Autowired
	ReserveGetService getservice;
	
	@Autowired
	ReserveService service;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/get")
	public void getDetail(HttpSession session, HttpServletResponse response){
//		session.setAttribute("openid", "oRTgpwQkDZKxGFvNnfKpJLWvxsyw");
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
	public ModelAndView ZMXY(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		UserInfo user = userService.getUser((String)session.getAttribute("openid"));
		if(user == null){
			modelAndView.addObject("state", "no");
			modelAndView.setViewName("redirect:/jsp/MyIndex.jsp");
			return modelAndView;
		}else if(user.getZmxyid() == null){
			modelAndView.getModel().put("phone", user.getPhone());
			modelAndView.getModel().put("name", user.getName());
			modelAndView.setViewName("redirect:/jsp/index.jsp");
			return modelAndView;
		}else if(!service.checkReserve((String)session.getAttribute("openid"))){
			modelAndView.setViewName("redirect:/jsp/Warn.jsp");
			return modelAndView;
		}
		else{
			modelAndView.setViewName("redirect:/jsp/DateChoose.jsp");
			return modelAndView;
		}
	}
	
	@RequestMapping(value = "/getDate")
	public void getDate(HttpSession session, HttpServletResponse response){
		log.info(session.getAttribute("openid"));
		Device device = service.reserveDevice((String)session.getAttribute("openid"), 0);
		JSONObject result=new JSONObject();
		try {
			PrintWriter out = response.getWriter();
			result.put("number", device.getNumber());
			result.put("id", device.getId());
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
	public void confirm(String deviceNum, String startDate, HttpSession session, HttpServletResponse response) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(startDate);
		service.makeOrder(deviceNum, (String)session.getAttribute("openid"), 0, date);
		try {
			PrintWriter out = response.getWriter();
			out.print("200");
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
