package com.aram.connect.dataObjects;

import java.util.Date;

public class PrelimsQuizJson {
	
	Long prelimsQuizId;
	
	String prelimsQuizDate;
	
	String prelimsQuestion;
	
	String explanation;
	
	String answer;
	
	String[] options;

	public Long getPrelimsQuizId() {
		return prelimsQuizId;
	}

	public void setPrelimsQuizId(Long prelimsQuizId) {
		this.prelimsQuizId = prelimsQuizId;
	}

	public String getPrelimsQuizDate() {
		return prelimsQuizDate;
	}

	public void setPrelimsQuizDate(String prelimsQuizDate) {
		this.prelimsQuizDate = prelimsQuizDate;
	}

	public String getPrelimsQuestion() {
		return prelimsQuestion;
	}

	public void setPrelimsQuestion(String prelimsQuestion) {
		this.prelimsQuestion = prelimsQuestion;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String[] getOptions() {
		return options;
	}

	public void setOptions(String[] options) {
		this.options = options;
	}
	
	

}
