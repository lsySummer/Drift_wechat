package edu.nju.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.nju.entities.Point;

@Controller
@RequestMapping(value="/map")
public class MapController {
	
	@RequestMapping(value = "/map")
	public String getMap1(String openid) {
		return "jsp/BaiduMap";
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