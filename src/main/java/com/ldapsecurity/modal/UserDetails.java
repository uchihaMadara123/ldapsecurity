package com.ldapsecurity.modal;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDetails {
	@JsonProperty("userId")
	private String uID;
	@JsonProperty("user_name")
	private String userName;
	@JsonProperty("role")
	private List<String> role;

	public String getuID() {
		return uID;
	}

	public void setuID(String uID) {
		this.uID = uID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<String> getRole() {
		return role;
	}

	public void setRole(List<String> role) {
		this.role = role;
	}

	

}
