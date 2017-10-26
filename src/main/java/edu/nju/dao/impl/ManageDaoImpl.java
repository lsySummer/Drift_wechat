package edu.nju.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.nju.dao.BaseDao;
import edu.nju.dao.ManageDao;
import edu.nju.entities.Device;
import edu.nju.entities.DeviceArea;
import edu.nju.entities.Order;
import edu.nju.entities.UserInfo;

@Repository
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

	@Override
	public boolean addDevice(Device d) {
		try{
			baseDao.save(d);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getOrders() {
		String hql = "from Orders";
		List<Order> list = baseDao.getNewSession().createQuery(hql).getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean confirm(String deviceId) {
		String hql = "from Device where id = :deviceId";
		List<Device> list = baseDao.getNewSession().createQuery(hql).setParameter("deviceId", deviceId).getResultList();
		if(list.size()>0){
			Device d = list.get(0);
			d.setLoc("company");
			return true;
		}
		return false;
	}

	@Override
	public boolean setArea(String deviceId, String area,int type) {
		DeviceArea da = new DeviceArea();
		da.setArea(area);
		da.setDeviceId(deviceId);
		da.setType(type);
		try{
			baseDao.save(da);
			return true;
		}catch(Exception e){
			return false;
		}
	}

}
