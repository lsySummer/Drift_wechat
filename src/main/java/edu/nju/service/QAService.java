package edu.nju.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import edu.nju.dao.QADao;
import edu.nju.entities.Answer;
import edu.nju.entities.LikeInfo;
import edu.nju.entities.Question;
import edu.nju.utils.Utility;

@Transactional
@Service
public class QAService {
	@Autowired
	QADao dao;
	
	public boolean makeFolder(String path) {
		return Utility.makeFolder(path);
	}
	
	public String addPicture(String filePath,MultipartFile file) {
		List<MultipartFile> list = new ArrayList<MultipartFile>();
		list.add(file);
		String absUrl = (new File("")).getAbsolutePath();
		//String baseUrl = absUrl.substring(0,absUrl.length()-3)+"/webapps/upload/"+filePath;
		String fileUrl = Utility.saveFile(filePath, list);
		String result = "/upload/"+fileUrl;
		return result;
	}
	
	public boolean delPicture(String filePath) {
		return Utility.deleteFile(filePath);
	}
	/**
	 * 把目录的名字由tmp改成img
	 */
	public boolean changenName(String path) {
		return Utility.changenName(path);
	}
	
	/**
	 * 发布问题
	 */
	public boolean publishQuestion(String openid,String title,String content,String picSig) {
		Question q = new Question();
		Date date = new Date();
		q.setOpenid(openid);
		q.setTitle(title);
		q.setContent(content);
		q.setCreateTime(date);
		q.setPicSig(picSig);
		return dao.publishQuestion(q);
	}
	
	/**
	 * 添加回答
	 */
	public boolean addAnswer(String openid,String qid,String content,String picSig) {
		Answer a = new Answer();
		a.setContent(content);
		a.setCreateTime(new Date());
		a.setOpenid(openid);
		a.setQid(qid);
		a.setPicSig(picSig);
//		Utility.saveFile("answers/"+qid+"/"+openid+"/",files);
		return dao.addAnswer(a);
	}
	
	public List<Question> getAllQuestion(){
		List<Question> list = dao.getAllQuestion();
		return list;
	}
	
	public List<Answer> getAnswers(String qid){
		List<Answer> list = dao.getAnswers(qid);
		return list;
	}
	
	/**
	 * 获得某个问题的回答数量
	 */
	public Long getAnswerNum(String qid) {
		Long num = dao.getAnswerNum(qid);
		return num;
	}
	
	
	/**
	 * 点赞
	 */
	public boolean addlike(String answerid,String likeid) {
		LikeInfo like = new LikeInfo();
		like.setAnswerid(answerid);
//		like.setAuthorid(authorid);
		like.setLikeid(likeid);
//		like.setQid(qid);
		like.setCreateTime(new Date());
		return dao.addlike(like);
	}
	
	/**
	 * 取消赞
	 */
	public boolean revokeLike(String qid,String answerid, String authorid,String likeid) {
		return dao.revokeLike(qid,answerid,authorid,likeid);
	}
	
	/**
	 * 根据点赞数排序
	 */
	public Map<Answer,Integer> sortByLikes(String qid){
		Map<Answer,Integer> list = dao.sortByLikes(qid);
		return list;
	}
	
	/**
	 * 根据日期排序
	 */
	public List<Answer> sortByDate(String qid){
		return dao.sortByDate(qid);
	}
	
	/**
	 * 根据answerid获得answer
	 */
	public Answer getByAnswerId(String aid) {
		return dao.getByAnswerId(aid);
	}
	
	public Question getByQuestionId(String qid) {
		return dao.getByQuestionId(qid);
	}
	
	/**
	 * 获得某个答案的点赞数量
	 */
	public Long getLikeNum(String answerid) {
		return dao.getLikeNum(answerid);
	}
	
}
