package com.hexaware.easypay.dto;



public class AuthRequest {
	private String userName;
	private String password;
	public String getUserName() {
		return userName;
	}
	
	public AuthRequest(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "AuthRequest [userName=" + userName + ", password=" + password + "]";
	}
	
	

}