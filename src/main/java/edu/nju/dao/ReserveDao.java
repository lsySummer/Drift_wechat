package edu.nju.dao;

import java.util.Date;

import edu.nju.entities.Device;

public interface ReserveDao {

	boolean saveDelInfo(String openId,String did);

	boolean makeOrder(String openid,int type,Date date,Date endDate);

	boolean confirm(String openid);

	public Device reserveDevice(String area,int type);

}
