package edu.nju.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.nju.entities.DeliveryInfo;
import edu.nju.entities.Order;
import edu.nju.entities.UserInfo;

@Transactional
@Service
public class ReserveService {
	//获得某个用户的预约信息
	public Order getOrder(String openId){
		return null;
	};
	//获得上家信息
	public UserInfo getBefore(String openId){
		return null;
	};
	//获得下家信息
	public UserInfo getAfter(String openId){
		return null;
	};
	//根据快递单号获得快递物流信息
	public DeliveryInfo getDeliveryInfo(String did){
		return null;
	};
	//用户购买耗材
	public boolean paySupply(String openId,int num){
		return true;
	};
	//用户赞助
	public boolean payDonate(String openId){
		return true;
	};
	//后台分配设备给用户
	public boolean reserve(String openId){
		return true;
	};
	//后台优先分配设备给付费用户
	public boolean priorReserve(String openId){
		return true;
	};


}
