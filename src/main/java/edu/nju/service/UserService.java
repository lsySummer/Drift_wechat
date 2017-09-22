package edu.nju.service;

import java.util.Date;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.nju.dao.UserDao;
import edu.nju.entities.UserInfo;
import edu.nju.model.RESCODE;
import edu.nju.utils.Constants;


@Transactional
@Service
public class UserService {

	@Autowired
	UserDao dao;
	
	public String register(String openId, String nickName) {
		JSONObject resultObj=new JSONObject();
		boolean b = dao.register(openId,nickName);
		if (!b) {
			resultObj.put(Constants.RESPONSE_CODE_KEY, RESCODE.CREATE_ERROR);
			resultObj.put(Constants.RESPONSE_MSG_KEY,
					RESCODE.CREATE_ERROR.getMsg());
			return resultObj.toString();
		}
		resultObj.put(Constants.RESPONSE_CODE_KEY, RESCODE.SUCCESS);
		resultObj.put(Constants.RESPONSE_MSG_KEY, RESCODE.SUCCESS.getMsg());
		return resultObj.toString();
	}

	public String setZMInfo(String openId,String transactionid, int score) {
		JSONObject resultObj=new JSONObject();
		boolean b = dao.setZMInfo(openId,transactionid,score);
		if (!b) {
			resultObj.put(Constants.RESPONSE_CODE_KEY, RESCODE.CREATE_ERROR);
			resultObj.put(Constants.RESPONSE_MSG_KEY,
					RESCODE.CREATE_ERROR.getMsg());
			return resultObj.toString();
		}
		resultObj.put(Constants.RESPONSE_CODE_KEY, RESCODE.SUCCESS);
		resultObj.put(Constants.RESPONSE_MSG_KEY, RESCODE.SUCCESS.getMsg());
		return resultObj.toString();
	}

	public String setAddress(String openid,String address, String phone, Date startDate, Date endDate) {
		JSONObject resultObj=new JSONObject();
		boolean b = dao.setAddress(openid,address,phone,startDate,endDate);
		if (!b) {
			resultObj.put(Constants.RESPONSE_CODE_KEY, RESCODE.CREATE_ERROR);
			resultObj.put(Constants.RESPONSE_MSG_KEY,
					RESCODE.CREATE_ERROR.getMsg());
			return resultObj.toString();
		}
		resultObj.put(Constants.RESPONSE_CODE_KEY, RESCODE.SUCCESS);
		resultObj.put(Constants.RESPONSE_MSG_KEY, RESCODE.SUCCESS.getMsg());
		return resultObj.toString();
	}

	public String getUser(String openid) {
		JSONObject resultObj=new JSONObject();
		UserInfo b = dao.getUser(openid);
		if (b==null) {
			resultObj.put(Constants.RESPONSE_CODE_KEY, RESCODE.CREATE_ERROR);
			resultObj.put(Constants.RESPONSE_MSG_KEY,
					RESCODE.NOT_FOUND.getMsg());
			return resultObj.toString();
		}
		resultObj.put(Constants.RESPONSE_CODE_KEY, RESCODE.SUCCESS);
		resultObj.put(Constants.RESPONSE_MSG_KEY, RESCODE.SUCCESS.getMsg());
		resultObj.put(Constants.RESPONSE_DATA_KEY, b);
		return resultObj.toString();
	}


}
