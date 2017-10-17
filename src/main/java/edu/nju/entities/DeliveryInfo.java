package edu.nju.entities;

import java.util.Date;
import java.util.List;

/**
 * @author lsy
 * 快递信息
 */
public class DeliveryInfo {
	private String del_id;
	private List<Date> time;
	private List<String> info;
	public String getDel_id() {
		return del_id;
	}
	public void setDel_id(String del_id) {
		this.del_id = del_id;
	}
	public List<Date> getTime() {
		return time;
	}
	public void setTime(List<Date> time) {
		this.time = time;
	}
	public List<String> getInfo() {
		return info;
	}
	public void setInfo(List<String> info) {
		this.info = info;
	}
	
}
