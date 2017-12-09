package edu.nju.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.nju.entities.Point;
import edu.nju.model.UserVO;
import edu.nju.service.UserService;
import edu.nju.utils.Constants;

@Controller
@RequestMapping(value="/map")
public class MapController {
	@Autowired
	UserService service;
	
	@RequestMapping(value = "/map")
	public String getMap1(String openid,HttpSession session) {
		return "jsp/BaiduMap";
	}
	
	@RequestMapping(value = "/getMap")
	@ResponseBody  
	public Map<String, Object> getMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> list1 = new ArrayList<Object>();
		JSONObject myJsonObject = new JSONObject(service.getUserVO());
		if(myJsonObject.has("Constants.RESPONSE_DATA_KEY")){
			JSONArray userAll = (JSONArray) myJsonObject.get(Constants.RESPONSE_DATA_KEY);
			if(userAll.length()>0){
			  for(int i=0;i<userAll.length();i++){
			    JSONObject job = userAll.getJSONObject(i);
			    list1.add(job);
			  }
			}
		}
		map.put("userArr", list1);
		return map;
	}
}
