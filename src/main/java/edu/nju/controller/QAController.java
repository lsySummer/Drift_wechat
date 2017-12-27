package edu.nju.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.nju.service.QAService;

@Controller
@RequestMapping("/QA")
public class QAController {
	@Autowired
	QAService qaservice;
	
	@RequestMapping("/publishQ")
	@ResponseBody  
	public String publishQuestion(HttpSession session,String title,String content){
		//String openid = (String)session.getAttribute("openid");
		String openid = "oRTgpwYGzwzbmz3DSAS-Z5WM37Yg";
		if(qaservice.publishQuestion(openid, title, content)){
			return "1";
		}
		else{
			return "0";
		}
	}
}
