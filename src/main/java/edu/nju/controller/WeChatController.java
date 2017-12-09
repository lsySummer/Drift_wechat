package edu.nju.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.nju.service.UserService;
import edu.nju.utils.Utility;
import edu.nju.utils.WechatConfig;
import edu.nju.utils.WechatLoginUse;

@Controller
@RequestMapping(value = "/wechat")
public class WeChatController {

	@Autowired
	UserService service;

	private Logger log = Logger.getLogger(UserController.class);

	@RequestMapping(value = "/center")
	public String toCenter(HttpServletRequest request, String code,String state, HttpSession session)
			throws IOException {
//		if(code==null||code.equals("")){
//			code = (String) session.getAttribute("code");
//		}
//		if(state==null||state.equals("")){
//			state = (String) session.getAttribute("state");
//		}
		String redir = getWechatInfo( code, state,session);
//		session.setAttribute("code", code);
//		session.setAttribute("state", state);
		log.info("code "+code+" state"+state);
		return redir;
	}
	
//	@RequestMapping(value = "/getOrder")//管理员修改订单
//	public String getOrder(HttpServletRequest request, String code,String state, HttpSession session)
//			throws IOException {
//		session.setAttribute("code", code);
//		session.setAttribute("state", state);
//		String wechatInfo = WechatLoginUse.wechatInfo(code);
//		JSONObject resultJson;
//		try {
//			log.info("用户信息:" + wechatInfo);
//			resultJson = new JSONObject(wechatInfo);
//			if (resultJson.get("message").equals("success")) {
//				String openid = resultJson.getString("openid");
//				String nickname = resultJson.getString("nickname");
//				String headimgurl = resultJson.getString("headimgurl");
//				session.setAttribute("openid", openid);
//				session.setAttribute("nickname", nickname);
//				session.setAttribute("headimgurl", headimgurl);
//				return "jsp/Orders";
//			} else {
//				return "jsp/Orders";
//			}
//		} catch (JSONException e) {
//			log.info(e);
//			e.printStackTrace();
//			return "jsp/Orders";
//		}
//	}
//	
//	
//	@RequestMapping(value = "/deliver")//商家发货提醒
//	public String deliver(HttpServletRequest request, String code,String state, HttpSession session)
//			throws IOException {
//		session.setAttribute("code", code);
//		session.setAttribute("state", state);
//		String wechatInfo = WechatLoginUse.wechatInfo(code);
//		JSONObject resultJson;
//		try {
//			log.info("用户信息:" + wechatInfo);
//			resultJson = new JSONObject(wechatInfo);
//			if (resultJson.get("message").equals("success")) {
//				String openid = resultJson.getString("openid");
//				String nickname = resultJson.getString("nickname");
//				String headimgurl = resultJson.getString("headimgurl");
//				session.setAttribute("openid", openid);
//				session.setAttribute("nickname", nickname);
//				session.setAttribute("headimgurl", headimgurl);
//				return "jsp/Delivery";
//			} else {
//				return "jsp/Delivery";
//			}
//		} catch (JSONException e) {
//			log.info(e);
//			e.printStackTrace();
//			return "jsp/Delivery";
//		}
//	}
	

	public String getWechatInfo(String code, String state,HttpSession session) throws UnsupportedEncodingException {
		String wechatInfo = WechatLoginUse.wechatInfo(code);
		JSONObject resultJson;
		try {
			log.info("用户信息:" + wechatInfo);
			resultJson = new JSONObject(wechatInfo);
			if (resultJson.get("message").equals("success")) {
				String openid = resultJson.getString("openid");
				String nickname = resultJson.getString("nickname");
				String headimgurl = resultJson.getString("headimgurl");
				String accessToken = resultJson.getString("access_token");
				session.setAttribute("openid", openid);
				session.setAttribute("nickname", nickname);
				session.setAttribute("headimgurl", headimgurl);
				String url="http://drift.gmair.net/Drift_wechat/api/wechat/center?code="+code+"&state="+state;
				 getAddress(session,url,accessToken);
				return "jsp/BaiduMap";
			} else {
				return "jsp/BaiduMap";
			}
		} catch (JSONException e) {
			log.info(e);
			e.printStackTrace();
			return "jsp/BaiduMap";
		}
	}

	@RequestMapping(value = "/getAddress")
	public void getAddress(HttpSession session,String url,String accessToken) {
//		String xml = HttpXmlClient.get("https://api.weixin.qq.com/cgi-bin/ticket/getticket?"
//				+ "access_token="+accessToken+"&type=jsapi");
//		net.sf.json.JSONObject jsonMap = net.sf.json.JSONObject.fromObject(xml);
//		HashMap<String, String> map = new HashMap<String, String>();
//		@SuppressWarnings("unchecked")
//		Iterator<String> it = jsonMap.keys();
//		while (it.hasNext()) {
//			String key = (String) it.next();
//			String u = jsonMap.get(key).toString();
//			map.put(key, u);
//		}
//		String jsapi_ticket = map.get("ticket");
		JSONObject ticket = WechatConfig.getJsApiTicketByWX();
		log.info("jsapi_ticket=" + ticket.get("ticket"));

		// 获取签名signature
		String noncestr = UUID.randomUUID().toString();
		String timestamp = Long.toString(System.currentTimeMillis() / 1000); 
        //以为我配置的菜单是http://yo.bbdfun.com/first_maven_project/，最后是有"/"的，所以url也加上了"/"
        log.info(url);
		String str = "jsapi_ticket=" + ticket.get("ticket") + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url="
				+ url;
		// // sha1加密
		String signature = Utility.SHA1(str);
		log.info("noncestr=" + noncestr);
		log.info("timestamp=" + timestamp);
		log.info("signature=" + signature);
		session.setAttribute("noncestr", noncestr);
		session.setAttribute("timestamp", timestamp);
		session.setAttribute("signature", signature);
		// 最终获得调用微信js接口验证需要的三个参数noncestr、timestamp、signature
	}

}
