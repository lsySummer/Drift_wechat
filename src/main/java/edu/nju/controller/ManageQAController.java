package edu.nju.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.nju.entities.Question;
import edu.nju.entities.UserInfo;
import edu.nju.service.ManageService;
import edu.nju.service.QAService;
import edu.nju.service.UserService;
import edu.nju.utils.PageUtil;
import edu.nju.utils.HandleFile;

@Controller
@RequestMapping(value="/manage/QA")
public class ManageQAController {
	@Autowired
	QAService qaService;
	@Autowired
	UserService uservice;
	@Autowired
	ManageService mservice;
	@RequestMapping(value = "/questionList")
	public String toAddDevice(Integer page,HttpSession session,Model model) {
		//String check = checkStatus(session);
		//if(check != "true"){return check;}
		System.out.println("获取问题列表");
		List<Question> qList = new ArrayList<Question>();
		List<UserInfo> userList = new ArrayList<UserInfo>();
		List<String> flagList = new ArrayList<String>();
		List<Question> recommendList = new ArrayList<Question>();
		PageUtil pageUtil = new PageUtil(page,qaService.getQuestionNum());
		recommendList = mservice.getRecommend();
		qList = qaService.getAllQuestion(pageUtil.getStart(),pageUtil.getPageSize());
		
		if(qList!=null){
			for(Question question :qList){
				//question.setContent(HandleFile.deleteImg(question.getContent()));
				userList.add(uservice.getUser(question.getOpenid()));
				boolean flag = false;
				for(Question questionRecommend :recommendList){
					if(questionRecommend.getId().equals(question.getId())){
						flag = true;
						System.out.println("有推荐热帖");
					}
				}
				if(flag){
					flagList.add("1");
				}
				else{
					flagList.add("0");
				}
			}
		}
		
		for(String flag :flagList){
			System.out.println(flag);
		}
		model.addAttribute("page", pageUtil);
		model.addAttribute("qList", qList);
		model.addAttribute("userList", userList);
		model.addAttribute("flagList", flagList);
		return "jsp/Manage/QuestionList";
	}
	
	@RequestMapping(value = "/setRecommend")
	public String setRecommend(String qid,HttpSession session,Model model) {
		return "jsp/Manage/QuestionList";
	}
	
	public static String checkStatus(HttpSession session){
		if(session.getAttribute("manage") == null){
			return "jsp/Manage/Login";
		}else{
			return "true";
		}
	}
}
