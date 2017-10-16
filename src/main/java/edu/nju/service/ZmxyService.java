package edu.nju.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nju.dao.ZmxyAskDao;
import edu.nju.dao.ZmxyCBDao;

@Service
public class ZmxyService {

	@Autowired
	private ZmxyCBDao zmxyCBDao;
	
	@Autowired
	private ZmxyAskDao zmxyAskDao;

	public String cb(String params,String sign) {
		return zmxyCBDao.verity(params,sign);
	}
	
	public String askRequest(String name,String phone){
		return zmxyAskDao.AuthInfo(name,phone);
	}
}