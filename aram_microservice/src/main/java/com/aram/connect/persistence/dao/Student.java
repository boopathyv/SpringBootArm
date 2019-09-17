package com.aram.connect.persistence.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name="students")
@NamedQueries({
	@NamedQuery(name = "Student.findById", query = "select m from Student m where m.studentId = :studentId"),
	@NamedQuery(name = "Student.findByEmailIdOrMobileNumber", query = "select m from Student m where m.emailId = :emailId OR m.mobileNumber = :mobileNumber"),
	@NamedQuery(name = "Student.list", query = "select m from Student m"),
	@NamedQuery(name = "Student.activeList", query = "select m from Student m where m.activeStatus = true AND m.registeredWithUPSC = :registeredWithUPSC"),
	@NamedQuery(name = "Student.inActiveList", query = "select m from Student m where m.activeStatus = false AND m.registeredWithUPSC = :registeredWithUPSC"),
	@NamedQuery(name = "Student.countActiveList", query = "select count(m) from Student m where m.activeStatus = true AND m.registeredWithUPSC = :registeredWithUPSC"),
	@NamedQuery(name = "Student.countInActiveList", query = "select count(m) from Student m where m.activeStatus = false AND m.registeredWithUPSC = :registeredWithUPSC"),
	// @NamedQuery(name = "Student.activeOffsetList", query = "select m from Student m where m.activeStatus = true limit :from, :to"),
	// @NamedQuery(name = "Student.inActiveOffsetList", query = "select m from Student m where m.activeStatus = false limit :from, :to"),
	@NamedQuery(name = "Student.searchWithTxtActiveOffsetList", query = "select m from Student m where (m.activeStatus = true) AND (m.registeredWithUPSC = :registeredWithUPSC) AND (m.studentId = :studentId OR m.studentName LIKE :searchTxt OR m.emailId LIKE :searchTxt OR m.mobileNumber LIKE :searchTxt)"),
	@NamedQuery(name = "Student.searchWithTxtInActiveOffsetList", query = "select m from Student m where (m.activeStatus = false) AND (m.registeredWithUPSC = :registeredWithUPSC) AND (m.studentId = :studentId OR m.studentName LIKE :searchTxt OR m.emailId LIKE :searchTxt OR m.mobileNumber LIKE :searchTxt)"),
	@NamedQuery(name = "Student.countOfSearchWithTxtActive", query = "select count(m) from Student m where (m.activeStatus = true) AND (m.registeredWithUPSC = :registeredWithUPSC) AND (m.studentId = :studentId OR m.studentName LIKE :searchTxt OR m.emailId LIKE :searchTxt OR m.mobileNumber LIKE :searchTxt)"),
	@NamedQuery(name = "Student.countOfSearchWithTxtInActive", query = "select count(m) from Student m where (m.activeStatus = false) AND (m.registeredWithUPSC = :registeredWithUPSC) AND (m.studentId = :studentId OR m.studentName LIKE :searchTxt OR m.emailId LIKE :searchTxt OR m.mobileNumber LIKE :searchTxt)")
})
public class Student implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="student_id")
	// @NotNull(message="Student Id can't be Null")
	private Integer studentId;

	@Column(name="student_name")
	private String studentName;
	
	@Column(name="stream")
	private String stream;
	
	@Column(name="email_id")
	@Email(message="Email Id is invalid")
	private String emailId;
	
	@Column(name="mobile_number")
	private String mobileNumber;

	@Column(name="active_status")
	private Boolean activeStatus;

	@Column(name = "registered_with_upsc")
	private Boolean registeredWithUPSC;

	@Column(name = "roll_no_upsc")
	private String rollNoUPSC;

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
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
	
	public Boolean getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(Boolean activeStatus) {
		this.activeStatus = activeStatus;
	}

	public Boolean getRegisteredWithUPSC() {
		return registeredWithUPSC;
	}

	public void setRegisteredWithUPSC(Boolean registeredWithUPSC) {
		this.registeredWithUPSC = registeredWithUPSC;
	}

	public String getRollNoUPSC() {
		return rollNoUPSC;
	}

	public void setRollNoUPSC(String upscRollNo) {
		this.rollNoUPSC = upscRollNo;
	}

	
}
