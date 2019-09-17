package com.aram.connect.persistence.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="lu_test_name")
@NamedQueries({
	@NamedQuery(name="LuTestName.findTestid", query="select m from LuTestName m where testId =:tId"),
	@NamedQuery(name="LuTestName.getAll", query="Select m from LuTestName m ")
})
public class LuTestName {
	
	@Id
	@Column(name="test_id")
	private Long testId;
	
	@Column(name="test_name")
	private String testName;
	
	@Column(name="test_type")
	private String testType;
	
	public Long getTestId() {
		return testId;
	}

	public void setTestId(Long testId) {
		this.testId = testId;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}
	
	

}
