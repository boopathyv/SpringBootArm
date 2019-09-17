package com.aram.connect.persistence.dao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "new_online_test")
@NamedQueries({
    @NamedQuery(name = "NewOnlineTest.list", query = "select m from NewOnlineTest m ORDER BY m.testDate ASC"),
    @NamedQuery(name = "NewOnlineTest.findOne", query = "select m from NewOnlineTest m where m.newOnlineTestId = :id")
})
public class NewOnlineTest implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "new_online_test_id")
    private Long newOnlineTestId;

    @Column(name = "test_category")
    private String testCategory;

    @Column(name = "test_date")
    private Date testDate;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "isOnline")
    private Boolean isOnline;

    @Column(name = "testTime")
    private Long testTime; //In minutes

    public Long getNewOnlineTestId() {
        return this.newOnlineTestId;
    }

    public String getTestCategory() {
        return this.testCategory;
    }

    public Date getTestDate() {
        return this.testDate;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public Boolean getIsOnline() {
        return this.isOnline;
    }

    public Long getTestTime() {
        return this.testTime;
    }

    public void setTestCategory(String testCategory) {
        this.testCategory = testCategory;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setIsOnline(Boolean isOnline) {
        this.isOnline = isOnline;
    }

    public void setTestTime(Long testTime) {
        this.testTime = testTime;
    }



}