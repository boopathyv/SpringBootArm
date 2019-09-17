package com.aram.connect.persistence.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.aram.connect.persistence.dao.StudentAnswer;
import com.aram.connect.persistence.dao.TestQuestion;
import com.aram.connect.persistence.TestQuestionPersistService;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Transactional
@Repository

public class TestQuestionPersistServiceImpl implements TestQuestionPersistService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void saveTestQuestion(TestQuestion testQuestion) throws SQLException{

        try {
            if(testQuestion.getTestQuestionId() == null) {
                em.persist(testQuestion);
                em.flush();
            } else {
                em.merge(testQuestion);
                em.flush();        
            }
     
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    @Override
    public TestQuestion getTestQuestion(Long id) {
        TestQuestion testQuestion = em.find(TestQuestion.class, id);
        return testQuestion;
    }

    @Override
    public void saveStudentAnswer(StudentAnswer studentAnswer) throws SQLException {
        try {
            if(studentAnswer.getStudentAnswerId() == null) {
                em.persist(studentAnswer);
                em.flush();
            } else {
                em.merge(studentAnswer);
                em.flush();        
            }
     
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    @Override
    public StudentAnswer getStudentAnswer(Long id) {
        StudentAnswer studentAnswer = em.find(StudentAnswer.class, id);
        return studentAnswer;
    }

    @Override
    public List<TestQuestion> getTestQuestions(String questionType) {

        TypedQuery<TestQuestion> testQuestions = em.createNamedQuery("TestQuestion.list", TestQuestion.class).setParameter("questionType", questionType);

        return testQuestions.getResultList();
    }

    @Override
    public List<StudentAnswer> getStudentAnswersForTestQuestion(Long testQuestionId) {
        TypedQuery<StudentAnswer> studentAnswers = em.createNamedQuery("StudentAnswer.getAnswersForTQ", StudentAnswer.class).setParameter("tqId", testQuestionId);
        return studentAnswers.getResultList();
    }

    
    @Override
    public List<Object> getTestQuestionsForStudent(String studentId, String questionType) {
        javax.persistence.Query q = em.createNativeQuery("SELECT aram.student_answer.* FROM aram.student_answer where aram.student_answer.student_id = " + studentId, StudentAnswer.class);
        List<Object> testQuestions = q.getResultList();
        return testQuestions;
    }
}