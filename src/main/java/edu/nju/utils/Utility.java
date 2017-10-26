package edu.nju.utils;

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
}
