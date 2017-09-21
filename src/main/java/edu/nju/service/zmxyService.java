package edu.nju.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nju.dao.zmxyCBDao;

@Service
public class zmxyService {

	@Autowired
	private zmxyCBDao zmxyCBDao;
	

	public void cb() {
		zmxyCBDao.test();
	}
	
	public void askRequest(){
		
	}

}