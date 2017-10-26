package edu.nju.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.nju.dao.ReserveDao;
import edu.nju.dao.ReserveGetDao;
import edu.nju.entities.Device;
import edu.nju.entities.UserInfo;
import edu.nju.model.OrderVO;

@Transactional
@Service
public class ReserveService {
	@Autowired
	ReserveDao dao;
	@Autowired
	ReserveGetDao gdao;
	
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
//	public Device reserve(String openId){
//		Device d = dao.reserve(openId);
//		return d;
//	};
	//后台优先分配设备给付费用户
//	public Device priorReserve(String openId){
//		Device d = dao.priorReserve(openId);
//		return d;
//	};
	
	/**
	 * @return
	 * 用户下订单过程
	 */
	public boolean makeOrder(String openid, String area,int type,Date startDate,Date endDate){
		boolean b = dao.makeOrder(openid,area,type,startDate,endDate);
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
	
	
	/**
	 * @param openId
	 * @param area
	 * @return
	 * 用户预订时，获得可供其预订的设备
	 */
	public Device reserveDevice(String area,int type){
		return dao.reserveDevice(area,type);
	};
	
	/**
	 * @param openid
	 * @return
	 * 检验是否能够预约。判断方法是下家order的状态
	 */
	public boolean checkReserve(String openid){
		Device d = gdao.getDeviceByOpenId(openid);
		if(d==null){//没有预约过
			return true;
		}else{
			UserInfo after = gdao.getAfter(openid);
			List<OrderVO> volist = gdao.getOrder(after.getOpenid());
			if(volist.size()>0){//有下家
				OrderVO vo = volist.get(0);
				String state = vo.getState();
				if(state.equals("已收货")||state.equals("已寄出")||state.equals("下家已收货")){
					return true;
				}else{
					return false;
				}
			}else{//退回给公司
				if(d.getLoc().equals("Company")){
					return true;
				}else{
					return false;
				}
			}
		}
	}

}
