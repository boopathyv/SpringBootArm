package com.aram.connect.persistence.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name="staffs")
public class Staff {
	
	@Id
	@Column(name="staff_id")
	@NotNull(message="Staff Id can't be Null")
	private int staffId;

	@Column(name="staff_name")
	private String staffName;
	
	@Column(name="stream")
	private String stream;
	
	@Column(name="email_id")
	@Email(message="Email Id is invalid")
	private String emailId;
	
	@Column(name="mobile_number")
	private String mobileNumber;

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
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
	
	

}
