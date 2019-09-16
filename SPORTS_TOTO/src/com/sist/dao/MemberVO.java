package com.sist.dao;

import java.util.*;

public class MemberVO {
	private int permission_level;
	private String id;
	private String pwd;
	private String name;
	private String gender;
	private String birthday;
	private String address;
	private String phone;
	private String email;
	private Date regdate;
	private String gamemoney;
	
	public String getGamemoney() {
		return gamemoney;
	}
	public void setGamemoney(String gamemoney) {
		this.gamemoney = gamemoney;
	}
	public int getPermission_level() {
		return permission_level;
	}
	public void setPermission_level(int permission_level) {
		this.permission_level = permission_level;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	
}
