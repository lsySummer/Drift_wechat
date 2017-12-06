package edu.nju.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import edu.nju.service.CommunityService;

@Controller
@RequestMapping(value="/community")
public class CommunityController {
	List<MultipartFile> photoLists = new ArrayList<MultipartFile>();
	
	@Autowired
	CommunityService service;
	
	@RequestMapping(value = "/upload",method = RequestMethod.POST)
	public void upload(@RequestParam(value = "file") MultipartFile photo, HttpSession session, HttpServletResponse response){
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
	
	@RequestMapping(value = "/txt")
	public void textUplood(String txt, HttpSession session, HttpServletResponse response){
		JSONObject result = new JSONObject();
//		service.addComment("oRTgpwQkDZKxGFvNnfKpJLWvxsyw", photoLists, txt);
		service.addComment((String)session.getAttribute("openid"), photoLists, txt);
		result.put("status", "200");
		try {
			PrintWriter out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();
			photoLists.clear();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
