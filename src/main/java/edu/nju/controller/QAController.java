package edu.nju.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import edu.nju.entities.Answer;
import edu.nju.entities.Question;
import edu.nju.entities.UserInfo;
import edu.nju.service.QAService;
import edu.nju.service.UserService;

@Controller
@RequestMapping("/QA")
public class QAController {
	@Autowired
	QAService qaservice;
	@Autowired
	UserService uservice;
	
	private Logger log = Logger.getLogger(UserController.class);
	
	@RequestMapping("/Index")
	public String getQList(HttpSession session,Model model){
		List<Question> qList = (List)qaservice.getAllQuestion();
		List<Long> qnumList = new ArrayList();
		for(Question question :qList){
			qnumList.add(qaservice.getAnswerNum(question.getId()));
		}
		model.addAttribute("qList", qList);
		model.addAttribute("qnumList", qnumList);
		return "jsp/community/CommunityIndex";
	}
	
	@RequestMapping("/DateSort")
	public String getQ2AList(String qid, HttpSession session,Model model){
		List<Answer> aList = (List)qaservice.sortByDate(qid);
		return packageData(aList,qid,model);
	}
	
	@RequestMapping("/LikeSort")
	public String getQ2ALikeList(String qid,Model model){
		List<Answer> aList = new ArrayList<Answer>();
		Map<Answer,Integer> map = new HashMap<Answer,Integer>();  		  
		for (Answer key : map.keySet()) {  
			aList.add(key);  
		}  
		return packageData(aList,qid,model);
	}
	
	
	@RequestMapping("/AddLike")
	public String addLike(HttpSession session,String aid,Model model){
		//String openid = (String) session.getAttribute("openid");
		String openid = "oRTgpwYGzwzbmz3DSAS-Z5WM37Yg";
		if(qaservice.addlike(aid, openid))
			return "1";
		else
			return "0";
	}
	
	@RequestMapping("/RemoveLike")
	public String cancellLike(HttpSession session,String aid,String qid,Model model){
		//String openid = (String) session.getAttribute("openid");
		String openid = "oRTgpwYGzwzbmz3DSAS-Z5WM37Yg";
		String authorid = qaservice.getByAnswerId(aid).getOpenid();
		if(qaservice.revokeLike(qid, aid, authorid,openid))
			return "1";
		else
			return "0";
	}
	
	public  String packageData(List<Answer> aList,String qid,Model model){
		Question question =  qaservice.getByQuestionId(qid);
		List<UserInfo> userList = new ArrayList<UserInfo>();
		List<String> dateStrs = new ArrayList<String>();
		List<Long> likeList = new ArrayList<Long>();
		for(Answer answer :aList){
			userList.add(uservice.getUser(answer.getOpenid()));
			dateStrs.add(convertDate(answer.getCreateTime()));
			likeList.add(qaservice.getLikeNum(answer.getId()));
		}
		model.addAttribute("anum",qaservice.getAnswerNum(qid));
		model.addAttribute("aList", aList);
		model.addAttribute("question", question);
		model.addAttribute("userList", userList);
		model.addAttribute("dateStrs", dateStrs);
		model.addAttribute("likeList", likeList);
		return "jsp/community/QuestionAnswer";
	}
	
	public String convertDate(Date createTime){
		String dateStr ="";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		long l=now.getTime()-createTime.getTime();
		long day=l/(24*60*60*1000);
		long hour=(l/(60*60*1000)-day*24);
		long min=((l/(60*1000))-day*24*60-hour*60);
		long s=(l/1000-day*24*60*60-hour*60*60-min*60);
		if(day!=0){
			dateStr =day+"天前";
			return dateStr;
		}
		else if(hour!=0){
			dateStr =hour+"小时前";
			return dateStr;
		}
		else if(min==0){
			dateStr =min+"分钟前";
			return dateStr;
		}
		else {
			dateStr =s+"秒前";
			return dateStr;
		}
	}
	
	@RequestMapping("/Question")
	public void ask(HttpSession session, @RequestParam(value = "file") MultipartFile file, HttpServletResponse response){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String filePath = "";
		if(session.getAttribute("question") == null){
			filePath = (String)session.getAttribute("openid") + "_" + df.format(new Date()) + "/temp/";
//			filePath = "test" + "_" + df.format(new Date()) + "/temp/";
			qaservice.makeFolder(filePath);
			session.setAttribute("question", filePath);
		}else{
			filePath = (String)session.getAttribute("question");
		}
		try {
			PrintWriter out = response.getWriter();
			String filename = qaservice.addPicture(filePath, file);
			session.setAttribute("qfilename", filename);
			out.print(filename);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/ConfirmQuestion")
	public String ConfirmAsk(HttpSession session, String title, String summernote, HttpServletResponse response){
		String picSig = "";
		if(session.getAttribute("question") != null){
			picSig = (String)session.getAttribute("qfilename");
			qaservice.changenName(picSig);
			session.removeAttribute("question");
			session.removeAttribute("qfilename");
		}
		//String questionid = qaservice.publishQuestion((String)session.getAttribute("openid"), title, summernote, picSig);
		String questionid = qaservice.publishQuestion("oRTgpwYGzwzbmz3DSAS-Z5WM37Yg", title, summernote, picSig);
		
		return "redirect:QuestionPreview?qid=" + questionid;
		//return questionPreview(questionid,model);
	}
	
	@RequestMapping("/QuestionPreview")
	public String questionPreview(String qid,Model model){
		Question question  = qaservice.getByQuestionId(qid);
		Long answerNum = qaservice.getAnswerNum(qid);
		model.addAttribute("question", question);
		model.addAttribute("answerNum", answerNum);
		return "jsp/community/QuestionPreview";
	}
	
	@RequestMapping("/Answer")
	public void Answer(HttpSession session, @RequestParam(value = "file") MultipartFile file, HttpServletResponse response){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String filePath = "";
		if(session.getAttribute("answer") == null){
			filePath = (String)session.getAttribute("openid") + "_" + df.format(new Date()) + "/temp/";
//			filePath = "test" + "_" + df.format(new Date()) + "/temp/";
			qaservice.makeFolder(filePath);
			session.setAttribute("answer", filePath);
		}else{
			filePath = (String)session.getAttribute("answer");
		}
		try {
			PrintWriter out = response.getWriter();
			String filename = qaservice.addPicture(filePath, file);
			session.setAttribute("afilename", filename);
			out.print(filename);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/ConfirmAnswer")
	public String ConfirmAnswer(HttpSession session, String qid, String summernote,Model model){
		String picSig = "";
		if(session.getAttribute("answer") != null){
			picSig = (String)session.getAttribute("afilename");
			qaservice.changenName(picSig);
			session.removeAttribute("answer");
			session.removeAttribute("afilename");
		}
		//String aid = qaservice.addAnswer((String)session.getAttribute("openid"), qid, summernote, picSig);
		String aid = qaservice.addAnswer("oRTgpwYGzwzbmz3DSAS-Z5WM37Yg", qid, summernote, picSig);
		return "redirect:AnswerPreview?qid=" + qid + "&aid=" + aid;
		//return toAnswerPreview(aid,qid,model);
	}
	
	@RequestMapping("/AnswerPreview")
	public String answerPreview(String aid,String qid,Model model){
		Question question = qaservice.getByQuestionId(qid);
		Answer answer = qaservice.getByAnswerId(aid);
		Long likeNum = qaservice.getLikeNum(aid);
		UserInfo user = uservice.getUser(answer.getOpenid());
		model.addAttribute("answer", answer);
		model.addAttribute("likeNum", likeNum);
		model.addAttribute("user", user);
		model.addAttribute("question",question);
		return "jsp/community/AnswerPreview";
	}
}
