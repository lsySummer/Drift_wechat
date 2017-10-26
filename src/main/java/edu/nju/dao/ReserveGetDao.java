package edu.nju.dao;

import java.util.Date;
import java.util.List;

import edu.nju.entities.Device;
import edu.nju.entities.Order;
import edu.nju.entities.UserInfo;
import edu.nju.model.OrderVO;

public interface ReserveGetDao {

	List<OrderVO> getOrder(String openId);

	UserInfo getBefore(String openId);

	UserInfo getAfter(String openId);
	
	Device getDeviceByOpenId(String openId);

	String getSendDid(String openid);

	String getRecDid(String openid);
	
	List<Order> getOrderById(String openId);

	List<String> getByDeviceId(String deviceId);

}
