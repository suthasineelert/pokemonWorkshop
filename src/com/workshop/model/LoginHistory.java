package com.workshop.model;

public class LoginHistory {
	private int id;
	private int userId;
	private String loginDateTime;
	private String logoutDateTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getLoginDateTime() {
		return loginDateTime;
	}
	public void setLoginDateTime(String loginDateTime) {
		this.loginDateTime = loginDateTime;
	}
	public String getLogoutDateTime() {
		return logoutDateTime;
	}
	public void setLogoutDateTime(String logoutDateTime) {
		this.logoutDateTime = logoutDateTime;
	}
	
}
