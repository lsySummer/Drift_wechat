package edu.nju.service;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.nju.dao.BaseDao;
import edu.nju.dao.ReserveDao;
import edu.nju.dao.ReserveGetDao;
import edu.nju.entities.Device;
import edu.nju.entities.Order;
import edu.nju.entities.UserInfo;
import edu.nju.model.OrderVO;
import edu.nju.utils.Utility;
import edu.nju.utils.WechatSend;

@Transactional
@Service
public class ReserveService {
	@Autowired
	ReserveDao dao;
	@Autowired
	ReserveGetDao gdao;
	@Autowired
	BaseDao baseDao;
	
	/**
	 * @param did 快递单号
	 * @param openId 
	 * @return
	 * 保存快递信息
	 */
	public boolean saveDelInfo(String openId,String did){
		boolean b = dao.saveDelInfo(openId,did);
		UserInfo u = gdao.getAfter(openId);
//		JSONObject data = WechatSend.packJsonmsg(o.getDeviceNumber(),o.getStartDate(),o.getEndDate());
		//TODO
		String url="http://open.weixin.qq.com/connect/oauth2/authorize?appid=wx80e3eed8e26e852f&redirect_uri=http%3A%2F%2Fdrift.gmair.net%2FDrift_wechat%2Fapi%2Fwechat%2Fdeliver&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
		WechatSend.sendWechatmsgToUser(u.getOpenid(),"4J8WZn8LtCyvmV5yMpT8RMlcUnUQOVsnCeRxL4DdUDw",url,"",new JSONObject());
		return b;
	}
	
	//用户购买耗材
	public boolean paySupply(String openId,int num,int ifPay){
		return true;
	};
	//用户赞助
	public boolean payDonate(String openId){
		return true;
	};
	
	/**
	 * @return
	 * 用户下订单过程
	 */
	public boolean makeOrder(String openid, int type,Date startDate){
		boolean b = dao.makeOrder(openid,type,startDate,Utility.getSpecifiedDayAfter(startDate, 1));
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
	public Device reserveDevice(String openId,int type){
		return dao.reserveDevice(openId,type);
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
				if(d.getLoc().equals("company")){
					return true;
				}else{
					return false;
				}
			}
		}
	}
	
	/**
	 * @param orderid
	 * @return
	 * 公司发货
	 */
	public boolean companySend(String orderid){
		Order o = gdao.getOrderByorderId(orderid);
		o.setState("上家已发货");
		try{
			baseDao.update(o);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @param orderid
	 * @return
	 * 公司收货
	 */
	public boolean companyReceive(String orderid){
		Order o = gdao.getOrderByorderId(orderid);
		o.setState("下家已收货");
		try{
			baseDao.update(o);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
