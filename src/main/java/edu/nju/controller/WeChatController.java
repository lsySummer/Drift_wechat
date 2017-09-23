package edu.nju.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.nju.service.UserService;
import edu.nju.utils.WechatLoginUse;


@Controller
@RequestMapping(value="/wechat")
public class WeChatController {
	
	@Autowired
	UserService service;
	
	private Logger log = Logger.getLogger(UserController.class);

	@RequestMapping(value = "/center")
	public String toCenter(String code, String state,HttpSession session) throws IOException {
		String htmlPage="center";
		String redir = getWechatInfo(htmlPage, code,session);
		return redir;
	}
	
//	@RequestMapping(value = "/service")
//	public String toService(String code, String state) throws IOException {
//		String htmlPage="service";    
//		String redir = getWechatInfo(htmlPage, code, state);
//		return redir;
//	}
//	@RequestMapping(value = "/tlogin")
//	public String tlogin(String code, String state) throws IOException {
//		String htmlPage="login";
//		String redir = getWechatInfo(htmlPage, code, state);
//		return redir;
//	}
	
	public String getWechatInfo(String htmlPage, String code, HttpSession session) throws UnsupportedEncodingException{
		String wechatInfo = WechatLoginUse.wechatInfo(code);
		JSONObject resultJson;
		try {
			log.info("用户信息:"+wechatInfo);
			resultJson = new JSONObject(wechatInfo);
			if(resultJson.get("message").equals("success")){
				String openid = resultJson.getString("openid");
				session.setAttribute("openid", openid);
				String nickname = "user";
				if(nickname==null || nickname.isEmpty()){
					return "redirect:../../"+htmlPage+".html";
				}else{
					nickname = URLEncoder.encode(nickname,"utf-8");
					return "jsp/index";
				}
				
				
			}else{
				return null;	
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return null;	
		} 
		
	}
}
