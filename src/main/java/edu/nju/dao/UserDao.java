package edu.nju.dao;

import java.util.Date;

import edu.nju.entities.UserInfo;

public interface UserDao {

	boolean register(String openId, String nickName);

	boolean setZMInfo(String openId,String transactionid, int score);

	boolean setAddress(String openid,String address, String phone, Date startDate, Date endDate);

	UserInfo getUser(String openid);


}
