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
@Table(name = "new_online_test_answer_summary")
@NamedQueries({
    @NamedQuery(name = "NewOnlineTestSummary.findByStudentAndTest", query = "select m from NewOnlineTestAnswerSummary m where m.student = :student AND m.newOnlineTest = :newOnlineTest")
})
public class NewOnlineTestAnswerSummary implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "new_online_test_answer_summary_id")
    public Long newOnlineTestAnswerSummaryId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student")
    public Student student;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "new_online_test")
    public NewOnlineTest newOnlineTest;

    @Column(name = "created_date")
    public Date createdDate;

    @Column(name = "total_questions")
    public Long totalQuestions;

    @Column(name = "attended_questions")
    public Long attendedQuestions;

    @Column(name = "not_attended_questions")
    public Long notAttendedQuestions;

    @Column(name = "correct_answers")
    public Long correctAnswers;

    @Column(name = "mark")
    public Long mark;

    @Column(name = "total_mark")
    public Long totalMark;

    @Column(name = "negative_marks")
    public Long negativeMark;    

    @Column(name = "is_completed")
    public Boolean isCompleted;

    @Column(name = "time_stamp")
    public Long timeStamp;

    @Column(name = "answers")
    public PrelimsTestAnswer answers;

}
