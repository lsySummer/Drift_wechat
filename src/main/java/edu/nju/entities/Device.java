package edu.nju.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "device")
public class Device {
	private String id;
	private String number;//设备编号
	private String loc;//目前所在位置
//	private String deliveryId;//目前快递单号
	private int queueNum;//当前排队人数
	
	@Id
	@Column(length = 32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
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
	public int getQueueNum() {
		return queueNum;
	}
	public void setQueueNum(int queueNum) {
		this.queueNum = queueNum;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
//	public String getDeliveryId() {
//		return deliveryId;
//	}
//	public void setDeliveryId(String deliveryId) {
//		this.deliveryId = deliveryId;
//	}
	
	
}
