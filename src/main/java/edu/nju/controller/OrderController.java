package edu.nju.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.nju.dao.impl.UserDaoImpl;
import edu.nju.entities.Device;
import edu.nju.entities.Question;
import edu.nju.entities.UserInfo;
import edu.nju.service.ManageService;
import edu.nju.service.QAService;
import edu.nju.service.ReserveGetService;
import edu.nju.service.ReserveService;
import edu.nju.service.UserService;

@Controller
@RequestMapping(value="/order")
public class OrderController {
	
	private static final Logger log = Logger.getLogger(UserDaoImpl.class);
	
	@Autowired
	ReserveGetService getservice;
	
	@Autowired
	ReserveService service;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ManageService manageService;
	
	@Autowired
	QAService qaservice;
	
	@RequestMapping(value = "/get")
	public void getDetail(HttpSession session, HttpServletResponse response){
//		session.setAttribute("openid", "oRTgpweSZbOxfrg9H57JwuPwMJLo");
		try {
			PrintWriter out = response.getWriter();
			out.print(getservice.getOrder((String)session.getAttribute("openid")));
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/set")
	public ModelAndView ZMXY(HttpSession session) {
		//测试入口
//		session.setAttribute("openid", "test");
		ModelAndView modelAndView = new ModelAndView();
		UserInfo user = userService.getUser((String)session.getAttribute("openid"));
		if(user == null){
			modelAndView.addObject("state", "no");
			modelAndView.setViewName("redirect:/jsp/MyIndex.jsp");
			return modelAndView;
		}else if(user.getZmxyid() == null){
			modelAndView.getModel().put("phone", user.getPhone());
			modelAndView.getModel().put("name", user.getName());
			modelAndView.setViewName("redirect:/jsp/index.jsp");
			return modelAndView;
		}else if(!service.checkReserve((String)session.getAttribute("openid"))){
			modelAndView.setViewName("redirect:/jsp/Warn.jsp");
			return modelAndView;
		}
		else{
			modelAndView.setViewName("redirect:/jsp/DateChoose.jsp");
			return modelAndView;
		}
	}
	
	@RequestMapping(value = "/getDate")
	public void getDate(HttpSession session, HttpServletResponse response){
		log.info(session.getAttribute("openid"));
		List<String> UnavailableDates = getservice.getUnavailableDates((String)session.getAttribute("openid"), 0);
//		List<String> UnavailableDates = getservice.getUnavailableDates("oRTgpwYGzwzbmz3DSAS-Z5WM37Yg", 0);
		JSONObject result=new JSONObject();
		try {
			PrintWriter out = response.getWriter();
			result.put("dates", UnavailableDates);
			out.print(result);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/date")
	public void confirm(String startDate, HttpSession session, HttpServletResponse response) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(startDate);
		boolean flag = service.makeOrder((String)session.getAttribute("openid"), 0, date);
		try {
			PrintWriter out = response.getWriter();
			if(flag){
				//设置订单状态为Step2
				userService.setUserState((String)session.getAttribute("openid"), 2);
				out.print("200");
			}else{
				out.print("500");
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/deviceTrack")
	public void deviceTrack(HttpSession session, HttpServletResponse response){
//		session.setAttribute("openid", "test1");
		JSONObject result=new JSONObject();
		try {
			PrintWriter out = response.getWriter();
			Device temp = getservice.getDeviceByOpenId((String)session.getAttribute("openid"));
			result.put("track", manageService.getRoute(temp.getId()));
			out.print(result);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/deviceAll")
	public void deviceAll(HttpSession session, HttpServletResponse response){
		JSONObject result=new JSONObject();
		try {
			PrintWriter out = response.getWriter();
			result.put("tracks", manageService.getAllRoute());
			out.print(result);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/step5")
	public void toCenter(HttpServletResponse response) throws IOException {
		List<Question> qList = manageService.getRecommend();
		List<Long> qnumList = new ArrayList<Long>();
		if(qList!=null){
			for(Question q:qList){
				qnumList.add(qaservice.getAnswerNum(q.getId()));
			}
		}
		JSONObject result=new JSONObject();
		try {
			PrintWriter out = response.getWriter();
			result.put("qList", qList);
			result.put("qnumList", qnumList);
			out.print(result);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
