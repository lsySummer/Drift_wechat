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

import edu.nju.entities.UserInfo;
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
		String openid = (String) session.getAttribute("openid");
		String name = (String) session.getAttribute("name");
		String phone = (String) session.getAttribute("phone");
		String zmxyid = (String) session.getAttribute("zmxyid");
		String nickname = (String) session.getAttribute("nickname");
		String addressTemp = address + address_detail;
		Date startTemp = sdf.parse(startDate);
		Date endTemp = sdf.parse(endDate);
		service.setAddress(openid,zmxyid,addressTemp,phone,startTemp,endTemp,name,nickname);
		return "jsp/Result";
	}
	
	@RequestMapping(value = "/getUser")
	public String getUser(String openid) {
		service.getUser(openid);
		return "jsp/index";
	}
	
	@RequestMapping(value = "/getDetail")
	public void getDetail(HttpSession session, HttpServletResponse response) {
		boolean flag = false;
		JSONObject result=new JSONObject();
		result.put("nickName", session.getAttribute("nickname"));
		result.put("image", session.getAttribute("headimgurl"));
//		UserInfo user = service.getUser((String)session.getAttribute("openid"));
		UserInfo user = service.getUser("hahaha");
		if(user != null){
			flag = true;
			result.put("address", user.getAddress());
			result.put("name", user.getName());
			result.put("phone",user.getPhone());
		}
		result.put("flag", flag);
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
	
	@RequestMapping(value = "/save")
	public String saveUser(HttpSession session, String deliveryPerson, String address, String phone, String address_detail) {
		UserInfo user = new UserInfo();
//		user.setOpenid((String)session.getAttribute("openid"));
		user.setOpenid("hahaha");
		user.setAddress(address + " " + address_detail);
		user.setPhone(phone);
		user.setName(deliveryPerson);
		service.saveOrUpdate(user);
		return "jsp/MyIndex";
	}
}

