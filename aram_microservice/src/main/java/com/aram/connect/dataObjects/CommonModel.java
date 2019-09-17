package com.aram.connect.dataObjects;


import java.io.Serializable;
import java.util.ArrayList;

import com.aram.connect.persistence.dao.PrelimsQuiz;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CommonModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty
	private ArrayList<PrelimsQuiz> listQuestions;

	public ArrayList<PrelimsQuiz> getListQuestions() {
		return listQuestions;
	}

	public void setListQuestions(ArrayList<PrelimsQuiz> listQuestions) {
		this.listQuestions = listQuestions;
	}


	


	
	

	
	
	
}
