package com.aram.connect.persistence.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name ="user_login")
@NamedQueries({
	@NamedQuery(name = "UserLogin.getUserByUsername", query = "select m from UserLogin m where m.username = :username")
})
public class UserLogin extends AuditClass implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name ="user_login_id")
	private int userLoginId;
	
	@Column(name ="user_identity")
	private String userIdentity;
	
	@Column(name ="role_id")
	private int roleId;

	@Column(name="user_name")
	private String username;

	@Column(name="otp")
	private String otp;

	@Column(name="isVerified")
	private Boolean isVerified;

	public int getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(int userLoginId) {
		this.userLoginId = userLoginId;
	}

	public String getUserIdentity() {
		return userIdentity;
	}

	public void setUserIdentity(String userIdentity) {
		this.userIdentity = userIdentity;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
		
	public String getOTP() {
		return otp;
	}

	public void setOTP(String otp) {
		this.otp = otp;
	}

	public Boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}
}
