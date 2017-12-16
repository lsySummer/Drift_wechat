package edu.nju.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import edu.nju.entities.Device;

public class Utility {
	@SuppressWarnings({ "unchecked", "rawtypes" })  
	public static void sortInt(List list){  
	    Collections.sort(list, new Comparator(){  
	        @Override  
	        public int compare(Object o1, Object o2) {  
	        	Device d1=(Device)o1;  
	        	Device d2=(Device)o2;  
	            if(d1.getQueueNum()>d2.getQueueNum()){  
	                return 1;  
	            }else if(d1.getQueueNum()==d2.getQueueNum()){  
	                return 0;  
	            }else{  
	                return -1;  
	            }  
	        }             
	    });  
	} 
	
	public static Date getSpecifiedDayAfter(Date date,int i){ 
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		int day=c.get(Calendar.DATE); 
		c.set(Calendar.DATE,day+i); 
		Date resultDate = c.getTime();
		return resultDate; 
		} 
	
	/**
     * date2比date1多的天数
     * @param date1    
     * @param date2
     * @return    
     */
    public static int differentDays(Date date1,Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
       int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
        
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2)   //同一年
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0)    //闰年            
                {
                    timeDistance += 366;
                }
                else    //不是闰年
                {
                    timeDistance += 365;
                }
            }
            
            return timeDistance + (day2-day1) ;
        }
        else    //不同年
        {
            System.out.println("判断day2 - day1 : " + (day2-day1));
            return day2-day1;
        }
    }
    
	
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
    
    public static long getDaysBetween(Date startDate,Date endDate){
    	return (long)((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 *24) + 0.5);
    }
}
