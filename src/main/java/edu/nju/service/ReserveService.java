package edu.nju.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.nju.dao.ReserveDao;
import edu.nju.entities.DeliveryInfo;
import edu.nju.entities.Device;
import edu.nju.entities.Order;
import edu.nju.entities.UserInfo;

@Transactional
@Service
public class ReserveService {
	@Autowired
	ReserveDao dao;
	
	//获得某个用户的预约信息
	public List<Order> getOrder(String openId){
		List<Order> orders = dao.getOrder(openId);
		return orders;
	};
	//获得上家信息
	public UserInfo getBefore(String openId){
		UserInfo u = dao.getBefore(openId);
		return u;
	};
	//获得下家信息
	public UserInfo getAfter(String openId){
		UserInfo u = dao.getAfter(openId);
		return u;
	};
	//根据快递单号获得快递物流信息  目前看前端写起来更方便
	public DeliveryInfo getDeliveryInfo(String did){
		return null;
	};
	
	/**
	 * @param did 快递单号
	 * @param deviceId Device主键
	 * @return
	 * 保存快递信息
	 */
	public boolean saveDelInfo(String deviceId,String did){
		boolean b = dao.saveDelInfo(deviceId,did);
		return b;
	}
	
	/**
	 * @param deviceId
	 * @return
	 * 根据甲醛仪id获得其快递单号
	 */
	public String getDelNum(String deviceId){
		String num = dao.getDelNum(deviceId);
		return num;
	}
	
	//用户购买耗材
	public boolean paySupply(String openId,int num,int ifPay){
		return true;
	};
	//用户赞助
	public boolean payDonate(String openId){
		return true;
	};
	/**
	 * @param openId 
	 * @return 设备id
	 * 后台分配设备给用户
	 */
	public Device reserve(String openId){
		Device d = dao.reserve(openId);
		return d;
	};
	//后台优先分配设备给付费用户
	public Device priorReserve(String openId){
		Device d = dao.priorReserve(openId);
		return d;
	};
	
	/**
	 * @return
	 * 管理员为用户下订单过程
	 */
	public boolean makeOrder(String openid,int ifPay,int num){
		boolean b = dao.makeOrder(openid,ifPay,num);
		return b;
	}


}
