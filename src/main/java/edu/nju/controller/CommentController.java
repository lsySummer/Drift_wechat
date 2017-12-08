package edu.nju.controller;

import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.nju.entities.UserComment;
import edu.nju.service.CommunityService;
@Controller
@RequestMapping(value="/comment")
public class CommentController {
	@Autowired
	CommunityService service;
	
	@RequestMapping(value = "/getComment")
	@ResponseBody  
	public Map<String, Object> getComment(String openid) {
		Map<String, Object> map = new HashMap<String, Object>();
		UserComment uc = service.getComment(openid);
		map.put("uc", uc);
		return map;
	}
}
