package edu.nju.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.nju.entities.Device;
import edu.nju.entities.UserInfo;

@Transactional
@Service
public class ManageService {
	//获得所有设备状态信息
	public List<Device> getDevices(){
		return null;
	};

	//获得所有用户信息
	public List<UserInfo> getUsers(){
		return null;
	};

}
