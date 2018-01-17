package edu.nju.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.nju.dao.BaseDao;
import edu.nju.dao.ReserveDao;
import edu.nju.dao.ReserveGetDao;
import edu.nju.dao.UserDao;
import edu.nju.entities.CheckResult;
import edu.nju.entities.DeliveryInfo;
import edu.nju.entities.Device;
import edu.nju.entities.DeviceArea;
import edu.nju.entities.Order;
import edu.nju.entities.UserInfo;
import edu.nju.utils.Constants;
import edu.nju.utils.Utility;

@Repository
public class ReserveDaoImpl implements ReserveDao{
	
	 @Autowired
	 private BaseDao baseDao;
	 @Autowired
	 private UserDao userDao;
	 @Autowired
	 private ReserveGetDao rgetDao;
//	 private Logger log = Logger.getLogger(UserController.class);


	/* (non-Javadoc)
	 * @see edu.nju.dao.ReserveDao#saveDelInfo(java.lang.String, java.lang.String)
	 * 保存快递信息
	 */
	@Override
	public boolean saveDelInfo(String openId,String did) {
		Device d = rgetDao.getDeviceByOpenId(openId);//当前用户的手中设备
		//把当前用户的订单改成已寄出
		Order o = rgetDao.getByOpenAndDid(openId,d.getId());
		o.setState("已寄出");
		baseDao.update(o);
		DeliveryInfo info = new DeliveryInfo();
		info.setDeliveryNumber(did);
		info.setSendId(openId);
		
		//把下家的订单改成上家已发货
		UserInfo afterUser = rgetDao.getAfter(openId);
		info.setReceiveId(afterUser.getOpenid());
		baseDao.save(info);
		if(!afterUser.getOpenid().equals("thisiscomponyinfomation")){
			Order afterOrder = rgetDao.getByOpenAndDid(afterUser.getOpenid(), d.getId());
			afterOrder.setState("上家已发货");
			baseDao.update(afterOrder);
		}else{
			//如果是寄回公司的，特殊处理.即给他的结束日期延长一天
			o.setEndDate(Utility.getSpecifiedDayAfter(o.getEndDate(), 1));
			baseDao.update(o);
		}
		return true;
	}
	

	@SuppressWarnings("unchecked")
	public List<Device> reserveDevice(String openid,int type) {
		UserInfo userInfo = userDao.getUser(openid);
		String[] arr =userInfo.getAddress().split(" ");
		String hql = "from DeviceArea where area=:area and type=:type";
		List<DeviceArea> list = baseDao.getNewSession().createQuery(hql)
				.setParameter("area", arr[0]).setParameter("type", type).getResultList();
		List<Device> dlist = new ArrayList<Device>();
		for(int i=0;i<list.size();i++){
			DeviceArea da = list.get(i);
			String did = da.getDeviceId();
			List<Device> tempList = rgetDao.getDeviceById(did);
			if(tempList.size()>0){
				dlist.add(tempList.get(0));
			}
		}
		return dlist;
	}

	@Override
	synchronized public boolean makeOrder(String openid,int type,Date startDate,Date endDate) {
		List<Device> devices = reserveDevice(openid, type);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String deviceId = "";
		for(Device d:devices) {
			List<String> availList = rgetDao.getByDeviceId(d.getId());
			if(availList.contains(sdf.format(startDate))) {
				deviceId = d.getId();
				break;
			}
		}
		if(deviceId.equals("")) {
			return false;
		}
		UserInfo user = userDao.getUser(openid);
		Device device = rgetDao.getDeviceById(deviceId).get(0);
		device.setQueueNum(device.getQueueNum()+1);//排队人数+1
		baseDao.update(device);
		endDate = Utility.getSpecifiedDayAfter(startDate,Constants.USER_DATE-1);
		device.setLoc(user.getAddress());
		baseDao.update(device);
		String startStr = sdf.format(startDate);
		List<String> dates = rgetDao.getByDeviceId(deviceId);
		if(dates.contains(startStr)){
			Date today = new Date();
			Order o = new Order(openid,startDate,endDate,device.getId(),device.getNumber(),"等待发货",0,type,today);
			baseDao.save(o);
			return true;
		}else{
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Order> getById(String deviceId){
		String hql = "from Order where deviceId =:deviceId";
		List<Order> list = baseDao.getNewSession().createQuery(hql).setParameter("deviceId", deviceId).getResultList();
		return list;
	}


	@SuppressWarnings("unchecked")
	@Override
	public boolean confirm(String openid) {
		//自己的订单状态修改为“已确认收货”
		//上家的订单状态改为“下家已收货”
		Device d = rgetDao.getDeviceByOpenId(openid);//当前用户的手中设备
		d.setQueueNum(d.getQueueNum()-1);
		String hql = "from Order where openId =:openid";
		UserInfo currentUser = userDao.getUser(openid);
		d.setLoc(currentUser.getAddress());//地址修改为当前用户的地址
		baseDao.update(d);
		List<Order> list = baseDao.getNewSession().createQuery(hql).setParameter("openid", openid).getResultList();
		if(list.size()>0){
			Order o = list.get(list.size()-1);
			o.setState("已确认收货");
			baseDao.update(o);
			
			UserInfo beforeUser = rgetDao.getBefore(openid);
			List<Order> beforeList = rgetDao.getOrderById(beforeUser.getOpenid());
			if(beforeList.size()>0){
				Order beforeOrder = beforeList.get(beforeList.size()-1);
				beforeOrder.setState("下家已收货");
				baseDao.update(beforeOrder);
			}
			return true;
		}
		return false;
	}


	@Override
	public boolean saveList(List<CheckResult> list) {
		try {
			for(CheckResult result:list) {
				baseDao.save(result);
			}
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	



	@SuppressWarnings("unchecked")
	@Override
	public List<CheckResult> getCheckResult(String openid) {
		List<Order> olist = rgetDao.getOrderById(openid);
		String orderid="";
		if(olist.size()>0) {
			orderid = olist.get(olist.size()-1).getId();
			String hql = "from CheckResult where orderid =:orderid";
			List<CheckResult> list = baseDao.getNewSession().createQuery(hql).setParameter("orderid", orderid).getResultList();
			return list;
		}
		return null;
	}
}
