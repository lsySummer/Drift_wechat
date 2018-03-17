package edu.nju.dao;

import java.util.Date;
import java.util.List;

import edu.nju.entities.UserInfo;
import edu.nju.model.UserVO;

public interface UserDao {

	boolean register(String openId, String nickName);

	boolean setZMInfo(String openId,String transactionid, int score);

	boolean setAddress(String openid, String zmxyid, String address, String phone, Date startDate, Date endDate,String name,String nickname);

	UserInfo getUser(String openid);
	
	List<UserInfo> getUserById(String openId);

	UserInfo getByNickName(String nickname);

	List<UserVO> getUserVO();

	boolean saveOrUpdate(UserInfo user);

	boolean setZMXY(String openid, String zmxyid);

	boolean setUserState(String openid, int state);

	int getUserState(String openid);

	boolean bindPhone(String phone, String openid, String nickname, String headimgUrl);

}
