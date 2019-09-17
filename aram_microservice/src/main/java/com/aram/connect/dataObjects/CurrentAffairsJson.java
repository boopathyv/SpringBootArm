package com.aram.connect.dataObjects;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.web.multipart.MultipartFile;

@XmlRootElement
public class CurrentAffairsJson {
	
	private MultipartFile file;
	
	private String heading;
	
	private String shortDescription;
	
	private String type;
	
	private String fileDate;
	
	public CurrentAffairsJson() {
		
	}

	public CurrentAffairsJson(MultipartFile file, String heading, String shortDescription, String type) {
		super();
		this.file = file;
		this.heading = heading;
		this.shortDescription = shortDescription;
		this.type = type;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFileDate() {
		return fileDate;
	}

	public void setFileDate(String fileDate) {
		this.fileDate = fileDate;
	}
	
	

}
