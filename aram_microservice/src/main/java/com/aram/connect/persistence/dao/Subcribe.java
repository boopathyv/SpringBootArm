package com.aram.connect.persistence.dao;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="subscribe")
@NamedQuery(name="Subcribe.findUsingEmailId" , query="Select s from Subcribe s where emailId = :emailId")
public class Subcribe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="subscribe_id")
	private Long subscribeId;

	@Column(name="email_id")
	private String emailId;
	
	@Column(name="subscribed_date")
	private Timestamp subscribedDate;
	
	@Column(name="active_flag")
	private String activeFlag;

	public Long getSubscribeId() {
		return subscribeId;
	}

	public void setSubscribeId(Long subscribeId) {
		this.subscribeId = subscribeId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Timestamp getSubscribedDate() {
		return subscribedDate;
	}

	public void setSubscribedDate(Timestamp subscribedDate) {
		this.subscribedDate = subscribedDate;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	
	
	
	
}
