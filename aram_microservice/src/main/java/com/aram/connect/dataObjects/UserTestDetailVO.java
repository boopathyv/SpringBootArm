package com.aram.connect.dataObjects;

import com.aram.connect.persistence.dao.AuditClass;

public class UserTestDetailVO extends AuditClass{
	
	int userTestId;
	
	int userLoginId;
	
	int testId;
	
	int attemptQuestion;
	
	int unattendedQuestion;
	
	int correctAnswer;
	
	int wrongAnswer;
	
	int attemptId;
	
	double totalMarks;
	
	String testName;
	
	String attemptDate;
	

	public int getUserTestId() {
		return userTestId;
	}

	public void setUserTestId(int userTestId) {
		this.userTestId = userTestId;
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

	public int getAttemptQuestion() {
		return attemptQuestion;
	}

	public void setAttemptQuestion(int attemptQuestion) {
		this.attemptQuestion = attemptQuestion;
	}

	public int getUnattendedQuestion() {
		return unattendedQuestion;
	}

	public void setUnattendedQuestion(int unattendedQuestion) {
		this.unattendedQuestion = unattendedQuestion;
	}

	public int getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public int getWrongAnswer() {
		return wrongAnswer;
	}

	public void setWrongAnswer(int wrongAnswer) {
		this.wrongAnswer = wrongAnswer;
	}

	public int getAttemptId() {
		return attemptId;
	}

	public void setAttemptId(int attemptId) {
		this.attemptId = attemptId;
	}

	public double getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(double totalMarks) {
		this.totalMarks = totalMarks;
	}
	
	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getAttemptDate() {
		return attemptDate;
	}

	public void setAttemptDate(String attemptDate) {
		this.attemptDate = attemptDate;
	}

	@Override
	public String toString() {
		return "UserTestDetailVO [userTestId=" + userTestId + ", userLoginId=" + userLoginId + ", testId=" + testId
				+ ", attemptQuestion=" + attemptQuestion + ", unattendedQuestion=" + unattendedQuestion
				+ ", correctAnswer=" + correctAnswer + ", wrongAnswer=" + wrongAnswer + ", attemptId=" + attemptId
				+ ", totalMarks=" + totalMarks + "]";
	}

	
	
	
	

}
