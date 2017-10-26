package edu.nju.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.nju.entities.Device;
import edu.nju.service.ManageService;
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
	
	@RequestMapping(value = "/insert")
	public String register() {
//		for(int i=0;i<10;i++){
//			Device d = new Device();
//			d.setNumber("甲醛仪"+i+"号");
//			d.setLoc("company");
//			d.setQueueNum(0);
//			mservice.addDevice(d);
//		}
//		String[] areas={"江苏","浙江","广东","北京","上海","黑龙江","山东","湖南","湖北","福建"};
//		List<Device> dlist = mservice.getDevices();
//		for(int i=0;i<dlist.size();i++){
//		mservice.setArea(dlist.get(i).getId(), areas[i], 0);
//		}
//		System.out.println(service.reserveDevice("江苏", 0).getId());
//		System.out.println(service.reserveDevice("江苏", 1).getId());
//		service.makeOrder("34567", "江苏", 0, Utility.getSpecifiedDayAfter(new Date(), 6), Utility.getSpecifiedDayAfter(new Date(), 7));
//		service.makeOrder("45678", "江苏", 0, Utility.getSpecifiedDayAfter(new Date(), 8), Utility.getSpecifiedDayAfter(new Date(), 9));
//		System.out.println(gservice.getBefore("23456").getName());
//		System.out.println(gservice.getAfter("12345").getName());
//		System.out.println(gservice.getAfter("23456").getName());
//		System.out.println(gservice.getBefore("12345").getName());
//		List<String> str = gservice.getByDeviceId("40288d815f5787ba015f5787ebb90010");
//		for(int i = 0;i<str.size();i++){
//			System.out.println(str.get(i));
//		}
		Device device = service.reserveDevice("oRTgpweSZbOxfrg9H57JwuPwMJLo",0);
		System.out.println(device.getNumber());
//		mservice.addDevice(device, "北京市", 0);
//		System.out.println(mservice.getDevices().get(0).toString());
//		System.out.println(mservice.getOrders().get(0).toString());
		return "success";
	}
}
