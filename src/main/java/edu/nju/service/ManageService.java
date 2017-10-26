package edu.nju.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.nju.dao.ManageDao;
import edu.nju.entities.Device;
import edu.nju.entities.Order;
import edu.nju.entities.UserInfo;

@Transactional
@Service
public class ManageService {
	
	@Autowired
	ManageDao manageDao;
	
	//获得所有设备状态信息
	public List<Device> getDevices(){
		List<Device> dlist = manageDao.getDevices();
		return dlist;
	};

	//获得所有用户信息
	public List<UserInfo> getUsers(){
		List<UserInfo> ulist = manageDao.getUsers();
		return ulist;
	};
	
	//添加设备
	public boolean addDevice(Device d){
		return manageDao.addDevice(d);
	}
	
	public List<Order> getOrders(){
		List<Order> olist = manageDao.getOrders();
		return olist;
	}
	
	//确认收货
	public boolean confirm(String deviceId){
		return manageDao.confirm(deviceId);
	}

	public boolean setArea(String deviceId,String area,int type){
		return manageDao.setArea(deviceId,area,type);
	}
}
