package edu.nju.model;

import java.util.Date;

public class UserVO {
	private String openId;
	private String nickname;
	private String address;
	private double longtitute;
	private double latitute;
	private Date startDate;
	private String deviceNumber;
	public UserVO(String openId, String nickname, String address, double longtitute, double latitute, Date startDate,
			String deviceNumber) {
		super();
		this.openId = openId;
		this.nickname = nickname;
		this.address = address;
		this.longtitute = longtitute;
		this.latitute = latitute;
		this.startDate = startDate;
		this.deviceNumber = deviceNumber;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getLongtitute() {
		return longtitute;
	}
	public void setLongtitute(double longtitute) {
		this.longtitute = longtitute;
	}
	public double getLatitute() {
		return latitute;
	}
	public void setLatitute(double latitute) {
		this.latitute = latitute;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getDeviceNumber() {
		return deviceNumber;
	}
	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}
	
}
