package edu.nju.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "order_list")
public class Order {
	@Override
	public String toString() {
		return "Order [id=" + id + ", openId=" + openId + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", deviceNumber=" + deviceNumber + ", deviceId=" + deviceId + ", state=" + state + ", num=" + num
				+ ", ifPay=" + ifPay + "]";
	}
	private String id;
	private String openId;//用戶openid
	private Date startDate;
	private Date endDate;
	private String deviceNumber;//甲醛仪设备编号
	private String deviceId;
	private String state;//等待发货 上家已发货 已确认收货 已寄出 下家已收货
	private int num;//购买耗材数量
	private int ifPay;//是否是付费用户,0代表免费用户，1代表付费用户
	private Date createDate;
//	private String afterOpenid;//下家openid
	
	public Order(){
	}
	
	public Order(String openId,Date startDate,Date endDate,String deviceId,String deviceNumber,
			String state,int num,int ifPay,Date createDate){
		this.openId = openId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.deviceId = deviceId;
		this.deviceNumber = deviceNumber;
		this.state=state;
		this.num = num;
		this.ifPay = ifPay;
		this.createDate = createDate;
	}
	
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
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
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
	public String getDeviceNumber() {
		return deviceNumber;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getIfPay() {
		return ifPay;
	}
	public void setIfPay(int ifPay) {
		this.ifPay = ifPay;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

//	public String getAfterOpenid() {
//		return afterOpenid;
//	}
//
//	public void setAfterOpenid(String afterOpenid) {
//		this.afterOpenid = afterOpenid;
//	}

	
}
