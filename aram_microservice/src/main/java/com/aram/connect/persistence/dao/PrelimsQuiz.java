package com.aram.connect.persistence.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="prelims_quiz")
@NamedQueries({
	@NamedQuery(name="PrelimsQuiz.findAll", query="SELECT a FROM PrelimsQuiz a"),
	@NamedQuery(name="PrelimsQuiz.getUsingDate", query="SELECT a from PrelimsQuiz a where a.prelimsQuizDate = :prelimsQuizDate ")
})

public class PrelimsQuiz {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="prelims_quiz_id")
	Long prelimsQuizId;
	
	@Column(name="prelims_quiz_date")
	Date prelimsQuizDate;
	
	@Column(name="prelims_question")
	String prelimsQuestion;
	
	@Column(name="explanation")
	String explanation;
	
	@Column(name="answer")
	String answer;
	
	@Column(name="optionA")
	String optionA;
	
	@Column(name="optionB")
	String optionB;
	
	@Column(name="optionC")
	String optionC;
	
	@Column(name="optionD")
	String optionD;
	
	@Column(name="optionE")
	String optionE;
	
	@Column(name="created_by")
	String createdBy;
	
	@Column(name="modified_by")
	String modifiedBy;
	
	@Column(name="created_date")
	Timestamp createdDate;
	
	@Column(name="modified_date")
	Timestamp modifiedDate;	
	
	
	public PrelimsQuiz() {
	}
	
	public Long getPrelimsQuizId() {
		return prelimsQuizId;
	}

	public void setPrelimsQuizId(Long prelimsQuizId) {
		this.prelimsQuizId = prelimsQuizId;
	}

	public Date getPrelimsQuizDate() {
		return prelimsQuizDate;
	}

	public void setPrelimsQuizDate(Date prelimsQuizDate) {
		this.prelimsQuizDate = prelimsQuizDate;
	}

	public String getPrelimsQuestion() {
		return prelimsQuestion;
	}

	public void setPrelimsQuestion(String prelimsQuestion) {
		this.prelimsQuestion = prelimsQuestion;
	}

	
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
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

	public String getOptionE() {
		return optionE;
	}

	public void setOptionE(String optionE) {
		this.optionE = optionE;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prelimsQuestion == null) ? 0 : prelimsQuestion.hashCode());
		result = prime * result + ((prelimsQuizDate == null) ? 0 : prelimsQuizDate.hashCode());
		result = prime * result + ((prelimsQuizId == null) ? 0 : prelimsQuizId.hashCode());
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
		PrelimsQuiz other = (PrelimsQuiz) obj;
		if (prelimsQuestion == null) {
			if (other.prelimsQuestion != null)
				return false;
		} else if (!prelimsQuestion.equals(other.prelimsQuestion))
			return false;
		if (prelimsQuizDate == null) {
			if (other.prelimsQuizDate != null)
				return false;
		} else if (!prelimsQuizDate.equals(other.prelimsQuizDate))
			return false;
		if (prelimsQuizId == null) {
			if (other.prelimsQuizId != null)
				return false;
		} else if (!prelimsQuizId.equals(other.prelimsQuizId))
			return false;
		return true;
	}
	
	

	
}
