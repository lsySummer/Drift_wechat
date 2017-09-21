package edu.nju.dao.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.nju.dao.BaseDao;
import edu.nju.dao.zmxyCBDao;
import edu.nju.entities.Test;

@Transactional
@Repository("zmxyCBDao")
public class zmxyCBDaoImpl implements zmxyCBDao{
	
	 @Autowired
	 private BaseDao baseDao;

	public void test() {
		Test t = new Test();
		t.setName("hahaha");
		baseDao.save(t);
		System.out.println("success!");
	}

}