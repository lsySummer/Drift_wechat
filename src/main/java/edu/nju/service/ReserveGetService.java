package edu.nju.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.nju.dao.ReserveDao;
import edu.nju.dao.ReserveGetDao;
import edu.nju.entities.Device;
import edu.nju.entities.Order;
import edu.nju.entities.UserInfo;
import edu.nju.model.OrderVO;
import edu.nju.model.RESCODE;
import edu.nju.utils.Constants;

@Transactional
@Service
public class ReserveGetService {
	@Autowired
	ReserveGetDao dao;
	@Autowired
	ReserveDao rdao;
	//获得某个用户的预约信息
		public String getOrder(String openId){
			JSONObject resultObj=new JSONObject();
			List<OrderVO> list = dao.getOrder(openId);
			if (list!=null&&list.size()!=0) {
				resultObj.put(Constants.RESPONSE_CODE_KEY, RESCODE.SUCCESS);
				resultObj.put(Constants.RESPONSE_MSG_KEY, RESCODE.SUCCESS.getMsg());
				resultObj.put(Constants.RESPONSE_DATA_KEY, list);
				return resultObj.toString();
			}
			resultObj.put(Constants.RESPONSE_CODE_KEY, RESCODE.NOT_FOUND);
			resultObj.put(Constants.RESPONSE_MSG_KEY,
					RESCODE.NOT_FOUND.getMsg());
			return resultObj.toString();
		};
		//获得上家信息
		public UserInfo getBefore(String openId){
			UserInfo u = dao.getBefore(openId);
			return u;
		};
		//获得下家信息
		public UserInfo getAfter(String openId){
			UserInfo u = dao.getAfter(openId);
			return u;
		};
		
		/**
		 * 根据用户id获得其订单中的设备信息
		 * @return
		 */
		public Device getDeviceByOpenId(String openId){
			Device d = dao.getDeviceByOpenId(openId);
			return d;
		}
		
		/**
		 * @param did
		 * @return
		 * 根据甲醛仪id获得甲醛仪
		 */
		public Device getDeviceById(String did){
			List<Device> dlist = dao.getDeviceById(did);
			if(dlist.size()>0){
				return dlist.get(0);
			}
			return null;
		}
		
		/**
		 * @param openid
		 * @return
		 * 根据用户id获得其接收的快递单号信息
		 */
		public String getRecDid(String openid){
			String did = dao.getRecDid(openid);
			return did;
		}
		
		
		/**
		 * @param openid
		 * @return
		 * 根据用户id获得其寄出的快递单号信息
		 */
		public String getSendDid(String openid){
			String did = dao.getSendDid(openid);
			return did;
		}
		
		/**
		 * @param openId
		 * @return
		 * 根据openid获得其订单状态
		 */
		public String getOrderState(String openId){
			List<Order> olist = dao.getOrderById(openId);
			if(olist.size()>0){
				Order o = olist.get(olist.size()-1);
				return o.getState();
			}
			return "暂无";
		}
		

		/**
		 * @param deviceId
		 * @return
		 * 根据设备id获得其可预约的日期2017-10-20
		 */
		public List<String> getByDeviceId(String deviceId){
			List<String> list = dao.getByDeviceId(deviceId);
			return list;
		}

		
		/**
		 * 获得不可用的日期
		 */
		public List<String> getUnavailableDates(String openid,int type){
			List<Device> devices = rdao.reserveDevice(openid, type);
			List<String> result = new ArrayList<String>();
			for(Device d:devices) {
				List<String> list = dao.getUnavailableDates(d.getId());
				result.addAll(list);
			}
			LinkedHashSet<String> set = new LinkedHashSet<String>(result);  
	        ArrayList<String> listWithoutDuplicateElements = new ArrayList<String>(set);  
			return listWithoutDuplicateElements;
		}
	
}
