package edu.nju.utils;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import edu.nju.controller.UserController;

public class WechatLoginUse {
	
	
	
	public static String wechatInfo(String code){
		
	    Logger log = Logger.getLogger(UserController.class);
		//access token
		JSONObject resultJson = WechatConfig.getAccessToken(code);
		log.info("resultJson"+resultJson.toString());
		String openid = resultJson.getString("openid");
		String accessToken = resultJson.getString("access_token");
		
		//user info
//		JSONObject userInfoJson = WechatConfig.getWXUserInfo(accessToken, openid);
//		System.out.println("昵称和头像："+userInfoJson);
//		String name = userInfoJson.getString("nickname");
//		String head = userInfoJson.getString("headimgurl");
		
		//返回前台
		JSONObject wechatInfo = new JSONObject();
		wechatInfo.put("message", "success");
		wechatInfo.put("openid", openid);
//		wechatInfo.put("nickname", name);
//		wechatInfo.put("headimgurl", head);
		return wechatInfo.toString();
	
	}
}
