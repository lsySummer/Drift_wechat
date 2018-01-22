package edu.nju.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.nju.dao.BaseDao;
import edu.nju.dao.ManageDao;
import edu.nju.dao.QADao;
import edu.nju.dao.UserDao;
import edu.nju.entities.Admin;
import edu.nju.entities.Device;
import edu.nju.entities.DeviceArea;
import edu.nju.entities.Order;
import edu.nju.entities.Question;
import edu.nju.entities.RecommendQ;
import edu.nju.entities.UserInfo;
import edu.nju.model.DeviceVO;
import edu.nju.model.OrderVO;
import edu.nju.model.RouteVO;

@Repository
public class ManageDaoImpl implements ManageDao{
	
	 @Autowired
	 private BaseDao baseDao;
	 @Autowired
	 private UserDao userDao;
//	 @Autowired
//	 private CommunityDao cDao;
	 @Autowired
	 private QADao qdao;
	 
	@SuppressWarnings("unchecked")
	@Override
	public List<DeviceVO> getDevices(int start,int page) {
		String hql = "from Device";
		List<Device> list = baseDao.getNewSession().createQuery(hql).setFirstResult(start).setMaxResults(page).getResultList();
		List<DeviceVO> volist = new ArrayList<DeviceVO>();
		for(int i=0;i<list.size();i++){
			Device d = list.get(i);
			String dhql = "from DeviceArea where deviceId=:did";
			List<DeviceArea> dlist = baseDao.getNewSession().createQuery(dhql).setParameter("did", d.getId()).getResultList();
			List<String> areaList = new ArrayList<String>();
			//String id, String number, String loc, int queueNum, List<String> area, int type
			for(int j=0;j<dlist.size();j++){
				areaList.add(dlist.get(j).getArea());
			}
			DeviceVO vo = new DeviceVO(d.getId(),d.getNumber(),d.getLoc(),d.getQueueNum(),
				areaList,dlist.get(0).getType());
			volist.add(vo);
		}
		return volist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfo> getUsers() {
		String hql = "from UserInfo";
		List<UserInfo> list = baseDao.getNewSession().createQuery(hql).getResultList();
		return list;
	}

	@Override
	public Device addDevice(Device d) {
		try{
			baseDao.save(d);
			return d;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderVO> getOrders(int start,int page) {
		String hql = "from Order";
		List<Order> list = baseDao.getNewSession().createQuery(hql).setFirstResult(start).setMaxResults(page).getResultList();
		List<OrderVO> volist = new ArrayList<OrderVO>();
		for(int i=0;i<list.size();i++){
			Order o = list.get(i);
			String openid = o.getOpenId();
			UserInfo u = userDao.getUser(openid);
			if(u!=null){
//				UserComment comment = cDao.getComment(openid);
				float jqNum = -1;
//				if(comment != null){
//					jqNum = comment.getNum();
//				}
				jqNum=0;
				OrderVO vo = new OrderVO(o.getId(), o.getStartDate(),o.getEndDate(), o.getDeviceNumber(), u.getName(), u.getPhone(),
						u.getAddress(),o.getState(),jqNum);
				volist.add(vo);
			}
			
		}
		return volist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean confirm(String deviceId) {
		String hql = "from Device where id = :deviceId";
		List<Device> list = baseDao.getNewSession().createQuery(hql).setParameter("deviceId", deviceId).getResultList();
		if(list.size()>0){
			Device d = list.get(0);
			d.setLoc("company");
			return true;
		}
		return false;
	}

	@Override
	public boolean setArea(String deviceId, String area,int type) {
		DeviceArea da = new DeviceArea();
		da.setArea(area);
		da.setDeviceId(deviceId);
		da.setType(type);
		try{
			baseDao.save(da);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateArea(String id, List<String> area, int type) {
		String hql = "from DeviceArea where deviceId=:did";
		List<DeviceArea> list = baseDao.getNewSession().createQuery(hql).setParameter("did", id).getResultList();
		if(list.size()>0){
			for(int i =0;i<list.size();i++){
				DeviceArea d = list.get(i);
				baseDao.delete(d);
				DeviceArea da = new DeviceArea();
				da.setArea(area.get(i));
				da.setDeviceId(id);
				da.setType(type);
				baseDao.save(da);
			}
			
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RouteVO> getRoute(String deviceid) {
		List<RouteVO> result = new ArrayList<RouteVO>();
		String hql = "from Order where deviceId=:deviceid";
		List<Order> list = baseDao.getNewSession().createQuery(hql).setParameter("deviceid", deviceid).getResultList();
		for(Order o:list){
			String openid = o.getOpenId();
			UserInfo u = userDao.getUser(openid);
			RouteVO vo = new RouteVO(o.getStartDate(),o.getEndDate(),o.getDeviceId(),o.getDeviceNumber(),u.getAddress());
			result.add(vo);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, List<RouteVO>> getAllRoute() {
		String hql = "from Device";
		List<Device> list = baseDao.getNewSession().createQuery(hql).getResultList();
		Map<String, List<RouteVO>> map = new HashMap<String,List<RouteVO>>();
		for(int i=0;i<list.size();i++){
			Device d = list.get(i);
			List<RouteVO> onelist = getRoute(d.getId());
			if(onelist.size()>0){
				map.put(d.getId(), onelist);
			}
		}
		return map;
	}

	@Override
	public boolean setRecommend(String qid) {
		RecommendQ rq = new RecommendQ();
		rq.setQid(qid);
		baseDao.save(rq);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> getRecommend() {
		String hql = "from RecommendQ";
		List<RecommendQ> list = baseDao.getNewSession().createQuery(hql).getResultList();
		List<Question> result = new ArrayList<Question>();
		for(int i=0;i<list.size();i++) {
			String qid = list.get(i).getQid();
			result.add(qdao.getByQuestionId(qid));
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean removeRec(String qid) {
		String hql = "from RecommendQ where qid=:qid";
		List<RecommendQ> list=baseDao.getNewSession().createQuery(hql).setParameter("qid", qid).getResultList();
		if(list.size()>0) {
			baseDao.delete(list.get(0));
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean login(String username, String password) {
		String hql = "from Admin where username=:username and password=:password";
		List<Admin> list=baseDao.getNewSession().createQuery(hql).setParameter("username", username)
				.setParameter("password", password).getResultList();
		if(list.size()>0) {
			return true;
		}
		return false;
	}

	@Override
	public Long getDevicenum() {
		String hql = "select count(*) from Device";
		Long num = (Long) baseDao.getNewSession().createQuery(hql).getSingleResult();
		return num;
	}

}
