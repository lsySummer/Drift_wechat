package edu.nju.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.nju.entities.Point;
import edu.nju.model.UserVO;

@Controller
@RequestMapping(value="/map")
public class MapController {
	
	@RequestMapping(value = "/map")
	public String getMap1(String openid,HttpSession session) {
		session.setAttribute("ip", "218.94.159.98");
		return "jsp/BaiduMap";
	}
	
	@RequestMapping(value = "/getMap")
	@ResponseBody  
	public Map<String, Object> getMap() {		
		Map<String, Object> map = new HashMap<String, Object>(); 
		UserVO user1 = new UserVO("1", "aaa", "江苏省南京市鼓楼区北京西路二号新村小区12栋101", 0, 0, new Date(11110), "甲醛仪1号",1);
		UserVO user2 = new UserVO("2", "bbb", "江苏省南京市鼓楼区汉口路22号", 0, 0, new Date(10110), "甲醛仪2号",1);
		UserVO user3 = new UserVO("1", "aaa", "江苏省南京市鼓楼区上海路大锏银巷17号1栋601", 0, 0, new Date(1110), "甲醛仪3号",0);
		UserVO user4 = new UserVO("1", "aaa", "江苏省南京市鼓楼区南京大学", 0, 0, new Date(111100), "甲醛仪4号",0); 
		
		ArrayList<UserVO> userArr1 = new ArrayList<UserVO>();
		ArrayList<UserVO> userArr2 = new ArrayList<UserVO>();
		
		userArr1.add(user1);
		userArr1.add(user2);
		userArr2.add(user3);
		userArr2.add(user4);
		
		map.put("userArr1", userArr1);
		map.put("userArr2", userArr2);
		return map;
	}
	
	
	@RequestMapping(value = "/getMapData")
	@ResponseBody  
	public Map<String, Object> getMapdata() {
		Map<String, Object> map = new HashMap<String, Object>();  
		double myPx = 116.345555;
		double myPy = 40.018661;
		int x = 3;
		int y = 8;
		ArrayList<Point> poiArr1 = new ArrayList<Point>();
		ArrayList<Point> poiArr2 = new ArrayList<Point>();
		
		int d1 = x + (int)(Math.random() * (y - x));
		int d2 = x + (int)(Math.random() * (y - x));
		for(int i=0;i<d1;i++){
			String title = "检测仪"+(i+1);
			poiArr1.add(MapController.randomPoint(title));
		}
		
		for(int i=0;i<d2;i++){
			String title = "空气净化器"+(i+1);
			poiArr2.add(MapController.randomPoint(title));
		}
		
		map.put("myLocation", new Point("我的位置",myPx,myPy));
		map.put("markerArr1", poiArr1);
		map.put("markerArr2", poiArr2);
		return map;
	}

	public static Point randomPoint(String title){
		double myPx = 116.345555;
		double myPy = 40.018661;
		double px;
		double py;
		if(Math.random()>0.5){
			px = myPx - Math.random()/1000;
		}
		else{
			px = myPx + Math.random()/1000;
		}
		
		if(Math.random()>0.5){
			py = myPy - Math.random()/1000;
		}
		else{
			py = myPy +Math.random()/1000;
		}
		return new Point(title,px,py);
	}
}
