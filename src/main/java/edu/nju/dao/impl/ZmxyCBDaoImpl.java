package edu.nju.dao.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.antgroup.zmxy.openplatform.api.DefaultZhimaClient;
import com.antgroup.zmxy.openplatform.api.ZhimaApiException;

import edu.nju.dao.BaseDao;
import edu.nju.dao.ZmxyCBDao;
import edu.nju.entities.Test;

@Transactional
@Repository("zmxyCBDao")
public class ZmxyCBDaoImpl implements ZmxyCBDao{
	
//	 @Autowired
//	 private BaseDao baseDao;
	private String gatewayUrl = "https://zmopenapi.zmxy.com.cn/openapi.do";
	private String appId = "300000028";
	private String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMJ2xz3ivQmyisZ5" + 
			"DF/JmaIfFaQyMR4Mi1RVUMG5gcb95MMPIAGn/8JG3BlgyTF0sKu1mRT4+jKxENHP" + 
			"GaUJMwFBAPhbqx4rkQQdCSsX7TMo0IORafzN+5mNbC137kM6qNvwyVq2QAD4nh8j" + 
			"Bl+7QBJDORoJwJo1iBgv7im+8iL9AgMBAAECgYB8osxIac5nLLutQWAg9IE3W1zv" + 
			"hAvfCoMyZgwAXflDpO695i/JX/eYePoQeev5eqTRKO8YdgtF+h6hmm8BS8gX44Xf" + 
			"gMXpAbeGiwEHgI7Ey0tOyUyceRo6BrEOPx2e0AOI0x/EWoYzd9c3GplDUDCfWXLi" + 
			"SgFQfTKqcwn4Ot9RMQJBAOWMexRbT7ZjwEdarYbEm6jXLHMdJ2lOUr6M5QNnNqxI" + 
			"wKQ4kgHj5JMdFGToVOEajeUFdBcIg9eUOY7sUCfS3BcCQQDY31KNEyQ/lt8iskEA" + 
			"NrkhPO34OXEW0cbWzQSJB+DcFIccDGlFyYKUS3CV4++791jEMDZi1r2QkVuVWggs" + 
			"OYILAkBdZJKO7be41Uzdux7uvf826pTDIRFv3tX7gQeMIuoznkYCiqGc80OEN0ic" + 
			"5YnjLBKfI2DgHi7F/yCL7cmO6lNxAkEAiTDs1dWQDrHwQdClke8MOdyDZY1g3uCh" + 
			"emyDO/RB6LcLqVCon78T3Z/Dfu6MT0nvW1xsayYREQ0C9H3ZwbA3dQJAQXdX330V" + 
			"4xLKcJn0db2C74andP+/GSKRZbBcas235452s6oQeI+dfxhfKqFWXIBvC7zNsuiF" + 
			"CGqJoGeN0eYPgg==";
	private String zhimaPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCPq7VlsywILrdZDJnOWlShWBhq85NwQ0OvCjZiQS2td+gX3hnhBb+1xs53f32lvfu2lPu2keoOTPrpw0AoB+jhcV2H5Za8LI3LdjO9/LMuomtiA0+XYZaJ91Yu7D9fwOdjTt6pOQlz5E99XPE1a2vh07KM9GKkT2Z4Xil401kvewIDAQAB";

	public String verity(String params,String sign) {
		if(params.indexOf("%") != -1) {
	        try {
				params = URLDecoder.decode(params, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    if(sign.indexOf("%") != -1) {
	        try {
				sign = URLDecoder.decode(sign, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    try {
		    DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, appId, privateKey, zhimaPublicKey);
	        String result = client.decryptAndVerifySign(params, sign);
	        String temp[] = result.split("&");
	        String temp1[] = temp[temp.length - 1].split("=");
	        String success = temp1[1];
	        if(success == "true"){
	        	String temp2[] = temp[0].split("=");
	        	return temp2[1];
	        }else{
	        	return "failed";
	        }
	    } catch (ZhimaApiException e) {
	        e.printStackTrace();
	        return "failed";
	    }
	}

}