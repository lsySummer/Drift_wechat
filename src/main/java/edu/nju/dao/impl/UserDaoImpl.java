package edu.nju.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.nju.dao.UserDao;


@Repository
public class UserDaoImpl implements UserDao{
	
	private static final Logger log = Logger.getLogger(UserDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public boolean register(String openId, String nickName) {
		try{
			String sql= "insert ignore into user(openId, nickName) values('" + openId + "','"+nickName+"')";
			getSession().createSQLQuery(sql).executeUpdate();//注意,插入要加上executeUpdate,否则插入不成功  
			return true;
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			return false;
		}
	}

}
