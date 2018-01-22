package edu.nju.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.nju.dao.BaseDao;
import edu.nju.dao.ManageDao;
import edu.nju.dao.ReserveGetDao;
import edu.nju.dao.UserDao;
import edu.nju.entities.Device;
import edu.nju.entities.Order;
import edu.nju.entities.Question;
import edu.nju.entities.UserInfo;
import edu.nju.model.DeviceVO;
import edu.nju.model.OrderVO;
import edu.nju.model.RouteVO;
import edu.nju.utils.Utility;
import edu.nju.utils.WechatConfig;
import edu.nju.utils.WechatSend;

@Transactional
@Service
public class ManageService {
	@Autowired
	BaseDao baseDao;
	@Autowired
	ManageDao manageDao;
	@Autowired
	ReserveGetService gservice;
	@Autowired
	ReserveGetDao reserveGetDao;
	@Autowired
	UserDao userDao;
	
	//获得所有设备状态信息
	public List<DeviceVO> getDevices(int start,int num){
		List<DeviceVO> dlist = manageDao.getDevices(start,num);
		return dlist;
	};

	//获得所有用户信息
	public List<UserInfo> getUsers(){
		List<UserInfo> ulist = manageDao.getUsers();
		return ulist;
	};
	
	//添加设备
//	public Device addDevice(Device d,String area,int type){
//		Device device = gservice.getDeviceById(d.getId());
//		if(device==null){
//			device = manageDao.addDevice(d);
//			manageDao.setArea(device.getId(), area, type);
//			return device;
//		}else{
//			manageDao.updateArea(d.getId(),area,type);
//			return d;
//		}
//	}
	/**
	 * 重新调整订单时，获得可用的device和对应的可用时间
	 * @param orderID
	 * @return
	 * @author liushao
	 */
	public Map<DeviceVO,Date> getAvailableDevice(String orderID){
		Map<DeviceVO, Date> result = new HashMap<>();
		//获得要修改的订单里的DeviceId
		Order order = reserveGetDao.getOrderByorderId(orderID);
		String uid = order.getOpenId();
		List<UserInfo> ulist = userDao.getUserById(uid);
		if(ulist.size()>0){
			String area = ulist.get(0).getAddress().split(" ")[0];
			String deviceId = order.getDeviceId();
			//获得所有的Device
			List<DeviceVO> devices = manageDao.getDevices(0,100);
			for(DeviceVO deviceVO:devices){
				String id = deviceVO.getId();
				//排除掉现在使用的device
				if(id.equals(deviceId)||(!deviceVO.getArea().contains(area))){
					continue;
				}
				//获得这个设备的所有订单
				List<Order> orders = reserveGetDao.getOrdersByDeviceId(id);
				Date today = new Date();
				//如果这个设备没有订单，那么就从明天可预订
				if(null == orders || orders.isEmpty()){
					result.put(deviceVO,  Utility.getSpecifiedDayAfter(today, 1));
				}else{
					//如果有订单，找到最新的endDate
					Date date = orders.get(0).getEndDate();
					for(Order o:orders){
						if (o.getEndDate().after(date)) {
							date = o.getEndDate();
						}
					}
					if(date.before(today)){
						result.put(deviceVO, Utility.getSpecifiedDayAfter(today, 1));
					}else{
						result.put(deviceVO, Utility.getSpecifiedDayAfter(date, 1));
					}
				} 
			}
		}
		
		return result;
	}
	
	public Order updateOrder(String orderId,String deviceNumber,
		String deviceId,Date startDate){
		Order o = reserveGetDao.getOrderByorderId(orderId);
		o.setDeviceId(deviceId);o.setDeviceNumber(deviceNumber);
		o.setStartDate(startDate);
		Date endDate = Utility.getSpecifiedDayAfter(startDate, 1);
		o.setEndDate(endDate);
		baseDao.update(o);
		Device device = gservice.getDeviceById(deviceId);
		device.setQueueNum(device.getQueueNum()+1);
		baseDao.update(device);
//		String tOpenid="oBaSqs929zqFraeZy2YXWeqAQJ7o";
		//TODO
		JSONObject data = WechatSend.packJsonmsg(o.getDeviceNumber(),o.getStartDate(),o.getEndDate());
		String url="http://open.weixin.qq.com/connect/oauth2/authorize?appid=wx80e3eed8e26e852f&redirect_uri=http%3A%2F%2Fdrift.gmair.net%2FDrift_wechat%2Fapi%2Fwechat%2Fcenter&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
		WechatSend.sendWechatmsgToUser(o.getOpenId(),WechatConfig.TEMPLAT_ID,url,"",data);
		return o;
	}
	
	public Device addDeviceList(Device d,List<String> list,int type){
		Device device = gservice.getDeviceById(d.getId());
		if(device==null){
			device = manageDao.addDevice(d);
			for(int i=0;i<list.size();i++){
				manageDao.setArea(device.getId(), list.get(i), type);
			}
			return device;
		}else{
			manageDao.updateArea(d.getId(),list,type);
			return d;
		}
	}
	
	public List<OrderVO> getOrders(int start,int num){
		List<OrderVO> olist = manageDao.getOrders(start,num);
		return olist;
	}
	
	//确认收货
	public boolean confirm(String deviceId){
		return manageDao.confirm(deviceId);
	}

	public boolean setArea(String deviceId,String area,int type){
		return manageDao.setArea(deviceId,area,type);
	}
	
	/**
	 * @return
	 * 根据设备id获得设备流转路线
	 */
	public List<RouteVO> getRoute(String deviceid){
		return manageDao.getRoute(deviceid);
	}
	

	/**
	 * 获得所有设备的数量
	 */
	public Long getDevicenum() {
		Long num = manageDao.getDevicenum();
		return num;
	}
	
	/**
	 * @return
	 * 获得所有设备流转路线
	 */
	public Map<String,List<RouteVO>> getAllRoute(){
		return manageDao.getAllRoute();
	}
	
	/**
	 * @return
	 * 设置推荐的问题
	 */
	public boolean setRecommend(String qid) {
		return manageDao.setRecommend(qid);
	}
	
	public boolean removeRec(String qid) {
		return manageDao.removeRec(qid);
	}
	
	/**
	 * @return
	 * 获得推荐的问题
	 */
	public List<Question> getRecommend() {
		return manageDao.getRecommend();
	}
	
	public boolean login(String username,String password) {
		return manageDao.login(username,password);
	}
}
