package edu.nju.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import edu.nju.service.ZmxyService;

@Controller
@RequestMapping("/zmxy")
public class ZmxyController {

	@Autowired
	private ZmxyService zmxyService;
	private Logger log = Logger.getLogger(ZmxyController.class);
	
	@ResponseBody
	@RequestMapping(value = "/cb", method = RequestMethod.GET)
	public ModelAndView callback(@RequestParam("params") String params,@RequestParam("sign") String sign)
	{
		log.info("params:" + params);
		log.info("sign:" + params);
		String state = zmxyService.cb(params,sign);
		ModelAndView modelAndView = new ModelAndView();
		log.info("state:" + state);
		if(state != "failed"){
			modelAndView.addObject("state", state);
			modelAndView.setViewName("redirect:/jsp/Detail_Write.jsp");
			return modelAndView;
		}else{
			modelAndView.addObject("state", state);
			modelAndView.setViewName("redirect:/jsp/index.jsp");
			return modelAndView;
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView ask(@RequestParam("name") String name,@RequestParam("phone") String phone)
	{
		return new ModelAndView(new RedirectView(zmxyService.askRequest(name,phone)));
	}
}