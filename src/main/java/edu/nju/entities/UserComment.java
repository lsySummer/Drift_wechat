package edu.nju.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author lsy
 * 用户评论
 */
@Entity
@Table(name = "user_comment")
public class UserComment {
	private String id;
	private String openid;
	private String orderid;
	private String comment;
	private String picURLS;//split by
//	private float num;
	
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
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getPicURLS() {
		return picURLS;
	}
	public void setPicURLS(String picURLS) {
		this.picURLS = picURLS;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
//	public float getNum() {
//		return num;
//	}
//	public void setNum(float num) {
//		this.num = num;
//	}
	@Override
	public String toString() {
		return "UserComment [id=" + id + ", openid=" + openid + ", orderid=" + orderid + ", comment=" + comment
				+ ", picURLS=" + picURLS + "]";
	}
	
}
