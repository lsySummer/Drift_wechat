package edu.nju.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.nju.entities.Question;
import edu.nju.service.QAService;
import edu.nju.utils.PageUtil;


@RequestMapping(value="/manage/QA")
public class ManageQAController {
	@Autowired
	QAService qaService;
	@RequestMapping(value = "/questionList")
	public String toAddDevice(Integer page,HttpSession session,Model model) {
		String check = checkStatus(session);
		if(check != "true"){return check;}
		List<Question> qList = new ArrayList<Question>();
		PageUtil pageUtil = new PageUtil(page,100);//100需要更改为真正的数量
		model.addAttribute("provinces", qList);
		return "jsp/Manage/Login";
	}
	
	public static String checkStatus(HttpSession session){
		if(session.getAttribute("manage") == null){
			return "jsp/Manage/Login";
		}else{
			return "true";
		}
	}
}
