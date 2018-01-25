package edu.nju.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.nju.entities.Answer;
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
	@RequestMapping(value = "/allQuestionList")
	public String getAllQuestion(Integer page,HttpSession session,Model model) {
		String check = checkStatus(session);
		if(check != "true"){return check;}
		List<Question> qList = new ArrayList<Question>();
		List<UserInfo> userList = new ArrayList<UserInfo>();
		List<String> flagList = new ArrayList<String>();
		List<Question> recommendList = new ArrayList<Question>();
		PageUtil pageUtil = new PageUtil(page,qaService.getQuestionNum());
		recommendList = mservice.getRecommend();
		qList = qaService.getAllQuestion(pageUtil.getStart(),pageUtil.getPageSize());
		
		if(qList!=null){
			for(Question question :qList){
				userList.add(uservice.getUser(question.getOpenid()));
				boolean flag = false;
				for(Question questionRecommend :recommendList){
					if(questionRecommend.getId().equals(question.getId())){
						flag = true;
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
		
		model.addAttribute("page", pageUtil);
		model.addAttribute("qList", qList);
		model.addAttribute("userList", userList);
		model.addAttribute("flagList", flagList);
		return "jsp/Manage/QuestionList";
	}
	
	@RequestMapping(value = "/recQuestionList")
	public String getRecQuestion(HttpSession session,Model model) {
		String check = checkStatus(session);
		if(check != "true"){return check;}
		String flag = "1";
		List<UserInfo> userList = new ArrayList<UserInfo>();
		List<Question> recommendList = new ArrayList<Question>();
		List<Long> numList =  new ArrayList<Long>();
		PageUtil pageUtil = new PageUtil(1,(long) 10);
		recommendList = mservice.getRecommend();
		if(recommendList!=null){
			for(Question question :recommendList){
				userList.add(uservice.getUser(question.getOpenid()));
				numList.add(qaService.getAnswerNum(question.getId()));
			}
		}
		
		model.addAttribute("qList", recommendList);
		model.addAttribute("userList", userList);
		model.addAttribute("numList", numList);
		model.addAttribute("flag", flag);
		model.addAttribute("page", pageUtil);
		return "jsp/Manage/QuestionList";
	}
	
	@RequestMapping(value = "/questionList")
	public String getQuestion(Integer page,HttpSession session,Model model) {
		String check = checkStatus(session);
		if(check != "true"){return check;}
		String flag = "0";
		List<Question> qList = new ArrayList<Question>();
		List<UserInfo> userList = new ArrayList<UserInfo>();
		List<Long> numList =  new ArrayList<Long>();
		PageUtil pageUtil = new PageUtil(page,qaService.getQuestionNum());
		qList = mservice.getNotRecommend(pageUtil.getStart(),pageUtil.getPageSize());
		if(qList!=null){
			for(Question question :qList){
				userList.add(uservice.getUser(question.getOpenid()));
				numList.add(qaService.getAnswerNum(question.getId()));
			}
		}
		model.addAttribute("page", pageUtil);
		model.addAttribute("qList", qList);
		model.addAttribute("userList", userList);
		model.addAttribute("numList", numList);
		model.addAttribute("flag", flag);
		return "jsp/Manage/QuestionList";
	}
	@RequestMapping(value = "/setRec")
	@ResponseBody
	public String setRecommend(String qid,HttpSession session) {
		if(mservice.setRecommend(qid)){
			return "1";
		}
		else{
			return "0";
		}
	}
	
	@RequestMapping(value = "/getQContent")
	@ResponseBody
	public String getQContent(String qid,HttpSession session) {
		Question question = qaService.getByQuestionId(qid);
		if(question.getContent()!=""){
			return question.getContent();
		}
		else{
			return "此提问作者未添加任何内容";
		}
	}
	
	@RequestMapping(value = "/removeRec")
	@ResponseBody
	public String removeRecommend(String qid,HttpSession session) {
		if(mservice.removeRec(qid)){
			return "1";
		}
		else{
			return "0";
		}
	}
	
	@RequestMapping(value = "/answerList")
	public String answerList(String qid,String page,HttpSession session,Model model){
		String check = checkStatus(session);
		if(check != "true"){return check;}
		List<Answer> aList = new ArrayList<Answer>();
		List<Long> likeList= new ArrayList<Long>();
		List<UserInfo> userList= new ArrayList<UserInfo>();
 		PageUtil pageUtil = new PageUtil(Integer.parseInt(page),qaService.getAnswerNum(qid));
		aList  = qaService.getAnswers(qid, pageUtil.getStart(), pageUtil.getPageSize());
		String flag = "0";
		if(aList!=null){
			flag = "1";
			for(Answer answer :aList){
				userList.add(uservice.getUser(answer.getOpenid()));
				likeList.add(qaService.getLikeNum(answer.getId()));
			}
		}
		model.addAttribute("aList", aList);
		model.addAttribute("likeList", likeList);
		model.addAttribute("userList", userList);
		model.addAttribute("page", pageUtil);
		model.addAttribute("flag", flag);
		return "jsp/Manage/AnswerList";
	}
	
	@RequestMapping(value = "/getAContent")
	@ResponseBody
	public String getAContent(String aid,HttpSession session) {
		Answer answer = qaService.getByAnswerId(aid);
		if(answer.getContent()!=""){
			return answer.getContent();
		}
		else{
			return "此提问作者未添加任何内容";
		}
	}
	
	public static String checkStatus(HttpSession session){
		if(session.getAttribute("manage") == null){
			return "jsp/Manage/Login";
		}else{
			return "true";
		}
	}
}
