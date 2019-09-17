package com.aram.connect.dataObjects;


public class OnlineTestJson {
	
	Long questionId;
	
	Long testId;
	
	String onlineTestQuestion;
	
	String explanation;
	String paragraph;
	String imagePath;
	String explanationImage;
	String answer;
	
	String[] options;

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public Long getTestId() {
		return testId;
	}

	public void setTestId(Long testId) {
		this.testId = testId;
	}

	public String getOnlineTestQuestion() {
		return onlineTestQuestion;
	}

	public void setOnlineTestQuestion(String onlineTestQuestion) {
		this.onlineTestQuestion = onlineTestQuestion;
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

	public String getParagraph() {
		return paragraph;
	}

	public void setParagraph(String paragraph) {
		this.paragraph = paragraph;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getExplanationImage() {
		return explanationImage;
	}

	public void setExplanationImage(String explanationImage) {
		this.explanationImage = explanationImage;
	}

	

}
