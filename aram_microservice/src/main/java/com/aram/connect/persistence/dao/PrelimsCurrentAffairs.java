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
@Table(name="prelims_current_affiars")
@NamedQuery(name="PrelimsCurrentAffairs.findUsingDate", query="Select p from PrelimsCurrentAffairs p where p.prelimsDate= :prelimsDate")
public class PrelimsCurrentAffairs extends AuditClass{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="prelims_id")
	Long prelimsId;
	
	@Column(name="prelims_date")
	Date prelimsDate;
	
	@Column(name="prelims_location")
	String location;
	
	@Column(name="prelims_heading")
	String heading;
	
	@Column(name="prelims_short_description")
	String shortDescription;
	
	@Column(name="prelims_description")
	String description;

	public Long getPrelimsId() {
		return prelimsId;
	}

	public void setPrelimsId(Long prelimsId) {
		this.prelimsId = prelimsId;
	}

	public Date getPrelimsDate() {
		return prelimsDate;
	}

	public void setPrelimsDate(Date prelimsDate) {
		this.prelimsDate = prelimsDate;
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
