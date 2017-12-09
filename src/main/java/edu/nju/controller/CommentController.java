package edu.nju.controller;

import java.util.HashMap;
import java.util.Map;


import org.json.JSONObject;
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
		JSONObject result=new JSONObject();
		if(uc!=null){
			result.put("id", uc.getId());
			result.put("openid", uc.getOpenid());
			result.put("orderid", uc.getOrderid());
			result.put("comment", uc.getComment());
			result.put("picURLS", uc.getPicURLS());
		}
		map.put("uc", result);
		return map;
	}
}
