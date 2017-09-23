package edu.nju.dao;

import java.util.Date;

import edu.nju.entities.UserInfo;

public interface UserDao {

	boolean register(String openId, String nickName);

	boolean setZMInfo(String openId,String transactionid, int score);

	boolean setAddress(String openid, String zmxyid, String address, String phone, Date startDate, Date endDate,String name);

	UserInfo getUser(String openid);


}
