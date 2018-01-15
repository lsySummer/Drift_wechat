package edu.nju.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.multipart.MultipartFile;

public class HandleFile {
	
	private static final Logger log = Logger.getLogger(Utility.class);
	public static boolean deleteFile(String filepath) {
    	String absUrl = (new File("")).getAbsolutePath();
		String baseUrl = absUrl.substring(0,absUrl.length()-3)+"/webapps/upload/"+filepath;
		System.out.println(baseUrl);
		log.info("删除图片地址"+baseUrl);
		Path path = Paths.get(baseUrl);
		if(Files.exists(path)){
			try {
				Files.delete(path);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
    }

	public static boolean changenName(String path) {
		String[] list = path.split("/");
		String filePath = "";
		for(int i=0;i<list.length-1;i++) {
			filePath = filePath+list[i]+"/";
		}
		filePath = filePath + "img/";
 		String absUrl = (new File("")).getAbsolutePath();
 		log.info("absUrl"+absUrl);
		String baseUrl = absUrl.substring(0,absUrl.length()-3)+"/webapps/upload/"+path;
		String newUrl = absUrl.substring(0,absUrl.length()-3)+"/webapps/upload/"+filePath;
		System.out.println(baseUrl);
		log.info("修改名字图片地址"+baseUrl);
		File file = new File(baseUrl);
		try {
			file.renameTo(new File(newUrl));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
    }

	public static boolean makeFolder(String filepath) {
		String absUrl = (new File("")).getAbsolutePath();
		String baseUrl = absUrl.substring(0,absUrl.length()-3)+"/webapps/upload/"+filepath;
		System.out.println(baseUrl);
		log.info("创建文件夹地址"+baseUrl);
		Path path = Paths.get(baseUrl);
		if(Files.notExists(path)){
			try {
				Files.createDirectories(path);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	/**
	 * 保存文件。先读取content的html再解析，将其中的img src更改为文件保存的位置，然后保存文件，返回更新后的html
	 */
	public static String[] saveFile(String content,List<MultipartFile> fileList,String filepath) {
		String[] result = new String[2];
		if(fileList.size()==0) {
			result[0] = content;
			result[1] = "";
			return result;
		}
		String baseUrl = "/upload/"+filepath;
		Document doc = Jsoup.parse(content);
		Elements links = doc.select("img"); 
		Utility.saveFile(filepath, fileList);
		for (int i=0;i<links.size();i++) { 
		  Element link = links.get(i);
		  MultipartFile mf = fileList.get(i);
		  String fileName = mf.getOriginalFilename();
		  link.attr("src", baseUrl+fileName);
		}
		result[0] = doc.html().replaceAll("\n", "");
		System.out.println(result[0]);
		result[1] = baseUrl + fileList.get(0).getOriginalFilename();
		return result;
	}
	
	public static String deleteImg(String content) {
		Document doc = Jsoup.parse(content);
		Elements links = doc.select("img"); 
		links.remove();
		return doc.toString();
	}
}
