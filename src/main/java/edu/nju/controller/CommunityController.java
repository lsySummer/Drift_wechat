package edu.nju.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import edu.nju.service.CommunityService;
import edu.nju.service.UserService;

@Controller
@RequestMapping(value="/community")
public class CommunityController {
	private Logger log = Logger.getLogger(CommunityController.class);
	
	@Autowired
	CommunityService service;
	
	@Autowired
	UserService userService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/upload",method = RequestMethod.POST)
	public void upload(@RequestParam(value = "file") MultipartFile photo, HttpSession session, HttpServletResponse response){
		if(session.getAttribute("photo") == null){
			List<MultipartFile> temp = new ArrayList<MultipartFile>();
			session.setAttribute("photo", temp);
		}
		List<MultipartFile> photoLists = (List<MultipartFile>) session.getAttribute("photo");
		JSONObject result=new JSONObject();
		photoLists.add(photo);
		result.put("status", "200");
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
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/txt")
	public void textUplood(String txt, HttpSession session, HttpServletResponse response){
		log.info("txt"+txt);
		JSONObject result = new JSONObject();
		List<MultipartFile> photoLists = (List<MultipartFile>) session.getAttribute("photo");
//		service.addComment("oRTgpwQkDZKxGFvNnfKpJLWvxsyw", photoLists, txt, Float.parseFloat(methanal));
		service.addComment((String)session.getAttribute("openid"), photoLists, txt);
		result.put("status", "200");
		try {
			//设置订单状态为Step4
			userService.setUserState((String)session.getAttribute("openid"), 4);
			PrintWriter out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();
			session.removeAttribute("photo");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
