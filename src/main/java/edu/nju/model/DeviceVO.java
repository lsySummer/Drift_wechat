package edu.nju.model;

import java.util.List;

public class DeviceVO {
	private String id;
	private String number;//设备编号
	private String loc;//目前所在位置
//	private String deliveryId;//目前快递单号
	private int queueNum;//当前排队人数
	private List<String> area;
	private int type;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public int getQueueNum() {
		return queueNum;
	}
	public void setQueueNum(int queueNum) {
		this.queueNum = queueNum;
	}
	public List<String> getArea() {
		return area;
	}
	public void setArea(List<String> area) {
		this.area = area;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public DeviceVO(String id, String number, String loc, int queueNum, List<String> area, int type) {
		super();
		this.id = id;
		this.number = number;
		this.loc = loc;
		this.queueNum = queueNum;
		this.area = area;
		this.type = type;
	}	
	
	
}
