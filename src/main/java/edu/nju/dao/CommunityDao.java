package edu.nju.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import edu.nju.entities.CheckResult;
import edu.nju.entities.UserComment;

public interface CommunityDao {
	
	public boolean addComment(String openid,List<MultipartFile> files,String comment);
	
	public UserComment getComment(String openid);

	public Long getOrdernum();

	public Long getTodayNum();

}
