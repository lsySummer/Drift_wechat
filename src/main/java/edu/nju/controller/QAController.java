package edu.nju.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	@RequestMapping("/getQList")
	@ResponseBody  
	public Map<String, Object> getQList(HttpSession session){
		//String openid = (String)session.getAttribute("openid");
		Map<String, Object> map = new HashMap<String, Object>();
		String openid = "oRTgpwYGzwzbmz3DSAS-Z5WM37Yg";
		List<Object> qlist = (List)qaservice.getAllQuestion(); 
		map.put("qlist", qlist);
		return map;
	}
}
