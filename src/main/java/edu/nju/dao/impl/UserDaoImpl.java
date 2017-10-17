package edu.nju.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.nju.dao.BaseDao;
import edu.nju.dao.UserDao;
import edu.nju.entities.UserInfo;


@Repository
public class UserDaoImpl implements UserDao{
	
	private static final Logger log = Logger.getLogger(UserDaoImpl.class);


	 @Autowired
	 private BaseDao baseDao;
	
	@Override
	public boolean register(String openId, String nickName) {
		try{
			UserInfo user = new UserInfo();
			user.setOpenid(openId);
			user.setNickName(nickName);
			baseDao.save(user);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean setZMInfo(String openId,String transactionid, int score) {
		String hql = "from UserInfo where openid =:openid";
		List<UserInfo> list = baseDao.getNewSession().createQuery(hql).setParameter("openid", openId).getResultList();
		if(list.size()>0){
			UserInfo user = list.get(0);
			user.setScore(score);
			user.setTransactionid(transactionid);
			baseDao.save(user);
			return true;
		}
		return false;
	}

	@Override
	public boolean setAddress(String openid, String zmxyid, String address, String phone, Date startDate, Date endDate,String name) {
		UserInfo user = new UserInfo();
		user.setAddress(address);
		user.setOpenid(openid);
		user.setZmxyid(zmxyid);
		user.setPhone(phone);
//		user.setStartDate(startDate);
//		user.setEndDate(endDate);
		user.setName(name);
		baseDao.save(user);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserInfo getUser(String openid) {
		String hql = "from UserInfo where openid =:openid";
		List<UserInfo> list = baseDao.getNewSession().createQuery(hql).setParameter("openid", openid).getResultList();
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
