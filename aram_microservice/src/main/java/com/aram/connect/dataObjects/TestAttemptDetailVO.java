package com.aram.connect.dataObjects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



public class TestAttemptDetailVO{

	
	int testAttemptDetailId;
	
	long attendedQuestionId;
	
	String attendedAnswer;
	
	int userLoginId;
	
	int testId;
	
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

	
	
	
	
	
}
