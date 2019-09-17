package com.aram.connect.persistence.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.aram.connect.persistence.dao.AuditClass;

@Entity
@Table(name="user_test_detail")
@NamedQueries({
	@NamedQuery(name="UserTestDetail.findPerUser", query="select m from UserTestDetail m where userLoginId =:userLoginId"),
	@NamedQuery(name="UserTestDetail.findPerAttempt", query="select m from UserTestDetail m where userLoginId =:userLoginId and testId =:testId and attemptId =:attemptId")
})
public class UserTestDetail extends AuditClass{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_test_id")
	int userTestId;
	
	@Column(name="user_login_id")
	int userLoginId;
	
	@Column(name="test_id")
	int testId;
	
	@Column(name="attended_question")
	int attemptQuestion;
	
	@Column(name="unattended_question")
	int unattendedQuestion;
	
	@Column(name="correct_answer")
	int correctAnswer;
	
	@Column(name="wrong_answer")
	int wrongAnswer;
	
	@Column(name="attended_id")
	int attemptId;
	
	@Column(name="total_marks")
	double totalMarks;

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

	
	
	
	

}
