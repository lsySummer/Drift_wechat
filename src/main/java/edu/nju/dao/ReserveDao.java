package edu.nju.dao;

import java.util.List;

import edu.nju.entities.Device;
import edu.nju.entities.UserInfo;
import edu.nju.model.OrderVO;

public interface ReserveDao {

	List<OrderVO> getOrder(String openId);

	UserInfo getBefore(String openId);

	UserInfo getAfter(String openId);

	boolean saveDelInfo(String openId,String did);

//	String getDelNum(String openId);

	Device reserve(String openId);

	Device priorReserve(String openId);

	boolean makeOrder(String openid, int ifPay, int num);

	Device getDeviceByOpenId(String openId);

}
