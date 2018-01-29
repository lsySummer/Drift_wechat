package edu.nju.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	
	@RequestMapping(value = "/deleteQuestion")
	@ResponseBody
	public String deleteQuestion(String qid,HttpSession session) {
		if(mservice.deleteQuestion(qid)){
			return "1";
		}
		else{
			return "0";
		}
	}
	
	@RequestMapping(value = "/deleteAnswer")
	@ResponseBody
	public String deleteAnswer(String aid,HttpSession session) {
		System.out.println(aid);
		if(mservice.deleteAnswer(aid)){
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
		System.out.println(page);
		System.out.println(qaService.getAnswerNum(qid));
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
		model.addAttribute("qid", qid);
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
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Question")
	public void ask(HttpSession session, @RequestParam(value = "file") MultipartFile file, HttpServletResponse response){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH:mm");
		String filePath = "";
		if(session.getAttribute("question") == null){
			String openid = "company";
			filePath = openid + "_" + df.format(new Date()) + "/";
			qaService.makeFolder(filePath);
			session.setAttribute("question", filePath);
			List<MultipartFile> temp = new ArrayList<MultipartFile>();
			session.setAttribute("qfile", temp);
		}else{
			filePath = (String)session.getAttribute("question");
		}
		List<MultipartFile> photoLists = (List<MultipartFile>) session.getAttribute("qfile");
		photoLists.add(file);
		try {
			PrintWriter out = response.getWriter();
			out.print("200");
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/ConfirmQuestion")
	public String ConfirmAsk(HttpSession session, String title, String summernote, HttpServletResponse response){
		String check = checkStatus(session);
		if(check != "true"){return check;}
		String qid = "";
		if(session.getAttribute("manage") != null){
			if(session.getAttribute("question") != null){
				List<MultipartFile> photoLists = (List<MultipartFile>)session.getAttribute("qfile");
				String filepath = (String)session.getAttribute("question");
				qid = qaService.publishQuestion("thisiscomponyinfomation", title, summernote, photoLists, filepath);
				session.removeAttribute("question");
				session.removeAttribute("qfile");
			}else{
				qid = qaService.publishQuestion("thisiscomponyinfomation", title, summernote, new ArrayList<MultipartFile>(), "");
			}
		}
		return "redirect:questionList?page=1";
	}
	
	@RequestMapping("/CancelQuestion")
	public String CancelQuestion(HttpSession session){
		String check = checkStatus(session);
		if(check != "true"){return check;}
		if(session.getAttribute("question") != null){
			session.removeAttribute("question");
			session.removeAttribute("qfile");
		}
		return "redirect:questionList?page=1";
	}
}
