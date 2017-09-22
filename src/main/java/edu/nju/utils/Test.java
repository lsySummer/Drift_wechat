package edu.nju.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Test {
	public static void main(String[] args) {
	    String result = "";
	    try {
	        result = URLEncoder.encode("http://106.14.172.12:8070/Drift_wechat/api/wechat/center/","utf-8");
	        System.out.println(result);
	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    }
	}
}
