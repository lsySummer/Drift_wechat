package edu.nju.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.nju.dao.QADao;
import edu.nju.entities.Answer;
import edu.nju.entities.LikeInfo;
import edu.nju.entities.Question;

@Transactional
@Service
public class QAService {
	@Autowired
	QADao dao;
	
	/**
	 * 发布问题
	 */
	public boolean publishQuestion(String openid,String title,String content) {
		Question q = new Question();
		Date date = new Date();
		q.setOpenid(openid);
		q.setTitle(title);
		q.setContent(content);
		q.setCreateTime(date);
		return dao.publishQuestion(q);
	}
	
	/**
	 * 添加回答
	 */
	public boolean addAnswer(String openid,String qid,String content) {
		Answer a = new Answer();
		a.setContent(content);
		a.setCreateTime(new Date());
		a.setOpenid(openid);
		a.setQid(qid);
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
	public int getAnswerNum(String qid) {
		int num = dao.getAnswerNum(qid);
		return num;
	}
	
	
	/**
	 * 点赞
	 */
	public boolean addlike(String answerid,String openid) {
		LikeInfo like = new LikeInfo();
		like.setAnswerid(answerid);
		like.setOpenid(openid);
		like.setCreateTime(new Date());
		return dao.addlike(like);
	}
	
	/**
	 * 取消赞
	 */
	public boolean revokeLike(String answerid,String openid) {
		return dao.revokeLike(answerid,openid);
	}
	
	/**
	 * 根据点赞数排序
	 */
	public List<Answer> sortByLikes(String pid){
		List<Answer> list = dao.sortByLikes(pid);
		return list;
	}
	
	/**
	 * 获得某个问题最多赞的答案
	 */
	public Answer getMostLike(String pid) {
		Answer a = dao.getMostLike(pid);
		return a;
	}
}
