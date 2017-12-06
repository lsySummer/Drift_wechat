package edu.nju.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.nju.dao.BaseDao;
import edu.nju.dao.CommunityDao;
import edu.nju.entities.Order;
import edu.nju.entities.UserComment;

@Repository
public class CommunityDaoImpl implements CommunityDao{
	 @Autowired
	 private BaseDao baseDao;

	@SuppressWarnings("unchecked")
	@Override
	public boolean addComment(String openid,String urls,String comment) {
		String hql = "from Order where openId =:openid";
		List<Order> list = baseDao.getNewSession().createQuery(hql).setParameter("openid", openid).getResultList();
		if(list.size()>0){
			Order o = list.get(list.size()-1);
			UserComment u = new UserComment();
			u.setComment(comment);
			u.setOpenid(openid);
			u.setOrderid(o.getId());
			u.setPicURLS(urls);
			baseDao.save(u);
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserComment getComment(String openid) {
		String hql = "from UserComment where openid =:openid";
		List<UserComment> list = baseDao.getNewSession().createQuery(hql).setParameter("openid", openid).getResultList();
		if(list.size()>0){
			UserComment uc = list.get(list.size()-1);
			return uc;
		}
		return null;
	}

}
