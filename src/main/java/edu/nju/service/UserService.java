package edu.nju.service;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.nju.dao.UserDao;
import edu.nju.entities.UserInfo;
import edu.nju.model.RESCODE;
import edu.nju.model.UserVO;
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

	public String setAddress(String openid,String zmxyid, String address, String phone, Date startDate, Date endDate,String name,String nickname) {
		JSONObject resultObj=new JSONObject();
		boolean b = dao.setAddress(openid,zmxyid,address,phone,startDate,endDate,name,nickname);
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
	
	public String setZMXY(String openid,String zmxyid){
		JSONObject resultObj = new JSONObject();
		boolean b = dao.setZMXY(openid,zmxyid);
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

	public UserInfo getUser(String openid) {
		UserInfo s = dao.getUser(openid);
		return s;
	}
	
	public String getByNickName(String nickname){
		JSONObject resultObj=new JSONObject();
		UserInfo s = dao.getByNickName(nickname);
		if (s!=null) {
			resultObj.put(Constants.RESPONSE_CODE_KEY, RESCODE.SUCCESS);
			resultObj.put(Constants.RESPONSE_MSG_KEY, RESCODE.SUCCESS.getMsg());
			resultObj.put(Constants.RESPONSE_DATA_KEY, new JSONObject(s));
			return resultObj.toString();
		}
		resultObj.put(Constants.RESPONSE_CODE_KEY, RESCODE.NOT_FOUND);
		resultObj.put(Constants.RESPONSE_MSG_KEY,
				RESCODE.NOT_FOUND.getMsg());
		return resultObj.toString();
	}
	
	public String getUserVO(){
		JSONObject resultObj=new JSONObject();
		List<UserVO> list = dao.getUserVO();
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
	}
	
	public String saveOrUpdate(UserInfo user){
		JSONObject resultObj=new JSONObject();
		boolean b = dao.saveOrUpdate(user);
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
	
}
