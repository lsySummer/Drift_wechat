package edu.nju.utils;

import java.util.HashMap;
import java.util.Map;

import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.APIConnectionException;
import com.pingplusplus.exception.APIException;
import com.pingplusplus.exception.AuthenticationException;
import com.pingplusplus.exception.ChannelException;
import com.pingplusplus.exception.InvalidRequestException;
import com.pingplusplus.exception.RateLimitException;
import com.pingplusplus.model.Charge;

public class Test {
	public static void main(String[] args) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException, ChannelException, RateLimitException {
//	    String result = "";
//	    try {
//	        result = URLEncoder.encode("http://www.baidu.com","utf-8");
//	        System.out.println(result);
//	    } catch (UnsupportedEncodingException e) {
//	        e.printStackTrace();
//	    }
		
//		List<Device> list = new ArrayList<Device>();
//		int num[]={3,2,5,4,7,2,7,3,1,10};
//		for(int i=0;i<10;i++){
//			Device d = new Device();
//			d.setQueueNum(num[i]);
//			list.add(d);
//		}
//		Utility.sortInt(list);
		Pingpp.apiKey = "sk_test_ibbTe5jLGCi5rzfH4OqPW9KC";
		Pingpp.privateKeyPath = "-----BEGIN RSA PRIVATE KEY-----"
				+ "MIICWwIBAAKBgQDFjAvBRxfZISOqq7kwElqe9JHSLhKLPwrs+9SwCkIkP0fdlFUIhSGy"
				+ "/IgOGcvgdpL4L+07+5vr3l1k+r/vbgzAPddXe9N99uds/ZDvbN8/mj/"
				+ "y2XoAorWWC+5XVUjp9umfL154tOM9Idj2eT7baJUoKlrtHPnD22VIg2jU84U5AQIDAQABAoGAZkopABCe8ahuPn6i6phmYi6Dn1CJ+55OOv3"
				+ "/WJRHQVufC8Y17a56D6MhlCOZZQYg/fBbKBcV2qGOvK32WrUiVjPQfmIM2yNsvh8WtAcGJ5nEtQJ5M4GmapzkWkz0iNDthNkJv+7HukGHyX4oxbMp6Cx/"
				+ "gQJHIWwcBmuLJbanAD0CQQDrFlhIr2pXD0Y3xkXcEsQT0o1WcLUdWMnA0zu7FycAYhaHIaM8N4pWY0rEQ17fra3d4326aONJwR23frm2Aql/"
				+ "AkEA1x7LoEK7voDdfE0KpEh5zkHklhzyZEStq66r2mqRgIyDsllyppPWRX70/"
				+ "ucDeMk3hIMaAWxE7iXfMgvRX2BdfwJAH/bu1Bs9206RXp4P+6d1j7huTVWP2siqzhQqp/qy6+Nqar9RIavvnGVt0hfS+5jy7huzibgfR3UBpGSPvcbI4wJAKh0PRBqMYqkbsx/"
				+ "p+2ts3SItO+4Czfpb/YY+go3mWkIn7hxww4ehpQ1LrTNQjK7nvXtk9e5aOD0VythhbsiQxQJAPJsOLFj5xFOTbI32akQNUnUxt5Z5HmvjeFarPOh/YfIIXhvM2sFTqaJtSiLV8oj67BP6c6RxUp2bkba6O/"
				+ "xjeA==-----END RSA PRIVATE KEY-----";
		Map<String, Object> chargeParams = new HashMap<String, Object>();
		  chargeParams.put("order_no",  "123456789");
		  chargeParams.put("amount", 100);//订单总金额, 人民币单位：分（如订单总金额为 1 元，此处请填 100）
		  Map<String, String> app = new HashMap<String, String>();
		  app.put("id", "app_bLCG44T04W5GHenf");
		  chargeParams.put("app", app);
		  chargeParams.put("channel",  "wx");
		  chargeParams.put("currency", "cny");
		  chargeParams.put("client_ip",  "127.0.0.1");
		  chargeParams.put("subject",  "Your Subject");
		  chargeParams.put("body",  "Your Body");
		  Charge.create(chargeParams);
	}
}
