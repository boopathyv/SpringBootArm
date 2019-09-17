package com.aram.connect.dataObjects;

import java.util.List;

public class TestAttempt {

	int userloginid;

	int testid;

	int attemptid;
	
	List<QandA> qalist;

	public int getUserloginid() {
		return userloginid;
	}

	public void setUserloginid(int userloginid) {
		this.userloginid = userloginid;
	}

	public int getTestid() {
		return testid;
	}

	public void setTestid(int testid) {
		this.testid = testid;
	}

	public int getAttemptid() {
		return attemptid;
	}

	public void setAttemptid(int attemptid) {
		this.attemptid = attemptid;
	}

	public List<QandA> getQalist() {
		return qalist;
	}

	public void setQalist(List<QandA> qalist) {
		this.qalist = qalist;
	}

	

}
