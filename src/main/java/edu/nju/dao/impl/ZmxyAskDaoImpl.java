package edu.nju.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.antgroup.zmxy.openplatform.api.DefaultZhimaClient;
import com.antgroup.zmxy.openplatform.api.ZhimaApiException;
import com.antgroup.zmxy.openplatform.api.request.ZhimaAuthInfoAuthorizeRequest;

import edu.nju.dao.BaseDao;
import edu.nju.dao.ZmxyAskDao;
import edu.nju.entities.Test;

@Transactional
@Repository("zmxyAskDao")
public class ZmxyAskDaoImpl implements ZmxyAskDao{
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
	
	@Override
	public String AuthInfo(String name,String phone) {
		// TODO Auto-generated method stub
		ZhimaAuthInfoAuthorizeRequest req = new ZhimaAuthInfoAuthorizeRequest();
        req.setChannel("apppc");
        req.setPlatform("zmop");
        req.setIdentityType("1");
        req.setIdentityParam("{\"name\":\"" + name + "\",\"mobileNo\":\"" + phone +"\"}");
        req.setBizParams("{\"auth_code\":\"M_APPSDK\",\"channelType\":\"appsdk\",\"state\":\"商户自定义\"}"); 
        DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, appId, privateKey, zhimaPublicKey);
        try {
            String url = client.generatePageRedirectInvokeUrl(req);
            System.out.println(url);
            return url;
        } catch (ZhimaApiException e) {
            e.printStackTrace();
            return "failed";
        }
//		return "https://zmopenapi.zmxy.com.cn/openapi.do?charset=UTF-8&method=zhima.auth.info.authorize&channel=apppc&sign=MK4ujfBP3syUx51DcA50V1UyFrCrxHXs%2BEqCOuI5KueXeV%2FF78ntEIIAVTRfOaeTndhcEOQbH7hkxGEGmT9gkt0ZcXDU0MrJpEu%2FYwva%2BfUjqSJsXug70QGSg1Y9yOcp%2FkGl13QYIheuElK7QfOtG%2BgOE0j9hDIY2Kbnu9Yq%2BP8%3D&version=1.0&app_id=300000028&sign_type=RSA&platform=zmop&params=SvjbVX8hnK%2BU%2BVJufK3zX4rwBQM7YbY7sbLeHuK87M%2BKUPw1IUuDHV7puS%2BnpXlV0qziM5J1vrNFkO3pghdtt56Kb1d1aMK%2BiGUmJJcm39W6yw9Y61Ak8FUzef8E2nP%2BWJ2kCM14uzZgPwHv9Bdw7%2Fp8szIuAUTbFzYR6b7i2oUuh8fPpgA2u4u%2FIryIxh0MaJqkPIpZ52yg0uluNfjxXBOSNaxBbpPZ1QnxtzTKb8JYkMif0y%2FWMFqbt4x2RRrmszyfE%2FpkwuojDltF%2B3rR4BYQK1SWBbn2VBA9p0mJsVsbAgHgdkDZKK2Tp0AHHoWtmbU9xaK8Vu6Sr85nIJJsY3prcouTy0T%2Bjs8Zz3u0jgnzWagfLLh2KCAfEm3L3H0PuE526HiUV7iSjG3pt%2F%2BygHOyIIwifEW9D7SH9lXH6e9CWKI24mGIyTVlPHqfvptl4LVILlc3TJual886fFI66fLO9N3Do475FO7Uwx3D767ZGbdv%2FE2tG0cBipys34mL";
	}
}
