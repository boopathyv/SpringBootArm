package com.aram.connect.persistence.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aram.connect.Exception.AramException;
import com.aram.connect.persistence.TestAttemptDetailService;
import com.aram.connect.persistence.dao.Subcribe;
import com.aram.connect.persistence.dao.TestAttemptDetail;

@Transactional
@Repository
public class TestAttemptServiceImpl implements TestAttemptDetailService {

	
	@PersistenceContext
    private EntityManager em;
	
	@Override
	public List<TestAttemptDetail> getTestAttemptDetail(int testId, int userLoginId, int attempt) {
		TypedQuery<TestAttemptDetail> query = em.createNamedQuery("TestAttemptDetail.findPerUser", TestAttemptDetail.class);
		query.setParameter("userLoginId", userLoginId);
		query.setParameter("testId", testId);
		query.setParameter("attemptId", attempt);
		List<TestAttemptDetail> attemptDetailList = query.getResultList();
		return attemptDetailList;
	}

	@Override
	public boolean storeTestAttemptPerUser(List<TestAttemptDetail> attemptList) throws AramException {
		
		try {
			for(TestAttemptDetail attempt : attemptList) {
				if(0 == attempt.getTestAttemptDetailId()) {
					em.persist(attempt);
				}else {
					em.merge(attempt);
				}
			}
			em.flush();
		}catch(Exception e) {
			throw new AramException("Error while mergeing the Test Attempt Detail " +e.getMessage());
		}
		return false;
	}

}
