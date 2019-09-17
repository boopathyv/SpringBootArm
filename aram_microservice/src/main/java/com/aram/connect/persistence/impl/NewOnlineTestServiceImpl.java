package com.aram.connect.persistence.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.aram.connect.persistence.NewOnlineTestService;
import com.aram.connect.persistence.dao.NewOnlineTest;
import com.aram.connect.persistence.dao.NewOnlineTestAnswerSummary;
import com.aram.connect.persistence.dao.NewOnlineTestQuestion;
import com.aram.connect.persistence.dao.Student;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class NewOnlineTestServiceImpl implements NewOnlineTestService{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void saveNewOnlineTest(NewOnlineTest newOnlineTest) {
        em.persist(newOnlineTest);
    }

    @Override
    public void saveNewOnlineTestQuestion(NewOnlineTestQuestion newOnlineTestQuestion) {
        em.persist(newOnlineTestQuestion);
    }

    @Override
    public List<NewOnlineTest> getNewPrelimsTest() {
        TypedQuery<NewOnlineTest> query = em.createNamedQuery("NewOnlineTest.list", NewOnlineTest.class);
        return query.getResultList();
    } 

    @Override
    public List<NewOnlineTestQuestion> getPrelimsTestQuestions(Long id) {
        TypedQuery<NewOnlineTest> queryForFindNO = em.createNamedQuery("NewOnlineTest.findOne", NewOnlineTest.class).setParameter("id", id);


        TypedQuery<NewOnlineTestQuestion> query = em.createNamedQuery("NewOnlineTestQuestion.getQuestions", NewOnlineTestQuestion.class).setParameter("id", queryForFindNO.getSingleResult());
        return query.getResultList();
    }

    @Override
    public NewOnlineTestAnswerSummary getPrelimsTestSummaryByStudentAndTest(Long studentId, Long testId) {
        TypedQuery<NewOnlineTest> queryForFindNO = em.createNamedQuery("NewOnlineTest.findOne", NewOnlineTest.class).setParameter("id", testId);

        Integer studentInInt = Math.toIntExact(studentId);
        TypedQuery<Student> queryForFindStudent = em.createNamedQuery("Student.findById", Student.class).setParameter("studentId", studentInInt);

        TypedQuery<NewOnlineTestAnswerSummary> query = em.createNamedQuery("NewOnlineTestSummary.findByStudentAndTest", NewOnlineTestAnswerSummary.class).setParameter("student", queryForFindStudent.getSingleResult()).setParameter("newOnlineTest", queryForFindNO.getSingleResult());

        if(query.getFirstResult() == 0) {
            return null;
        }
        return query.getSingleResult();
    }

    @Override
    public void savePrelimsTestAnswer(NewOnlineTestAnswerSummary newOnlineTestAnswerSummary) {

        if(newOnlineTestAnswerSummary.newOnlineTestAnswerSummaryId == null || newOnlineTestAnswerSummary.newOnlineTestAnswerSummaryId == 0) {
            em.persist(newOnlineTestAnswerSummary);
            return;
        }

        em.merge(newOnlineTestAnswerSummary);
    }

    @Override
    public NewOnlineTest findPrelimsTestById(Long id) {
        TypedQuery<NewOnlineTest> queryForFindNO = em.createNamedQuery("NewOnlineTest.findOne", NewOnlineTest.class).setParameter("id", id);

        return queryForFindNO.getSingleResult();
    }

    @Override
    public NewOnlineTestQuestion findPrelimsQuestionById(Long id) {
        TypedQuery<NewOnlineTestQuestion> query = em.createNamedQuery("NewOnlineTestQuestion.findById", NewOnlineTestQuestion.class).setParameter("id", id);

        return query.getSingleResult();
    }

}