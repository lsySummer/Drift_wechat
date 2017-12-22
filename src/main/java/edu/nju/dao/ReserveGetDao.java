package edu.nju.dao;

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
	
	List<Device> getDeviceById(String deviceId);
	/*
	 *新增接口，根据orderId得到此order
	 */
	Order getOrderByorderId(String oderId);
	
	List<Order> getOrdersByDeviceId(String deviceId);

	public List<String> getAreaByDid(String did);
	
	List<Order> getOrders();
	
	Order getByOpenAndDid(String openid,String deviceid);

	List<String> getUnavailableDates(String deviceId);

	List<Device> getAllDevice();
}
