package com.aram.connect.dataObjects;

import java.util.Date;
import com.aram.connect.persistence.dao.AuditClass;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="fortnight_compilation")
@NamedQuery(name="FortnightCompliation.findUsingDate", query="select m from FortnightCompliation m where fortnightDate =:fDate")
public class FortnightCompliation extends AuditClass{
	
	public Long getFortnightId() {
		return fortnightId;
	}

	public void setFortnightId(Long fortnightId) {
		this.fortnightId = fortnightId;
	}

	public Date getFortnightDate() {
		return fortnightDate;
	}

	public void setFortnightDate(Date fortnightDate) {
		this.fortnightDate = fortnightDate;
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

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="fortnight_id")
	Long fortnightId;
	
	@Column(name="fortnight_date")
	Date fortnightDate;
	
	@Column(name="fortnight_location")
	String location;
	
	@Column(name="fortnight_heading")
	String heading;
	
	@Column(name="fortnight_short_description")
	String shortDescription;
	
	@Column(name="fortnight_description")
	String description;
	
	

}
