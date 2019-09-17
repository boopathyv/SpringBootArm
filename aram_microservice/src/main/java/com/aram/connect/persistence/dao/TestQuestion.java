package com.aram.connect.persistence.dao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.aram.connect.persistence.dao.AuditClass;
import com.aram.connect.persistence.dao.UserLogin;

@Entity
@Table(name = "test_question", uniqueConstraints = @UniqueConstraint(columnNames={"sequence_no"}))
@NamedQueries({
    @NamedQuery(name = "TestQuestion.list", query = "select m from TestQuestion m where m.questionType = :questionType")
})
public class TestQuestion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "test_question_id")
    private Long testQuestionId;

    @Column(name = "sequence_no")
    private Long sequenceNo;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "test_date")
    private Date testDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "test_created_by")
    private UserLogin testCreatedBy;

    @Column(name = "answer_file_path")
    private String answerFilePath;

    @Column(name = "has_answer")
    private Boolean hasAnswer;

    @Column(name = "answer_uploaded_on")
    private Date answerUploadedOn;

    @Column(name = "discussion_file_path")
    private String discussionFilePath;

    @Column(name = "question_type")
    private String questionType;

    @Transient
    public String testDateStr;
    @Transient
    public String answerUploadedOnStr;
    @Transient
    public int testCreatedById;

    public Long getTestQuestionId() {
        return testQuestionId;
    }

    public Long getSequenceNo() {
        return sequenceNo;
    }

    public String getFilePath() {
        return filePath;
    }

    public Date getTestDate() {
        return testDate;
    }

    public UserLogin getTestCreatedBy() {
        return testCreatedBy;
    }

    public String getAnswerFilePath() {
        return answerFilePath;
    }

    public Boolean getHasAnswer() {
        return hasAnswer;
    }

    public Date getAnswerUploadedOn() {
        return answerUploadedOn;
    }

    public String getDiscussionFilePath() {
        return discussionFilePath;
    }

    public String getQuestionType() {
        return questionType;
    }

    // Set
    public void setSequenceNo(Long seqNo) {
        this.sequenceNo = seqNo;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public void setTestCreatedBy(UserLogin userLogin) {
        this.testCreatedBy = userLogin;
    }

    public void setAnswerFilePath(String answerFilePath) {
        this.answerFilePath = answerFilePath;
    }

    public void setHasAnswer(Boolean hasAnswer) {
        this.hasAnswer = hasAnswer;
    }

    public void setAnswerUploadedOn(Date answerUploadedOn) {
        this.answerUploadedOn = answerUploadedOn;
    }

    public void setDiscussionFilePath(String discussionPath) {
        this.discussionFilePath = discussionPath;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

}