package com.aram.connect.persistence.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="test_attempt_detail")
@NamedQuery(name="TestAttemptDetail.findPerUser", query="select m from TestAttemptDetail m where userLoginId =:userLoginId and testId =:testId and attemptId =:attemptId")
public class TestAttemptDetail{

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="test_attempt_detail_id")
	int testAttemptDetailId;
	
	@Column(name="attended_question_id")
	long attendedQuestionId;
	
	@Column(name="attended_answer")
	String attendedAnswer;
	
	@Column(name="user_login_id")
	int userLoginId;
	
	@Column(name="test_id")
	int testId;
	
	@Column(name="attempt_id")
	int attemptId;

	public int getTestAttemptDetailId() {
		return testAttemptDetailId;
	}

	public void setTestAttemptDetailId(int testAttemptDetailId) {
		this.testAttemptDetailId = testAttemptDetailId;
	}

	public long getAttendedQuestionId() {
		return attendedQuestionId;
	}

	public void setAttendedQuestionId(long attendedQuestionId) {
		this.attendedQuestionId = attendedQuestionId;
	}

	public String getAttendedAnswer() {
		return attendedAnswer;
	}

	public void setAttendedAnswer(String attendedAnswer) {
		this.attendedAnswer = attendedAnswer;
	}

	public int getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(int userLoginId) {
		this.userLoginId = userLoginId;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public int getAttemptId() {
		return attemptId;
	}

	public void setAttemptId(int attemptId) {
		this.attemptId = attemptId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + attemptId;
		result = prime * result + (int) (attendedQuestionId ^ (attendedQuestionId >>> 32));
		result = prime * result + testId;
		result = prime * result + userLoginId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestAttemptDetail other = (TestAttemptDetail) obj;
		if (attemptId != other.attemptId)
			return false;
		if (attendedQuestionId != other.attendedQuestionId)
			return false;
		if (testId != other.testId)
			return false;
		if (userLoginId != other.userLoginId)
			return false;
		return true;
	}

	
	
	
	
	
}
