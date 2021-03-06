package edu.nju.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.nju.entities.Question;
import edu.nju.service.CommunityService;
import edu.nju.service.ManageService;
import edu.nju.service.QAService;
import edu.nju.service.UserService;
import edu.nju.utils.Utility;
import edu.nju.utils.WechatConfig;
import edu.nju.utils.WechatLoginUse;

@Controller
@RequestMapping(value = "/wechat")
public class WeChatController {

	@Autowired
	UserService service;
	
	@Autowired
	CommunityService cservice;
	
	@Autowired
	ManageService mservice;
	
	@Autowired
	QAService qaservice;
	
	private Logger log = Logger.getLogger(UserController.class);

	@RequestMapping(value = "/center")
	public String toCenter(HttpServletRequest request, String code,String state, HttpSession session,Model model)
			throws IOException {
//		if(code==null||code.equals("")){
//			code = (String) session.getAttribute("code");
//		}
//		if(state==null||state.equals("")){
//			state = (String) session.getAttribute("state");
//		}
		String redir = "jsp/index2";
		if(session.getAttribute("openid")==null) {
			redir = getWechatInfo( code, state,session);
		}
//		session.setAttribute("code", code);
//		session.setAttribute("state", state);
		log.info("code "+code+" state"+state);
		List<Question> qList = mservice.getRecommend();
		List<Long> qnumList = new ArrayList();
		if(qList!=null){
			for(Question q:qList){
				qnumList.add(qaservice.getAnswerNum(q.getId()));
			}
		}
		model.addAttribute("allnum", cservice.getOrderNum());
		model.addAttribute("todaynum", cservice.getTodayNum());
		model.addAttribute("qList", qList);
		model.addAttribute("qnumList", qnumList);
		return redir;
	}
	

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
				String url="http://drift.gmair.net/Drift_wechat/api/wechat/center/?code="+code+"&state="+state;
				getAddress(session,url,accessToken);
				return "jsp/index2";
			} else {
				return "jsp/index2";
			}
		} catch (JSONException e) {
			log.info(e);
			e.printStackTrace();
			return "jsp/index2";
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
