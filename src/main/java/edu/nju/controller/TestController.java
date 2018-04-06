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
//		Device d = gservice.getDeviceById("0000000061e60eca0161e62de4810006");
//		List<String> list = new ArrayList<String>();
//		list.add("江苏省");
//		list.add("上海");
//		mservice.addDeviceList(d, list);
		System.out.println(gservice.getBefore("o-Zay1L_qlGS_7IASDo2wDyZ7ts4").getNickName());
		System.out.println(gservice.getBefore("o-Zay1NkOxBTnoRsLofP6I-5BMYM").getNickName());
		System.out.println(gservice.getBefore("o-Zay1F84qAt1aFlXa011eHFPnsE").getNickName());
		System.out.println(gservice.getBefore("o-Zay1I0ZAGOdTmGpY_xCRBfulQ0").getNickName());
		System.out.println(gservice.getBefore("o-Zay1JrTymubxMT5odoOkO1eghM").getNickName());
		System.out.println(gservice.getBefore("o-Zay1HVtb_xP7NoRDLtS_aWDVag").getNickName());
		return "jsp/NewFile";
	}
}


