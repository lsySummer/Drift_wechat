package edu.nju.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
 
public class HttpXmlClient {
 
    public static String post(String url, Map<String, String> params) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        String body = null;
        HttpPost post = postForm(url, params);
        body = invoke(httpclient, post);
        httpclient.getConnectionManager().shutdown();
        return body;
    }
 
    public static String get(String url) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        String body = null;
        HttpGet get = new HttpGet(url);
        body = invoke(httpclient, get);
        httpclient.getConnectionManager().shutdown();
        return body;
    }
 
    private static String invoke(DefaultHttpClient httpclient,
            HttpUriRequest httpost) {
        HttpResponse response = sendRequest(httpclient, httpost);
        String body = paseResponse(response);
        return body;
    }
 
    private static String paseResponse(HttpResponse response) {
        HttpEntity entity = response.getEntity();
        String charset = EntityUtils.getContentCharSet(entity);
        String body = null;
        try {
            body = EntityUtils.toString(entity);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return body;
    }
 
    private static HttpResponse sendRequest(DefaultHttpClient httpclient,
            HttpUriRequest httpost) {
        HttpResponse response = null;
        try {
            response = httpclient.execute(httpost);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
 
    private static HttpPost postForm(String url, Map<String, String> params) {
 
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
 
        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            nvps.add(new BasicNameValuePair(key, params.get(key)));
        }
 
        try {
            httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
 
        return httpost;
    }
 
    public static void main(String[] args) {
 
        //获取access_token
        Map<String, String> params = new HashMap<String, String>();
        params.put("corpid","wx5f24fa0db1819ea2");
        params.put("corpsecret","uQtWzF0bQtl2KRHX0amekjpq8L0aO96LSpSNfctOBLRbuYPO4DUBhMn0_v2jHS-9");
        String xml = HttpXmlClient.post("https://qyapi.weixin.qq.com/cgi-bin/gettoken",params);
        JSONObject jsonMap  = JSONObject.fromObject(xml);
        Map<String, String> map = new HashMap<String, String>();
        Iterator<String> it = jsonMap.keys();  
        while(it.hasNext()) {  
            String key = (String) it.next();  
            String u = jsonMap.get(key).toString();
            map.put(key, u);  
        }
        String access_token = map.get("access_token");
        System.out.println("access_token=" + access_token);
 
        //获取ticket
        params.put("access_token",access_token);
        xml = HttpXmlClient.post("https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket",params); 
        jsonMap  = JSONObject.fromObject(xml);
        map = new HashMap<String, String>();
        it = jsonMap.keys();  
        while(it.hasNext()) {  
            String key = (String) it.next();  
            String u = jsonMap.get(key).toString();
            map.put(key, u);  
        }
        String jsapi_ticket = map.get("ticket");
        System.out.println("jsapi_ticket=" + jsapi_ticket);
 
        //获取签名signature
        String noncestr = UUID.randomUUID().toString();
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        String url="http://mp.weixin.qq.com";
        String str = "jsapi_ticket=" + jsapi_ticket +
                "&noncestr=" + noncestr +
                "&timestamp=" + timestamp +
                "&url=" + url;
        //sha1加密
        String signature = SHA1(str);
        System.out.println("noncestr=" + noncestr);
        System.out.println("timestamp=" + timestamp);
        System.out.println("signature=" + signature);
        //最终获得调用微信js接口验证需要的三个参数noncestr、timestamp、signature
    }
 
       /** 
     * @author：罗国辉 
     * @date： 2015年12月17日 上午9:24:43 
     * @description： SHA、SHA1加密
     * @parameter：   str：待加密字符串
     * @return：  加密串
    **/
    public static String SHA1(String str) {
        try {
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("SHA-1"); //如果是SHA加密只需要将"SHA-1"改成"SHA"即可
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexStr = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexStr.append(0);
                }
                hexStr.append(shaHex);
            }
            return hexStr.toString();
 
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}