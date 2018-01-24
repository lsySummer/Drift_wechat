package edu.nju.model;

import java.util.Date;

public class UserVO {
	private String openId;
	private String nickname;
	private String address;
	private Date startDate;
	private String deviceNumber;
	private int deviceState;
	private double jqNum;
	public UserVO(String openId, String nickname, String address,  Date startDate,
			String deviceNumber,int deviceState,double jqNum) {
		super();
		this.openId = openId;
		this.nickname = nickname;
		this.address = address;
		this.startDate = startDate;
		this.deviceNumber = deviceNumber;
		this.deviceState = deviceState;
		this.jqNum = jqNum;
	}
	public double getJqNum() {
		return jqNum;
	}
	public void setJqNum(double jqNum) {
		this.jqNum = jqNum;
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
	public int getDeviceState() {
		return deviceState;
	}
	public void setDeviceState(int deviceState) {
		this.deviceState = deviceState;
	}
	@Override
	public String toString() {
		return "UserVO [openId=" + openId + ", nickname=" + nickname + ", address=" + address + ", startDate="
				+ startDate + ", deviceNumber=" + deviceNumber + ", deviceState=" + deviceState + ", jqNum=" + jqNum
				+ "]";
	}
	
}
