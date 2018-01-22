package edu.nju.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.nju.dao.BaseDao;
import edu.nju.dao.ReserveGetDao;
import edu.nju.dao.UserDao;
import edu.nju.entities.DeliveryInfo;
import edu.nju.entities.Device;
import edu.nju.entities.DeviceArea;
import edu.nju.entities.Order;
import edu.nju.entities.UserInfo;
import edu.nju.model.OrderVO;
import edu.nju.utils.Constants;
import edu.nju.utils.Utility;

@Repository
public class ReserveGetDaoImpl implements ReserveGetDao{ 
	 @Autowired
	 private BaseDao baseDao;
	 @Autowired
	 private UserDao userDao;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderVO> getOrder(String openId) {
		String hql = "from Order where openid =:openid order by createDate desc";
		List<Order> list = baseDao.getNewSession().createQuery(hql).setParameter("openid", openId).getResultList();
		List<OrderVO> volist = new ArrayList<OrderVO>();
		for(int i=0;i<list.size();i++){
			Order o = list.get(i);
			String openid = o.getOpenId();
			UserInfo u = userDao.getUser(openid);
			if(u!=null){
				OrderVO vo = new OrderVO(o.getId(), o.getStartDate(),o.getEndDate(), o.getDeviceNumber(), u.getName(), u.getPhone(),
						u.getAddress(),o.getState(),-1);
				volist.add(vo);
			}
			
		}
		return volist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserInfo getBefore(String openId) {
		Device device = getDeviceByOpenId(openId);
		String hql = "from Order where deviceId = :did order by endDate";
		List<Order> list =baseDao.getNewSession().createQuery(hql).setParameter("did", device.getId()).getResultList();
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				Order o = list.get(i);
				if(o.getOpenId().equals(openId)){
					if(i==0){
						return userDao.getUser("thisiscomponyinfomation");
					}else{
						Order beforeOrder = list.get(i-1);
						//(long)((beforeOrder.getEndDate().getTime() - beforeOrder.getStartDate().getTime()) / (1000 * 60 * 60 *24) + 0.5)
						if(Utility.getDaysBetween(beforeOrder.getStartDate(),beforeOrder.getEndDate())==Constants.USER_DATE){
							return userDao.getUser("thisiscomponyinfomation");
						}
						UserInfo beforeUser = userDao.getUser(beforeOrder.getOpenId());
						return beforeUser;
					}
				}
			}
			}
		UserInfo u = userDao.getUser("thisiscomponyinfomation");
		return u;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserInfo getAfter(String openId) {
		Device device = getDeviceByOpenId(openId);
		String hql = "from Order where deviceId = :did order by endDate";
		List<Order> list =baseDao.getNewSession().createQuery(hql).setParameter("did", device.getId()).getResultList();
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				Order o = list.get(i);
				if(o.getOpenId().equals(openId)){
					if(i==list.size()-1){
						return userDao.getUser("thisiscomponyinfomation");
					}else{
						//如果是寄回给公司了
						if(Utility.getDaysBetween(o.getStartDate(),o.getEndDate())==Constants.USER_DATE){
							return userDao.getUser("thisiscomponyinfomation");
						}
						Order afterOrder = list.get(i+1);
						//如果和下家之间的时间间隔超过2天，就寄回给公司
						if(Utility.getDaysBetween(afterOrder.getStartDate(),o.getEndDate())>2) {
							return userDao.getUser("thisiscomponyinfomation");
						}
						UserInfo afterUser = userDao.getUser(afterOrder.getOpenId());
						return afterUser;
					}
				}
			}
			}
		UserInfo u = userDao.getUser("thisiscomponyinfomation");
		return u;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Device getDeviceByOpenId(String openId) {
		String hql = "from Order where openId = :openId";
		List<Order> list = baseDao.getNewSession().createQuery(hql).setParameter("openId", openId).getResultList();
		if(list.size()>0){
			Order o = list.get(list.size()-1);
			String deviceId = o.getDeviceId();
			List<Device> deviceList =getDeviceById(deviceId);
			if(deviceList.size()>0){
				return deviceList.get(0);
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Device> getDeviceById(String deviceId){
		String hql = "from Device where id =:deviceId";
		List<Device> list = baseDao.getNewSession().createQuery(hql).setParameter("deviceId", deviceId).getResultList();
		return list;
	}

	// 根据用户id获得其寄出的快递单号信息
	@SuppressWarnings("unchecked")
	@Override
	public String getSendDid(String openid) {
		String hql = "from DeliveryInfo where sendId = :openId";
		List<DeliveryInfo> list = baseDao.getNewSession().createQuery(hql).setParameter("openId", openid).getResultList();
		if(list.size()>0){
			return list.get(list.size()-1).getDeliveryNumber();
		}
		return "暂无物流信息";
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getRecDid(String openid) {
		String hql = "from DeliveryInfo where receiveId = :openId";
		List<DeliveryInfo> list = baseDao.getNewSession().createQuery(hql).setParameter("openId", openid).getResultList();
		if(list.size()>0){
			return list.get(list.size()-1).getDeliveryNumber();
		}
		return "暂无物流信息";
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getByDeviceId(String deviceId) {
		String hql = "from Order where deviceId = :deviceId and endDate >:todayDate";
		Date todayDate = new Date();
		List<Order> list = baseDao.getNewSession().createQuery(hql).setParameter("deviceId", deviceId).
				setParameter("todayDate",todayDate).getResultList();
		List<String> dateList = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0;i<31;i++){
			todayDate = Utility.getSpecifiedDayAfter(todayDate, 1);
			String dateStr = sdf.format(todayDate);
			dateList.add(dateStr);
		}
		for(int i =0;i<list.size();i++){
			Order o = list.get(i);
			dateList.remove(sdf.format(o.getStartDate()));
//			dateList.remove(sdf.format(o.getEndDate()));
			long betweenDays = (long)((o.getEndDate().getTime() - o.getStartDate().getTime()) / (1000 * 60 * 60 *24) + 0.5); 
			int index=0;
			while(index<betweenDays){
				dateList.remove(sdf.format(Utility.getSpecifiedDayAfter(o.getStartDate(), 1+index)));
				index++;
			}
		}
		for(int i=0;i<dateList.size();i++){
			String dateStr = dateList.get(i);
			try{
				Date date = sdf.parse(dateStr);
				if(!dateList.contains(sdf.format(Utility.getSpecifiedDayAfter(date, 1)))){
					dateList.remove(dateStr);
					i--;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return dateList;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getUnavailableDates(String deviceId) {
		String hql = "from Order where deviceId = :deviceId and endDate >:todayDate";
		Date todayDate = new Date();
		List<Order> list = baseDao.getNewSession().createQuery(hql).setParameter("deviceId", deviceId)
				.setParameter("todayDate",todayDate).getResultList();
		List<String> dateList = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0;i<list.size();i++) {
			Order o = list.get(i);
			dateList.add(sdf.format(o.getStartDate()));
			long betweenDays = (long)((o.getEndDate().getTime() - o.getStartDate().getTime()) / (1000 * 60 * 60 *24) + 0.5); 
			int index=0;
			while(index<betweenDays){
				dateList.add(sdf.format(Utility.getSpecifiedDayAfter(o.getStartDate(), 1+index)));
				index++;
			}
		
		}
		List<String> resultList = new ArrayList<String>();
		for(int i=0;i<dateList.size();i++) {
			resultList.add(dateList.get(i));
		}
		if(dateList.size()>2) {
			for(int i=0;i<dateList.size();i++){
				String dateStr = dateList.get(i);
				try{
					Date date = sdf.parse(dateStr);
					if(dateList.contains(sdf.format(Utility.getSpecifiedDayAfter(date, 1)))){
						resultList.add(dateStr);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return dateList;
	}
	

	@Override
	@SuppressWarnings("unchecked")
	public List<Order> getOrderById(String openId){
		String hql = "from Order where openid =:openid";
		List<Order> list = baseDao.getNewSession().createQuery(hql).setParameter("openid", openId).getResultList();
		return list;
	}

	@Override
	public Order getOrderByorderId(String oderId) {
		String hql = "from Order where id =:orderId";
		Order o = (Order) baseDao.getNewSession().createQuery(hql).setParameter("orderId", oderId).getSingleResult();
		return o;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getOrdersByDeviceId(String deviceId) {
		String hql = "from Order where deviceId=:deviceId";
		List<Order> orders = baseDao.getNewSession().createQuery(hql).setParameter("deviceId", deviceId).getResultList();
		return orders;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAreaByDid(String did){
		String hql = "from DeviceArea where deviceId = :did";
		List<DeviceArea> das = baseDao.getNewSession().createQuery(hql).setParameter("did", did).getResultList();
		List<String> areas = new ArrayList<String>();
		for(int i=0;i<das.size();i++){
			areas.add(das.get(i).getArea());
		}
		return areas;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getOrders() {
		String hql = "from Order";
		List<Order> orders = baseDao.getNewSession().createQuery(hql).getResultList();
		return orders;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Order getByOpenAndDid(String openid, String deviceid) {
		String hql = "from Order where deviceId=:deviceId and openId = :openid";
		List<Order> orders = baseDao.getNewSession().createQuery(hql).setParameter("deviceId", deviceid).setParameter("openid", openid).getResultList();
		if(orders.size()>0){
			return orders.get(orders.size()-1);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Device> getAllDevice() {
		String hql = "from Device";
		List<Device> list = baseDao.getNewSession().createQuery(hql).getResultList();
		return list;
	}

}
