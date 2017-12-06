package edu.nju.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import edu.nju.entities.UserComment;

public interface CommunityDao {
	
	public boolean addComment(String openid,List<MultipartFile> files,String comment);
	
	public UserComment getComment(String openid);
}
