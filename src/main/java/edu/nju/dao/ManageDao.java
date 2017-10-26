package edu.nju.dao;

import java.util.List;

import edu.nju.entities.Device;
import edu.nju.entities.UserInfo;
import edu.nju.model.DeviceVO;
import edu.nju.model.OrderVO;

public interface ManageDao {

	List<DeviceVO> getDevices();

	List<UserInfo> getUsers();

	Device addDevice(Device d);

	List<OrderVO> getOrders();

	boolean confirm(String deviceId);

	boolean setArea(String deviceId, String area,int type);

	void updateArea(String id, String area, int type);

}
