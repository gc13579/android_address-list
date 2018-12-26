package com.example.enity;

import java.io.Serializable;

public class Linkman implements Serializable {
	private Integer id;
	private String name;
	private String phone;
	private String QQ;
	public String getQQ() {
		return QQ;
	}
	public void setQQ(String QQ) {
		this.QQ = QQ;
	}
	public Linkman() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	

}
