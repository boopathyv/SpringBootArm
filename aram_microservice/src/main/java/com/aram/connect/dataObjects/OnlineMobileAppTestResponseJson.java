package com.aram.connect.dataObjects;

import java.util.List;

public class OnlineMobileAppTestResponseJson {
	
	
	String name;
	String description;
	Integer no_of_question;
	List<OnlineTestMbAppJson> questions;
	Integer is_answerd;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getNo_of_question() {
		return no_of_question;
	}
	public void setNo_of_question(Integer no_of_question) {
		this.no_of_question = no_of_question;
	}
	public List<OnlineTestMbAppJson> getQuestions() {
		return questions;
	}
	public void setQuestions(List<OnlineTestMbAppJson> questions) {
		this.questions = questions;
	}
	public Integer getIs_answerd() {
		return is_answerd;
	}
	public void setIs_answerd(Integer is_answerd) {
		this.is_answerd = is_answerd;
	}
	
	
	
}
