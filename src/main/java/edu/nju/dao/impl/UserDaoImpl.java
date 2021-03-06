package edu.nju.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.nju.dao.BaseDao;
import edu.nju.dao.ReserveDao;
import edu.nju.dao.UserDao;
import edu.nju.entities.CheckResult;
import edu.nju.entities.Order;
import edu.nju.entities.UserInfo;
import edu.nju.model.UserVO;


@Repository
public class UserDaoImpl implements UserDao{
	
	private static final Logger log = Logger.getLogger(UserDaoImpl.class);


	 @Autowired
	 private BaseDao baseDao;
//	 @Autowired
//	 private CommunityDao communityDao;
	 @Autowired
	 private ReserveDao rdao;
	
	@Override
	public boolean register(String openId, String nickName) {
		try{
			UserInfo user = new UserInfo();
			user.setOpenid(openId);
			user.setNickName(nickName);
			user.setState(1);
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
			int state = 0;//0代表正在使用，1代表使用过
			if(o.getState().equals("下家已收货")||o.getState().equals("已寄出")){
				state = 1;
			}
			List<CheckResult> checkList = rdao.getCheckResult(u.getOpenid());
			Double jqNum = 0.0;
			if(checkList.size()>0) {
				List<Double> resultList = new ArrayList<Double>();
				for(int j=0;j<checkList.size();j++) {
					resultList.add(checkList.get(j).getNum());
				}
				jqNum = Collections.max(resultList);
			}
			UserVO vo = new UserVO(u.getOpenid(),u.getNickName(),u.getAddress(),o.getStartDate(),
			o.getDeviceNumber(),state,jqNum);
			volist.add(vo);
			}
		}
		for(int i=0;i<volist.size();i++) {
			System.out.println(volist.get(i).toString());
		}
		return volist;
	}

	@Override
	public boolean saveOrUpdate(UserInfo user) {
		List<UserInfo> list = getUserById(user.getOpenid());
		log.info("saveorupdate"+user.getOpenid());
		if(list.size()==0){
			if(!user.getOpenid().equals("")){
				user.setState(1);
				baseDao.save(user);
			}
		}else{
			UserInfo u = list.get(0);
			user.setId(u.getId());
			user.setScore(u.getScore());
			user.setTransactionid(u.getTransactionid());
			user.setZmxyid(u.getZmxyid());
			baseDao.update(user);
		}
		return true;
	}

	@Override
	public boolean setZMXY(String openid, String zmxyid) {
		UserInfo user = getUser(openid);
		user.setZmxyid(zmxyid);
		baseDao.update(user);
		return true;
	}

	@Override
	public boolean setUserState(String openid, int state) {
		UserInfo user = getUser(openid);
		user.setState(state);
		baseDao.update(user);
		return true;
	}

	@Override
	public int getUserState(String openid) {
		UserInfo user = getUser(openid);
		return user.getState();
	}

	@Override
	public boolean bindPhone(String phone, String openid, String nickname, String headimgUrl) {
		
		return false;
	}
}
