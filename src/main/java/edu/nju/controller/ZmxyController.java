package edu.nju.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import edu.nju.service.UserService;
import edu.nju.service.ZmxyService;

@Controller
@RequestMapping("/zmxy")
public class ZmxyController {

	@Autowired
	private ZmxyService zmxyService;
	
	@Autowired
	private UserService userService;
	
	private Logger log = Logger.getLogger(ZmxyController.class);
	
	@ResponseBody
	@RequestMapping(value = "/cb", method = RequestMethod.GET)
	public ModelAndView callback(@RequestParam("params") String params,@RequestParam("sign") String sign, HttpSession session)
	{
		log.info("params:" + params);
		log.info("sign:" + params);
		String state = zmxyService.cb(params,sign);
		ModelAndView modelAndView = new ModelAndView();
		log.info("state:" + state);
		if(state.equals("failed")){
			modelAndView.addObject("state", state);
			modelAndView.setViewName("redirect:/jsp/Warn.jsp");
			return modelAndView;
		}else{
			session.setAttribute("zmxyid", state);
//			modelAndView.addObject("state", state);
			userService.setZMXY((String)session.getAttribute("openid"), state);
			modelAndView.setViewName("redirect:/jsp/DateChoose.jsp");
			return modelAndView;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView ask(@RequestParam("name") String name,@RequestParam("phone") String phone, HttpSession session)
	{
		session.setAttribute("name", name);
		session.setAttribute("phone", phone);
		return new ModelAndView(new RedirectView(zmxyService.askRequest(name,phone)));
	}
}