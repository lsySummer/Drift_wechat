package edu.nju.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nju.dao.TestDao;

@Service
public class TestService {

	@Autowired
	private TestDao testDao;

	public void test() {
		testDao.test();
	}
	
	

}