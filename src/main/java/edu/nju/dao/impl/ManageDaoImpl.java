package edu.nju.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.dao.BaseDao;
import edu.nju.dao.ManageDao;
import edu.nju.entities.Device;
import edu.nju.entities.UserInfo;

public class ManageDaoImpl implements ManageDao{
	
	 @Autowired
	 private BaseDao baseDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<Device> getDevices() {
		String hql = "from Device";
		List<Device> list = baseDao.getNewSession().createQuery(hql).getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfo> getUsers() {
		String hql = "from UserInfo";
		List<UserInfo> list = baseDao.getNewSession().createQuery(hql).getResultList();
		return list;
	}

}
