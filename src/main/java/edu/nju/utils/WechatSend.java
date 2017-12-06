package edu.nju.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class WechatSend {
	
	private static Logger log = Logger.getLogger(WechatConfig.class);
	/**
     * @method packJsonmsg
     * @描述: TODO(封装微信模板:订单支付成功) 
     * @参数@param cname  客户姓名
     * @参数@param cphone  客户电话
     * @参数@param rtime  预约时间
     * @参数@param result  预约结果
     * @参数@return
     * @返回类型：JSONObject
     * @添加时间 2016-1-5下午03:38:54
     * @作者：***
     */
    public static JSONObject packJsonmsg(String deviceNumber, Date startTime,Date endTime){
        JSONObject json = new JSONObject();
        JSONObject jsonFirst = new JSONObject();
		jsonFirst.put("value", "订单修改通知");
		jsonFirst.put("color", "#173177");
		json.put("first", jsonFirst);
		
		JSONObject jsonName = new JSONObject();
		jsonName.put("value", deviceNumber);
		jsonName.put("color", "#173177");
		json.put("keyword1", jsonName);
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");  
		String str=sdf.format(startTime);  
		String str2 = sdf.format(endTime);
		
		JSONObject jsonPhone = new JSONObject();
		jsonPhone.put("value", str);
		jsonPhone.put("color", "#173177");
		json.put("keyword2", jsonPhone);
		
		JSONObject jsonTime = new JSONObject();
		jsonTime.put("value", str2);
		jsonTime.put("color", "#173177");
		json.put("keyword3", jsonTime);
		
		JSONObject jsonRemark = new JSONObject();
		jsonRemark.put("value", "点击查看订单详情");
		jsonRemark.put("color", "#173177");
		json.put("remark", jsonRemark);
        return json;
    }
    
    /**
     * @method sendWechatmsgToUser
     * @描述: TODO(发送模板信息给用户) 
     * @参数@param touser  用户的openid
     * @参数@param templat_id  信息模板id
     * @参数@param url  用户点击详情时跳转的url
     * @参数@param topcolor  模板字体的颜色
     * @参数@param data  模板详情变量 Json格式
     * @参数@return
     * @返回类型：String
     * @添加时间 2016-1-5上午10:38:45
     * @作者：***
     */
    public static String sendWechatmsgToUser(String touser, String templat_id, String clickurl, String topcolor, JSONObject data){
        String tmpurl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
        JSONObject accessToken = WechatConfig.getAccessTokenForInteface();
		String token = accessToken.getString("access_token");
        String url = tmpurl.replace("ACCESS_TOKEN", token);
        JSONObject json = new JSONObject();
        json.put("touser", touser);
		json.put("template_id", templat_id);
		json.put("url", clickurl);
		json.put("topcolor", topcolor);
		json.put("data", data);
        String result = httpsRequest(url, "POST", json.toString());
        try {
            JSONObject resultJson = new JSONObject(result);
            log.info("发送消息结果"+resultJson.toString());
            String errmsg = (String) resultJson.get("errmsg");
            if(!"ok".equals(errmsg)){  //如果为errmsg为ok，则代表发送成功，公众号推送信息给用户了。
                return "error";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "success";
    }
    
    public static String httpsRequest(String requestUrl, String requestMethod, String outputStr){
        try {
            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            // 当outputStr不为null时向输出流写数据
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            // 从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            return buffer.toString();
        } catch (ConnectException ce) {
           log.error("连接超时：{}");
        } catch (Exception e) {
        	log.error("https请求异常：{}");
        }
        return null;
    }
}
