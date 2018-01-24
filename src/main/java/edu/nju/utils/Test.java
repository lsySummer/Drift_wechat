package edu.nju.utils;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.multipart.MultipartFile;

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
//		HandleFile.testParse("<p><img src=\"blob:http://drift.gmair.net/3ba24568-5df8-4ccc-bb65-a1dd2f8e44fb\" id=\"0\" style=\"width: 375px;\">打Call！</p><p><br></p>");
		Document doc = Jsoup.parse("<p><br></p>  <hr>  <p><img style=\"width: 375px;\" src=\"/upload/7JwuPwMJLo_2018-01-20_17:47/temp/E8D1A724-FA96-471F-B498-EAE11D3DE42F.png\" id=\"2\"><img style=\"width: 375px;\" src=\"/upload/7JwuPwMJLo_2018-01-20_17:47/temp/B44B31D5-0E5B-4F1B-9140-360CE2053093.png\" id=\"1\">123</p>  <p><br></p>");
		Elements links = doc.select("img"); 
		for (int i=0;i<links.size();i++) { 
			  Element link = links.get(i);
			  link.attr("src", "test"+i);
			}
		System.out.println(doc.select("p").toString());
	}
}
