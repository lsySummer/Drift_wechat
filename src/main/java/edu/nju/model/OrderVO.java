package edu.nju.model;

import java.util.Date;

public class OrderVO {
	private String id;
	private Date startDate;
	private Date endDate;
	private String deviceNumber;//甲醛仪设备编号
	private String name;
	private String phone;
	private String address;
	public OrderVO(String id, Date startDate, Date endDate, String deviceNumber, String name, String phone,
			String address) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.deviceNumber = deviceNumber;
		this.name = name;
		this.phone = phone;
		this.address = address;
	}
	
}
