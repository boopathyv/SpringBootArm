package com.aram.connect.persistence.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="mains_quiz")
@NamedQuery(name="MainsQuiz.findUsingDate", query="select m from MainsQuiz m where mainsQuizDate =:mquizDate")
public class MainsQuiz extends AuditClass{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="mains_quiz_id")
	Long mainsQuizId;
	
	@Column(name="mains_quiz_date")
	Date mainsQuizDate;
	
	@Column(name="mains_quiz_location")
	String location;
	
	@Column(name="mains_quiz_heading")
	String heading;
	
	@Column(name="mains_quiz_short_description")
	String shortDescription;
	
	@Column(name="mains_quiz_description")
	String description;

	public Long getMainsQuizId() {
		return mainsQuizId;
	}

	public void setMainsQuizId(Long mainsQuizId) {
		this.mainsQuizId = mainsQuizId;
	}

	public Date getMainsQuizDate() {
		return mainsQuizDate;
	}

	public void setMainsQuizDate(Date mainsQuizDate) {
		this.mainsQuizDate = mainsQuizDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	
}
