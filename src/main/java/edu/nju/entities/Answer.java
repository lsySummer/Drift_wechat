package edu.nju.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "answer")
public class Answer {
	private String id;
	private String qid;//问题id
	private String openid;//回答者id
	private String content;
	private Date createTime;
	private String picSig;
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
	public String getQid() {
		return qid;
	}
	public void setQid(String qid) {
		this.qid = qid;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getPicSig() {
		return picSig;
	}
	public void setPicSig(String picSig) {
		this.picSig = picSig;
	}
	@Override
	public String toString() {
		return "Answer [id=" + id + ", qid=" + qid + ", openid=" + openid + ", content=" + content + ", createTime="
				+ createTime + "]";
	}
	
}
