package edu.nju.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import edu.nju.dao.BaseDao;
import edu.nju.dao.CommunityDao;
import edu.nju.entities.Order;
import edu.nju.entities.UserComment;
import edu.nju.utils.Utility;

@Repository
public class CommunityDaoImpl implements CommunityDao{
//	private static final Logger log = Logger.getLogger(CommunityDaoImpl.class);
	 @Autowired
	 private BaseDao baseDao;

	@SuppressWarnings("unchecked")
	@Override
	public boolean addComment(String openid,List<MultipartFile> files,String comment,float num) {
		String hql = "from Order where openId =:openid";
		List<Order> list = baseDao.getNewSession().createQuery(hql).setParameter("openid", openid).getResultList();
		if(list.size()>0){
			Order o = list.get(list.size()-1);
			UserComment u = new UserComment();
			u.setComment(comment);
			u.setOpenid(openid);
			u.setOrderid(o.getId());
			u.setNum(num);
			String urls = Utility.saveFile("comment/"+openid+"/",files);
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
