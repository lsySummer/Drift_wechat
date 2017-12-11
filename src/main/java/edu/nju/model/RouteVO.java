package edu.nju.model;

import java.util.Date;

public class RouteVO {
	private Date startDate;
	private Date endDate;
	private String deviceId;
	private String deviceNumber;
	private String address;
	public RouteVO(Date startDate, Date endDate, String deviceId, String deviceNumber, String address) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.deviceId = deviceId;
		this.deviceNumber = deviceNumber;
		this.address = address;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getDeviceNumber() {
		return deviceNumber;
	}
	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
