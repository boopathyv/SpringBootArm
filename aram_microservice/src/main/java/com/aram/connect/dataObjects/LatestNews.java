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
@Table(name="latest_news")
@NamedQueries({
	@NamedQuery(name="LatestNews.findActiveNews", query="select m from LatestNews m where activeFlag = 'Y'"),
	@NamedQuery(name="LatestNews.getAllNews", query="select m from LatestNews m "),
	@NamedQuery(name="LatestNews.getNews", query="select m from LatestNews m where m.latestNewsId = :latestnewsId")
})
public class LatestNews extends AuditClass {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="latest_news_id")
	private Long latestNewsId;
	
	@Column(name="news")
	private String news;
	
	@Column(name="link")
	private String link;
	
	@Column(name="active_flag")
	private String activeFlag;

	@Column(name = "is_bold")
	private Boolean isBold;

	public Long getLatestNewsId() {
		return latestNewsId;
	}

	public void setLatestNewsId(Long latestNewsId) {
		this.latestNewsId = latestNewsId;
	}

	public String getNews() {
		return news;
	}

	public void setNews(String news) {
		this.news = news;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Boolean getIsBold() {
		return isBold;
	}

	public void setIsBold(Boolean isBold) {
		this.isBold = isBold;
	}
	
	

}
