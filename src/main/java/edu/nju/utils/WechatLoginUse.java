package edu.nju.utils;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import edu.nju.controller.UserController;

public class WechatLoginUse {
	
	
	
	public static String wechatInfo(String code){
		
	    Logger log = Logger.getLogger(UserController.class);
		//access token
		JSONObject resultJson = WechatConfig.getAccessToken(code);
		log.info("resultJson"+resultJson.toString());
		JSONObject wechatInfo = new JSONObject();
		try{
		String openid = resultJson.getString("openid");
		String accessToken = resultJson.getString("access_token");
		
		//user info
		JSONObject userInfoJson = WechatConfig.getWXUserInfo(accessToken, openid);
//		System.out.println("昵称和头像："+userInfoJson);
		String name = userInfoJson.getString("nickname");
		String head = userInfoJson.getString("headimgurl");
		
		//返回前台
		wechatInfo.put("message", "success");
		wechatInfo.put("openid", openid);
		wechatInfo.put("nickname", name);
		wechatInfo.put("headimgurl", head);
		wechatInfo.put("access_token", accessToken);
		}catch(JSONException e){
			//不是从微信进去的
			wechatInfo.put("message", "fail");
		}
		return wechatInfo.toString();
	
	}
}
