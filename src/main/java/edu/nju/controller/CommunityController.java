package edu.nju.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

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
	
	@Autowired
	CommunityService service;
	
	
	@RequestMapping(value = "/upload",method = RequestMethod.POST)
	public void upload(@RequestParam(value = "file") MultipartFile photo, HttpSession session, HttpServletResponse response){
		JSONObject result=new JSONObject();
		
		result.put("test", "test");
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
}
