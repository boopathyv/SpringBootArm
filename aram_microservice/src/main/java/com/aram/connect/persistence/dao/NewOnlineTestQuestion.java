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

@Entity
@Table(name = "new_online_test_question")
@NamedQueries({
    @NamedQuery(name = "NewOnlineTestQuestion.getQuestions", query = "select m from NewOnlineTestQuestion m where m.newOnlineTest = :id ORDER BY m.seqNo ASC"),
    @NamedQuery(name = "NewOnlineTestQuestion.findById", query = "select m from NewOnlineTestQuestion m where m.newOnlineTesQuestiontId = :id")
})
public class NewOnlineTestQuestion implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "new_online_test_question_id")
    private Long newOnlineTesQuestiontId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "new_online_test")
    private NewOnlineTest newOnlineTest;

    @Column(name = "question")
    private String question;

    @Column(name = "optionA")
    private String optionA;

    @Column(name = "optionB")
    private String optionB;

    @Column(name = "optionC")
    private String optionC;

    @Column(name = "optionD")
    private String optionD;

    @Column(name = "answer")
    private String answer;

    @Column(name = "explanation")
    private String explanation;

    @Column(name = "image")
    private String image;

    @Column(name = "explanation_image")
    private String explanationImage;

    @Column(name = "seqNo")
    private Long seqNo;

    @Column(name = "mark")
    private Double mark;

    @Column(name = "negative_mark")
    private Double negativeMark;

    public Long getNewOnlineTestQuestionId() {
        return this.newOnlineTesQuestiontId;
    }

    public NewOnlineTest getNewOnlineTest() {
        return this.newOnlineTest;
    }

    public String getQuestion() {
        return this.question;
    }

    public String getOptionA() {
        return this.optionA;
    }

    public String getOptionB() {
        return this.optionB;
    }

    public String getOptionC() {
        return this.optionC;
    }

    public String getOptionD() {
        return this.optionD;
    }

    public String getAnswer() {
        return this.answer;
    }

    public String getExplanation() {
        return this.explanation;
    }

    public String getImage() {
        return this.image;
    }


    public String getExplanationImage() {
        return this.explanationImage;
    }

    public Double getMark() {
        return this.mark;
    }

    public Double getNegativeMark() {
        return this.negativeMark;
    }

    public Long getSeqNo() {
        return this.seqNo;
    }

    //Set

    public void setNewOnlineTest(NewOnlineTest newOnlineTest) {
        this.newOnlineTest = newOnlineTest;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public void setExplanationImage(String explanationImage) {
        this.explanationImage = explanationImage;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    public void setNegativeMark(Double negativeMark) {
        this.negativeMark = negativeMark;
    }

    public void setSeqNo(Long seqNo) {
        this.seqNo = seqNo;
    }

}