package edu.nju.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.nju.dao.BaseDao;
import edu.nju.dao.ManageDao;
import edu.nju.dao.ReserveGetDao;
import edu.nju.entities.Device;
import edu.nju.entities.Order;
import edu.nju.entities.UserInfo;
import edu.nju.model.DeviceVO;
import edu.nju.model.OrderVO;
import edu.nju.utils.Utility;

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
	
	//获得所有设备状态信息
	public List<DeviceVO> getDevices(){
		List<DeviceVO> dlist = manageDao.getDevices();
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
		String deviceId = order.getDeviceId();
		//获得所有的Device
		List<DeviceVO> devices = manageDao.getDevices();
		for(DeviceVO deviceVO:devices){
			String id = deviceVO.getId();
			//排除掉现在使用的device
			if(id.equals(deviceId)){
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
		return result;
	}
	
	public Order updateOrder(String orderId,String deviceNumber,
			String deviceId,Date startDate,Date endDate){
		Order o = reserveGetDao.getOrderByorderId(orderId);
		o.setDeviceId(deviceId);o.setDeviceNumber(deviceNumber);
		o.setStartDate(startDate);;o.setEndDate(endDate);
		baseDao.update(o);
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
	
	public List<OrderVO> getOrders(){
		List<OrderVO> olist = manageDao.getOrders();
		return olist;
	}
	
	//确认收货
	public boolean confirm(String deviceId){
		return manageDao.confirm(deviceId);
	}

	public boolean setArea(String deviceId,String area,int type){
		return manageDao.setArea(deviceId,area,type);
	}
}
