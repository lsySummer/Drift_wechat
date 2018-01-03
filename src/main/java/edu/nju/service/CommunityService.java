package edu.nju.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import edu.nju.dao.CommunityDao;
import edu.nju.entities.UserComment;

/**
 * @author lsy
 * 社区相关
 */

@Transactional
@Service
public class CommunityService {
	@Autowired
	CommunityDao dao;
	
	/**
	 * @param num 甲醛含量
	 * @return
	 */
	public boolean addComment(String openid,List<MultipartFile> files,String comment){
		boolean b = dao.addComment(openid,files,comment);
		return b;
	}
	
	public UserComment getComment(String openid){
		UserComment uc = dao.getComment(openid);
		return uc;
	}
	
	public Long getOrderNum() {
		return dao.getOrdernum();
	}
	
	public Long getTodayNum() {
		return dao.getTodayNum();
	}
}
