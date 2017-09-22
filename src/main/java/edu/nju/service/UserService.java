package edu.nju.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.nju.dao.UserDao;
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


}
