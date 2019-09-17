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
@Table(name="mains_current_affiars")
@NamedQuery(name="MainsCurrentAffairs.findUsingDate", query="Select p from MainsCurrentAffairs p where p.mainsDate= :mDate")
public class MainsCurrentAffairs extends AuditClass {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="mains_id")
	Long mainsId;
	
	@Column(name="mains_date")
	Date mainsDate;
	
	@Column(name="mains_location")
	String location;
	
	@Column(name="mains_heading")
	String heading;
	
	@Column(name="mains_short_description")
	String shortDescription;
	
	@Column(name="mains_description")
	String description;

	public Long getMainsId() {
		return mainsId;
	}

	public void setMainsId(Long mainsId) {
		this.mainsId = mainsId;
	}

	public Date getMainsDate() {
		return mainsDate;
	}

	public void setMainsDate(Date mainsDate) {
		this.mainsDate = mainsDate;
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
