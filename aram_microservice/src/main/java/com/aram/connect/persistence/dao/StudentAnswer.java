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

import com.aram.connect.persistence.dao.AuditClass;
import com.aram.connect.persistence.dao.Student;

@Entity
@Table(name = "student_answer")
@NamedQueries(
    @NamedQuery(name = "StudentAnswer.getAnswersForTQ", query = "select m from StudentAnswer m where testQuestion.testQuestionId = :tqId")
)
public class StudentAnswer implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "student_answer_id")
    private Long studentAnswerId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserLogin user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "test_question_id")
    private TestQuestion testQuestion;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "is_corrected")
    private Boolean isCorrected;

    @Column(name = "feedback")
    private String feedback;

    @Column(name = "feedback_file_path")
    private String feedbackFilePath;

    @Column(name = "answered_on")
    private Date answeredOn;

    @Column(name = "feedback_on")
    private Date feedbackOn;

    @Transient
    public Long studentId;

    @Transient
    public Long userId;

    @Transient 
    public Long testQuestionId;

    //GET

    public Long getStudentAnswerId() {
        return studentAnswerId;
    }

    public Student getStudent() {
        return student;
    }

    public UserLogin getUser() {
        return user;
    }

    public TestQuestion getTestQuestion() {
        return testQuestion;
    }

    public String getFilePath() {
        return filePath;
    }

    public Boolean getIsCorrected() {
        return isCorrected;
    }

    public String getFeedback() {
        return feedback;
    }

    public String getFeedbackFilePath() {
        return feedbackFilePath;
    }

    public Date getAnsweredOn() {
        return answeredOn;
    }

    public Date getFeedbackOn() {
        return feedbackOn;
    }

    //Set

    public void setStudentAnswerId(Long id) {
        studentAnswerId = id;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setUser(UserLogin user) {
        this.user = user;
    }

    public void setTestQuestion(TestQuestion tq) {
        testQuestion = tq;
    }

    public void setFilePath(String fp) {
        filePath = fp;
    }

    public void setIsCorrected(Boolean isCorrected) {
        this.isCorrected = isCorrected;
    }

    public void setFeedback(String f) {
        feedback = f;
    }

    public void setFeedbackFilePath(String ffp) {
        feedbackFilePath = ffp;
    }

    public void setAnsweredOn(Date ao) {
        answeredOn = ao;
    }

    public void setFeedbackOn(Date fo) {
        feedbackOn = fo;
    }

}