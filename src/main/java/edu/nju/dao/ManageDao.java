package edu.nju.dao;

import java.util.List;

import edu.nju.entities.Device;
import edu.nju.entities.Order;
import edu.nju.entities.UserInfo;

public interface ManageDao {

	List<Device> getDevices();

	List<UserInfo> getUsers();

	boolean addDevice(Device d);

	List<Order> getOrders();

	boolean confirm(String deviceId);

	boolean setArea(String deviceId, String area,int type);

}
