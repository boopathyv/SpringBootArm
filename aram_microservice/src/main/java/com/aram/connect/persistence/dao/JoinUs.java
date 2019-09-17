package com.aram.connect.persistence.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="join_us")
public class JoinUs {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="join_us_id")
	private Long joinUsId;

	@Column(name="email_id")
	private String emailId;
	
	@Column(name="mobile_number")
	private String mobileNumber;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="message")
	private String message;

	public Long getJoinUsId() {
		return joinUsId;
	}

	public void setJoinUsId(Long joinUsId) {
		this.joinUsId = joinUsId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
