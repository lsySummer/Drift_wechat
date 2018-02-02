package edu.nju.dao;

import java.util.List;
import java.util.Map;

import edu.nju.entities.Device;
import edu.nju.entities.Question;
import edu.nju.entities.UserInfo;
import edu.nju.model.DeviceVO;
import edu.nju.model.OrderVO;
import edu.nju.model.RouteVO;

public interface ManageDao {

	List<DeviceVO> getDevices(int start, int page);

	List<UserInfo> getUsers();

	Device addDevice(Device d);

	List<OrderVO> getOrders(int start, int page);

	boolean confirm(String deviceId);

	boolean setArea(String deviceId, String area);

	void updateArea(String id, List<String> area);

	List<RouteVO> getRoute(String deviceid);
	
	public Map<String,List<RouteVO>> getAllRoute();

	boolean setRecommend(String qid);

	List<Question> getRecommend();

	boolean removeRec(String qid);

	boolean login(String username, String password);

	Long getDevicenum();

	List<Question> getNotRecommend(int start,int num);

	boolean deleteQuestion(String qid);

	boolean deleteAnswer(String aid);

	boolean changeDeviceType(String deviceId, int type);

	boolean deleteDevice(String deviceId);

	Long getOrdernum();
}
