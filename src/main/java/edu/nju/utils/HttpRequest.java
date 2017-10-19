package edu.nju.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.log4j.Logger;
import org.json.JSONObject;


/**
 * @author javebean
 */
public class HttpRequest {
	
	
	//每个用户下载图片放在单独的userid文件夹中
	public static String userId;
	
	private static Logger log = Logger.getLogger(HttpRequest.class);
	
	//初始化 httpclient
	private static CloseableHttpClient getHttpclient(){
		CloseableHttpClient httpclient =null;
		SSLContext sslContext = null;
		try {
			sslContext = SSLContexts.custom().build();
		} catch (KeyManagementException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
		
		SSLConnectionSocketFactory sf = new SSLConnectionSocketFactory(sslContext,new String[] {"TLSv1", "TLSv1.1", "TLSv1.2"},
		        null,new HostnameVerifier(){
					@Override
					public boolean verify(String hostname, SSLSession session) {
						return true;
					}
		        	
		        });
		httpclient = HttpClients.custom().setSSLSocketFactory(sf).build();
		return httpclient;
	}
	
	
	/*设置超时*/
	private static RequestConfig setTimeOut(){
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectionRequestTimeout(10000)
				.setConnectTimeout(10000)
				.setSocketTimeout(10000)
				.build();
		
		return requestConfig;
	}
	
	
	public static String postCall(String interfaceName,HttpEntity entity,Map<String,Object> head){
		log.info("调用远程接口， 接口名："+interfaceName);
		
		CloseableHttpClient httpclient =getHttpclient();
		CloseableHttpResponse response = null;
		HttpPost httpPost = new HttpPost(interfaceName);
		if(entity!=null){
			httpPost.setEntity(entity);
		}
		//设置请求头
		if(head!=null){
			for(Map.Entry<String, Object> h :head.entrySet()){
				httpPost.setHeader(h.getKey(), h.getValue().toString());
			}
		}
		try {
			response = httpclient.execute(httpPost);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String outResult = getOutResult(response);
		return outResult;
		
	}
	
	/**
	 * 处理输出结果，如果是图片单独处理
	 */
	private static String getOutResult(CloseableHttpResponse response){
		StringBuilder out = new StringBuilder();
		Header firstHeader = response.getFirstHeader("Content-Type");
		List<String> arr = new ArrayList<String>(4);
		arr.add("image/bmp");
		arr.add("image/gif");
		arr.add("image/jpeg");
		arr.add("image/png");
		
		
		try{
			InputStream body = response.getEntity().getContent();
			
			//如果是图片
			if(arr.contains(firstHeader.getValue())){
				/*disposition  --》attachment; filename="wqTrmcVTWn3N-OSixFlHb56LxUrkcFe7L2Hzt3KZLoIIiSPkF-67BZy2mu
						ksN14u.jpg"
						*/	
				String disposition = response.getFirstHeader("Content-disposition").getValue();
				String filename = disposition.substring(22, disposition.length()-1);
				saveMediaImg(body,filename);
				return filename;
			}
			
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(body,"utf-8"));
			String line;
			while ((line = reader.readLine()) != null) {
				out.append(line);
			}
			body.close();
			reader.close();
		}catch(IOException  e){
			e.printStackTrace();
		}
		
		return out.toString();	
	}
	
	

	/**
	 * 请求头里面是图片的单独处理
	 */
	//http://stackoverflow.com/questions/43157/easy-way-to-write-contents-of-a-java-inputstream-to-an-outputstream
	private static void saveMediaImg(InputStream input,String filename){
		Path target = Paths.get(filename);
		/**
		 * 每个用户一个单独的文件夹
		 */
		
		try {
			if(Files.notExists(target)){
				Files.createDirectories(target);
			}
			Files.copy(input, target, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static String getCall(String interfaceName,Map<String,Object> param,Map<String,Object> head){
		CloseableHttpClient httpclient =getHttpclient();
		
		CloseableHttpResponse response = null;
		if(param!=null){
			String paramString  ="";
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for(Map.Entry<String, Object> map : param.entrySet()){
				nvps.add(new BasicNameValuePair(map.getKey(), map.getValue().toString()));
			}
			paramString = URLEncodedUtils.format(nvps, "utf-8");
			interfaceName = interfaceName+"?"+paramString;
		}
		HttpGet httpGet = new HttpGet(interfaceName);
		httpGet.setConfig(setTimeOut());
		
		//设置请求头
		if(head!=null){
			for(Map.Entry<String, Object> h :head.entrySet()){
				httpGet.setHeader(h.getKey(), h.getValue().toString());
			}
		}
		try {
			response = httpclient.execute(httpGet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info("发起get请求 请求路径："+interfaceName);

		String outResult = getOutResult(response);
		return outResult;
		
	}
	
	
	
	
	/**
	 * 在post请求中，需要提交参数，参数 可以为map jsonobject 以及str
	 * @param param
	 * @return
	 */
	public static HttpEntity getEntity(Map<String,Object> param){
		HttpEntity entity = null;
		if(param!=null && !param.isEmpty()){
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for(Map.Entry<String, Object> map : param.entrySet()){
				nvps.add(new BasicNameValuePair(map.getKey(), map.getValue().toString()));
			}
			try {
				entity =  new UrlEncodedFormEntity(nvps, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		return entity;
	}
	
	
	/**
	 * 在post请求中，需要提交参数，参数 可以为map jsonobject 以及str
	 * @param param
	 * @return
	 */
	
	public static HttpEntity getEntity(JSONObject obj){
		StringEntity entity = null;
		if(obj!=null){
			 entity = new StringEntity(obj.toString(),"utf-8"); //解决中文乱码问题    
		}
		return entity;
	}
	
	/**
	 * 在post请求中，需要提交参数，参数 可以为map jsonobject 以及str
	 * @param param
	 * @return
	 */
	public static HttpEntity getEntity(String str){
		StringEntity entity = null;
		if(str!=null){
			entity = new StringEntity(str,"utf-8"); //解决中文乱码问题    
		}
		return entity;
	}
	
	  public static String getIpAddress(HttpServletRequest request) { 
		    String ip = request.getHeader("x-forwarded-for"); 
		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		      ip = request.getHeader("Proxy-Client-IP"); 
		    } 
		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		      ip = request.getHeader("WL-Proxy-Client-IP"); 
		    } 
		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		      ip = request.getHeader("HTTP_CLIENT_IP"); 
		    } 
		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		      ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
		    } 
		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		      ip = request.getRemoteAddr(); 
		    } 
		    return ip; 
		  } 
	
}
