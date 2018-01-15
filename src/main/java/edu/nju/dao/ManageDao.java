package edu.nju.dao;

import java.util.List;
import java.util.Map;

import edu.nju.entities.Device;
import edu.nju.entities.UserInfo;
import edu.nju.model.DeviceVO;
import edu.nju.model.OrderVO;
import edu.nju.model.RouteVO;

public interface ManageDao {

	List<DeviceVO> getDevices();

	List<UserInfo> getUsers();

	Device addDevice(Device d);

	List<OrderVO> getOrders();

	boolean confirm(String deviceId);

	boolean setArea(String deviceId, String area,int type);

	void updateArea(String id, List<String> area, int type);

	List<RouteVO> getRoute(String deviceid);
	
	public Map<String,List<RouteVO>> getAllRoute();

	boolean setRecommend(String qid);

	List<String> getRecommend();

	boolean removeRec(String qid);
}
