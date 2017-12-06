package edu.nju.dao.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import edu.nju.dao.BaseDao;
import edu.nju.dao.CommunityDao;
import edu.nju.entities.Order;
import edu.nju.entities.UserComment;

@Repository
public class CommunityDaoImpl implements CommunityDao{
	private static final Logger log = Logger.getLogger(CommunityDaoImpl.class);
	 @Autowired
	 private BaseDao baseDao;
	 @Autowired
	 private ServletContext context;

	@SuppressWarnings("unchecked")
	@Override
	public boolean addComment(String openid,List<MultipartFile> files,String comment) {
		
		String hql = "from Order where openId =:openid";
		List<Order> list = baseDao.getNewSession().createQuery(hql).setParameter("openid", openid).getResultList();
		if(list.size()>0){
			Order o = list.get(list.size()-1);
			UserComment u = new UserComment();
			u.setComment(comment);
			u.setOpenid(openid);
			u.setOrderid(o.getId());
			String urls = saveFile(openid,files);
			u.setPicURLS(urls);
			baseDao.save(u);
			return true;
		}
		return false;
	}
	
	public String saveFile(String openid,List<MultipartFile> mfs){
//		String baseUrl="D:\\upload\\community\\";
		String baseUrl = "/home/airstaff/Server/apache-tomcat-8.0.33/upload/comment/"+openid+"/";
		System.out.println(baseUrl);
		log.info("上传图片地址"+baseUrl);
		Path path = Paths.get(baseUrl);
		if(Files.notExists(path)){
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String urllst="";
		log.info("mfssize"+mfs.size());
		for(int i=0;i<mfs.size();i++){
			MultipartFile mf = mfs.get(i);
			String fileName = mf.getOriginalFilename();
			if (!"".equals(fileName)) {
				// 获取后缀
				String suffix = fileName.substring(fileName.indexOf("."),
						fileName.length());
				String newName=i+suffix;
				String url = baseUrl + newName;
				urllst = urllst+newName+";";
				try {
					mf.transferTo(new File(url));
				}catch(Exception e){
					e.printStackTrace();
					log.error(e.getMessage());
				}
			}
		}
		return urllst;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserComment getComment(String openid) {
		String hql = "from UserComment where openid =:openid";
		List<UserComment> list = baseDao.getNewSession().createQuery(hql).setParameter("openid", openid).getResultList();
		if(list.size()>0){
			UserComment uc = list.get(list.size()-1);
			return uc;
		}
		return null;
	}

}
