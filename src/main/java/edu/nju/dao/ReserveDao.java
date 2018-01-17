package edu.nju.dao;

import java.util.Date;
import java.util.List;

import edu.nju.entities.CheckResult;
import edu.nju.entities.Device;

public interface ReserveDao {

	boolean saveDelInfo(String openId,String did);

	boolean makeOrder(String openid,int type,Date date,Date endDate);

	boolean confirm(String openid);

	public List<Device> reserveDevice(String openid,int type);

	boolean saveList(List<CheckResult> list);

	public List<CheckResult> getCheckResult(String openid);

}
