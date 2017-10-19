package edu.nju.dao;

import java.util.List;

import edu.nju.entities.Device;
import edu.nju.entities.Order;
import edu.nju.entities.UserInfo;

public interface ReserveDao {

	List<Order> getOrder(String openId);

	UserInfo getBefore(String openId);

	UserInfo getAfter(String openId);

	boolean saveDelInfo(String deviceId,String did);

	String getDelNum(String openId);

	Device reserve(String openId);

	Device priorReserve(String openId);

	boolean makeOrder(String openid, int ifPay, int num);

}
