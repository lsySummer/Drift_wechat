package edu.nju.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.nju.dao.BaseDao;
import edu.nju.dao.UserDao;
import edu.nju.entities.Order;
import edu.nju.entities.UserInfo;
import edu.nju.model.UserVO;


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

	@Override
	public boolean setZMInfo(String openId,String transactionid, int score) {
		List<UserInfo> list = getUserById(openId);
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
	public boolean setAddress(String openid, String zmxyid, String address, String phone, Date startDate, Date endDate,String name,String nickname) {
		UserInfo user = new UserInfo();
		user.setAddress(address);
		user.setOpenid(openid);
		user.setZmxyid(zmxyid);
		user.setPhone(phone);
//		user.setStartDate(startDate);
//		user.setEndDate(endDate);
		user.setNickName(nickname);
		user.setName(name);
		baseDao.save(user);
		return true;
	}

	@Override
	public UserInfo getUser(String openid) {
		List<UserInfo> list = getUserById(openid);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<UserInfo> getUserById(String openId){
		String hql = "from UserInfo where openid =:openid";
		List<UserInfo> list = baseDao.getNewSession().createQuery(hql).setParameter("openid", openId).getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserInfo getByNickName(String nickname) {
		String hql = "from UserInfo where nickname =:nickname";
		List<UserInfo> list = baseDao.getNewSession().createQuery(hql).setParameter("nickname", nickname).getResultList();
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserVO> getUserVO() {
		String hql = "from Order";
		List<Order> list = baseDao.getNewSession().createQuery(hql).getResultList();
		List<UserVO> volist = new ArrayList<UserVO>();
		for(int i=0;i<list.size();i++){
			Order o = list.get(i);
			List<UserInfo> ulist = getUserById(o.getOpenId());
			if(ulist.size()>0){
			UserInfo u = ulist.get(0);
			UserVO vo = new UserVO(u.getOpenid(),u.getNickName(),u.getAddress(), u.getLongtitute(),u.getLatitute(),o.getStartDate(),
			o.getDeviceNumber());
			volist.add(vo);
			}
		}
		return volist;
	}
}
