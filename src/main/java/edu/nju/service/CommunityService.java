package edu.nju.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	public boolean addComment(String openid,String urls,String comment){
		boolean b = dao.addComment(openid,urls,comment);
		return b;
	}
	
	public UserComment getComment(String openid){
		UserComment uc = dao.getComment(openid);
		return uc;
	}
}
