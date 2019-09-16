package com.sist.dao;

import java.util.*;

public class MyGameVO {

	private String id;
	private String regdate;
	private String matchDate;
	private String homeTeam;
	private String awayTeam;
	private String expectResult;
	private String divRate;
	private String bettingmoney;
	private String matchResult;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getBettingmoney() {
		return bettingmoney;
	}
	public void setBettingmoney(String bettingmoney) {
		this.bettingmoney = bettingmoney;
	}
	
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getMatchDate() {
		return matchDate;
	}
	public void setMatchDate(String matchDate) {
		this.matchDate = matchDate;
	}
	public String getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}
	public String getAwayTeam() {
		return awayTeam;
	}
	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}
	public String getExpectResult() {
		return expectResult;
	}
	public void setExpectResult(String expectResult) {
		this.expectResult = expectResult;
	}
	public String getDivRate() {
		return divRate;
	}
	public void setDivRate(String divRate) {
		this.divRate = divRate;
	}
	public String getMatchResult() {
		return matchResult;
	}
	public void setMatchResult(String matchResult) {
		this.matchResult = matchResult;
	}
	
}
