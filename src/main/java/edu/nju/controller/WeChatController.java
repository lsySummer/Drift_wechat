package edu.nju.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
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
import edu.nju.utils.HttpRequest;
import edu.nju.utils.HttpXmlClient;
import edu.nju.utils.Utility;
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
		String htmlPage = "center";
		String redir = getWechatInfo(htmlPage, code, state,session,request);
		 String ipAdd = HttpRequest.getIpAddress(request);
		 session.setAttribute("ipAddress", ipAdd);
		 log.info("ipAdd"+ipAdd);
		return redir;
	}

	public String getWechatInfo(String htmlPage, String code, String state,HttpSession session,HttpServletRequest request) throws UnsupportedEncodingException {
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
				 getAddress(session,request,url,accessToken);
				return "jsp/BaiduMap";
			} else {
				return null;
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/getAddress")
	public void getAddress(HttpSession session,HttpServletRequest request,String url,String accessToken) {
		String xml = HttpXmlClient.get("https://api.weixin.qq.com/cgi-bin/ticket/getticket?"
				+ "access_token=tcbD-nsjcaN3PZ8-7q_ol9UGkyThBEDLCQ_ViKdCeyaSN6HnjWJd4QL34D6YhuvG3l2KohxA3vUqWv_mmDbN5N1VcT9cuqVj7RK8pUkhuPW-Vg9cj9uKwCoNfERQPr6eYZPgADABCS&type=jsapi");
		net.sf.json.JSONObject jsonMap = net.sf.json.JSONObject.fromObject(xml);
		HashMap<String, String> map = new HashMap<String, String>();
		@SuppressWarnings("unchecked")
		Iterator<String> it = jsonMap.keys();
		while (it.hasNext()) {
			String key = (String) it.next();
			String u = jsonMap.get(key).toString();
			map.put(key, u);
		}
		String jsapi_ticket = map.get("ticket");
		log.info("jsapi_ticket=" + jsapi_ticket);

		// 获取签名signature
		String noncestr = UUID.randomUUID().toString();
		String timestamp = Long.toString(System.currentTimeMillis() / 1000); 
        //以为我配置的菜单是http://yo.bbdfun.com/first_maven_project/，最后是有"/"的，所以url也加上了"/"
        log.info(url);
		String str = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url="
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
