package com.aram.connect.dataObjects;

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
@Table(name="online_test")
@NamedQueries({
	@NamedQuery(name="OnlineTest.findTestid", query="select m from OnlineTest m where testId =:tId"),
	@NamedQuery(name="OnlineTest.deleteByTestId", query="Delete from OnlineTest m where testId =:tId")
})

public class OnlineTest extends AuditClass{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="question_id")
	private Long questionId;
	
	@Column(name="test_id")
	private Long testId;
	
	@Column(name="online_test_question")
	private String onlineTestQuestion;
	
	@Column(name="explanation")
	private String explanation;
	
	@Column(name="answer")
	private String answer;
	
	@Column(name="optionA")
	private String optionA;
	
	@Column(name="optionB")
	private String optionB;
	
	@Column(name="optionC")
	private String optionC;
	
	@Column(name="optionD")
	private String optionD;
	
	@Column(name="paragraph")
	private String paragraph;
	
	@Column(name="image")
	private String imagePath;
	
	@Column(name="explanation_image")
	private String explanationImage;

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

	public String getOptionA() {
		return optionA;
	}

	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}

	public String getOptionB() {
		return optionB;
	}

	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}

	public String getOptionC() {
		return optionC;
	}

	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}

	public String getOptionD() {
		return optionD;
	}

	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}
	
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((questionId == null) ? 0 : questionId.hashCode());
		result = prime * result + ((testId == null) ? 0 : testId.hashCode());
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
		OnlineTest other = (OnlineTest) obj;
		if (questionId == null) {
			if (other.questionId != null)
				return false;
		} else if (!questionId.equals(other.questionId))
			return false;
		if (testId == null) {
			if (other.testId != null)
				return false;
		} else if (!testId.equals(other.testId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OnlineTest [questionId=" + questionId + ", testId=" + testId + ", onlineTestQuestion="
				+ onlineTestQuestion + ", explanation=" + explanation + ", answer=" + answer + ", optionA=" + optionA
				+ ", optionB=" + optionB + ", optionC=" + optionC + ", optionD=" + optionD + ", paragraph=" + paragraph
				+ ", imagePath=" + imagePath + ", explanationImage=" + explanationImage + "]";
	}
	
	
	
	

}
