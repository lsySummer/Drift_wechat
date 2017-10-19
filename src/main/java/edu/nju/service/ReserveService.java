package edu.nju.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.nju.dao.ReserveDao;
import edu.nju.entities.Device;
import edu.nju.entities.UserInfo;
import edu.nju.model.OrderVO;
import edu.nju.model.RESCODE;
import edu.nju.utils.Constants;

@Transactional
@Service
public class ReserveService {
	@Autowired
	ReserveDao dao;
	
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
	 * 根据用户id获得其设备信息
	 * @return
	 */
	public Device getDeviceByOpenId(String openId){
		Device d = dao.getDeviceByOpenId(openId);
		return d;
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
	 * @param did 快递单号
	 * @param openId 
	 * @return
	 * 保存快递信息
	 */
	public boolean saveDelInfo(String openId,String did){
		boolean b = dao.saveDelInfo(openId,did);
		return b;
	}
	
	/**
	 * @param deviceId
	 * @return
	 * 根据甲醛仪id获得其快递单号
	 */
//	public String getDelNum(String deviceId){
//		String num = dao.getDelNum(deviceId);
//		return num;
//	}
	
	//用户购买耗材
	public boolean paySupply(String openId,int num,int ifPay){
		return true;
	};
	//用户赞助
	public boolean payDonate(String openId){
		return true;
	};
	/**
	 * @param openId 
	 * @return 设备id
	 * 后台分配设备给用户
	 */
	public Device reserve(String openId){
		Device d = dao.reserve(openId);
		return d;
	};
	//后台优先分配设备给付费用户
	public Device priorReserve(String openId){
		Device d = dao.priorReserve(openId);
		return d;
	};
	
	/**
	 * @return
	 * 管理员为用户下订单过程
	 */
	public boolean makeOrder(String openid,int ifPay,int num){
		boolean b = dao.makeOrder(openid,ifPay,num);
		return b;
	}

	/**
	 * 用户确认收货，
	 * @param openid
	 * @return
	 */
	public boolean confirm(String openid){
		boolean b = dao.confirm(openid);
		return b;
	}

}
