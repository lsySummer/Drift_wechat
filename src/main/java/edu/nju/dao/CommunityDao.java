package edu.nju.dao;

import edu.nju.entities.UserComment;

public interface CommunityDao {
	
	public boolean addComment(String openid,String urls,String comment);
	
	public UserComment getComment(String openid);
}
