package com.aram.connect.persistence.dao;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public class AuditClass {
	
	@Column(name="created_by")
	String createdBy;
	
	@Column(name="modified_by")
	String modifiedBy;
	
	@Column(name="created_date")
	Timestamp createdDate;
	
	@Column(name="modified_date")
	Timestamp modifiedDate;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	

}
