package edu.nju.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.nju.service.CommunityService;
import edu.nju.service.ManageService;
import edu.nju.service.ReserveGetService;
import edu.nju.service.ReserveService;
import edu.nju.service.UserService;
import edu.nju.utils.Utility;

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
//		System.out.println(service.reserveDevice("江苏省", 0).getId());
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
//		Device device = service.reserveDevice("oRTgpweSZbOxfrg9H57JwuPwMJLo",0);
//		System.out.println(device.getNumber());
//		Device d = new Device();
//		Device d = gservice.getDeviceById("40288d815f599f39015f599f59880000");
//		List<String> list = new ArrayList<String>();
//		list.add("广东省");
//		list.add("湖南省");
//		mservice.addDeviceList(d, list, 0);
//		System.out.println(mservice.getDevices().get(0).toString());
//		System.out.println(mservice.getOrders().get(0).toString());
//		cservice.addComment("oRTgpwQkDZKxGFvNnfKpJLWvxsyw", "/usr/local/123.jpg;/usr/234.jpg", "不错很好哈哈哈");
//		System.out.println("写入成功");
//		System.out.println(cservice.getComment("oRTgpwQkDZKxGFvNnfKpJLWvxsyw").toString());
//		System.out.println(service.getCompanyReceive().size());
//		service.saveDelInfo("oRTgpwQkDZKxGFvNnfKpJLWvxsyw", "166681986110");
//		System.out.println(mservice.getRoute("000000005fa5ec9f015fae298e3b0007").size());
//		System.out.println(mservice.getAllRoute().size());
//		System.out.println(new Date().toString());
//		service.makeOrder("000000005fa5ec9f015fae298e3b0007", "oRTgpwQkDZKxGFvNnfKpJLWvxsyw", 0, Utility.getSpecifiedDayAfter(new Date(),3));
		//		List<String> str = gservice.getByDeviceId("000000005fa5ec9f015fae298e3b0007");
//		MyThread myThread1 = new MyThread(service);
//		MyThread myThread2 = new MyThread(service);
//		myThread1.start();
//		myThread2.start();
		service.confirm("oRTgpwQkDZKxGFvNnfKpJLWvxsyw");
		return "success";
	}
}


