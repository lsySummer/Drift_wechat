package edu.nju.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.nju.entities.Device;
import edu.nju.service.CommunityService;
import edu.nju.service.ManageService;
import edu.nju.service.QAService;
import edu.nju.service.ReserveGetService;
import edu.nju.service.ReserveService;
import edu.nju.service.UserService;

@Controller
@RequestMapping(value="/test")
public class TestController {
	@Autowired
	ReserveService service;
	@Autowired
	UserService userService;
	@Autowired
	ManageService mservice;
	@Autowired
	ReserveGetService gservice;
	@Autowired
	CommunityService cservice;
	@Autowired
	QAService qservice;
	
	@RequestMapping(value = "/insert")
	public String register() {
		mservice.deleteDevice("4028e3e86154866b0161548682b30000");
		return "jsp/NewFile";
	}
}


