package edu.nju.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.nju.entities.Answer;
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
//		System.out.println(service.reserveDevice("江苏", 1).getId());
//		service.makeOrder("oRTgpweSZbOxfrg9H57JwuPwMJLo", 0, Utility.getSpecifiedDayAfter(new Date(), 6));
//		System.out.println(gservice.getBefore("oRTgpweSZbOxfrg9H57JwuPwMJLo").getName());
//		System.out.println(gservice.getAfter("oRTgpwYGzwzbmz3DSAS-Z5WM37Yg").getName());
//		List<String> str = gservice.getUnavailableDates("oRTgpweSZbOxfrg9H57JwuPwMJLo",0);
//		Device device = service.reserveDevice("oRTgpweSZbOxfrg9H57JwuPwMJLo",0);
//		System.out.println(device.getNumber());
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
//		List<String> list = gservice.getByDeviceId("000000005fa5ec9f015fae298e3b0007");
//		MyThread myThread1 = new MyThread(service);
//		MyThread myThread2 = new MyThread(service);
//		myThread1.start();
//		myThread2.start();
//		service.confirm("oRTgpwQkDZKxGFvNnfKpJLWvxsyw");
//		List<Answer> list = qservice.sortByLikes("000000006112e119016112ea50d20003");
//		for(int i=0;i<list.size();i++) {
//			System.out.println(list.get(i).toString());
//		}
//		System.out.println(qservice.sortByDate("1").get(0).toString()+qservice.sortByDate("1").get(1));
//		qservice.publishQuestion("123", "publish测试", "hahaha");
//		qservice.addAnswer("123", "1", "测试addAnswer");
//		System.out.println(qservice.getAnswerNum("1"));
//		qservice.addlike("1", "1", "1234", "2345");
//		qservice.revokeLike("1","1", "1234","2345");
//		try {
//			String result = qservice.getAnswers("1").get(1).getContent();
//			model.addAttribute("result", result);
//			PrintWriter out = response.getWriter();
//			out.print(result);
//			out.flush();
//			out.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(cservice.getOrderNum());
//		System.out.println(cservice.getTodayNum());
//		System.out.println(gservice.getOrder("oRTgpwYGzwzbmz3DSAS-Z5WM37Yg"));
//		qservice.getAllQuestion();
//		System.out.println(qservice.publishQuestion("oRTgpwQ-wDOO3tGyhnqOM0829ubc", "id可以正常返回吗？", "可以吧", "1"));
//		System.out.println(qservice.checkIfLike("1", "2", "1", "1"));
//		mservice.setRecommend("4028e3ef60f878570160f87ab7f90001");
//		mservice.removeRec("4028e3ef60f878570160f87ab7f90001");
//		System.out.println(mservice.getRecommend().size());
//		System.out.println(cservice.getCheckResult("oRTgpwYGzwzbmz3DSAS-Z5WM37Yg").size());
//		List<Question> list = qservice.getAllQuestion(2, 5);
//		for(Question q:list) {
//			System.out.println(q.toString());
//		}
//		mservice.setRecommend("00000000611cbb3d01611cc3fe0c0002");
//		mservice.setRecommend("00000000611cbb3d01611cddcd9f0005");
//		System.out.println(mservice.getRecommend().size());
		mservice.removeRec("00000000611cbb3d01611cddcd9f0005");
		System.out.println(mservice.getRecommend().size());
		return "jsp/NewFile";
	}
}


