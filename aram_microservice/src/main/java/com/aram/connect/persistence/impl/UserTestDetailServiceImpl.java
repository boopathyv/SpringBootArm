package com.aram.connect.persistence.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aram.connect.Exception.AramException;
import com.aram.connect.dataObjects.mapper.UserTestDetailMapper;
import com.aram.connect.persistence.UserTestDetailService;
import com.aram.connect.persistence.dao.TestAttemptDetail;
import com.aram.connect.persistence.dao.UserTestDetail;
import com.aram.connect.util.AramUtil;

@Transactional
@Repository
public class UserTestDetailServiceImpl implements UserTestDetailService {

	@PersistenceContext
    private EntityManager em;
	
	@Override
	public List<UserTestDetail> getTestDetailPerUser(int userLoginId, int testId) {
		
		TypedQuery<UserTestDetail> query = em.createNamedQuery("UserTestDetail.findPerUser", UserTestDetail.class);
		query.setParameter("userLoginId", userLoginId);
		List<UserTestDetail> attemptDetailList = query.getResultList();
		return attemptDetailList;
	}

	@Override
	public UserTestDetail getTestDetailPerAttempt(int userLoginId, int testId, int attemptId) {
		
		TypedQuery<UserTestDetail> query = em.createNamedQuery("UserTestDetail.findPerAttempt", UserTestDetail.class);
		query.setParameter("userLoginId", userLoginId);
		query.setParameter("testId", testId);
		query.setParameter("attemptId", attemptId);
		Optional<UserTestDetail> attemptDetail = query.getResultList().stream().findFirst();
		
		return attemptDetail.isPresent() ? attemptDetail.get() : null;
	}

	@Override
	public boolean updateUserTestDetail(UserTestDetail userTestDetail) throws AramException {
		
		UserTestDetail existingUserTest = getTestDetailPerAttempt(userTestDetail.getUserLoginId() , userTestDetail.getTestId(), userTestDetail.getAttemptId());
		UserTestDetailMapper mapper = new UserTestDetailMapper();
		try {
			if(null == existingUserTest) {
				userTestDetail.setCreatedBy(userTestDetail.getUserLoginId()+"");
				userTestDetail.setCreatedDate(AramUtil.getCurrentTimeStamp());
				userTestDetail.setModifiedDate(AramUtil.getCurrentTimeStamp());
				userTestDetail.setModifiedBy(userTestDetail.getUserLoginId()+"");
				em.persist(userTestDetail);
				em.flush();
			}else {
				mapper.convertEntityToEntity(existingUserTest, userTestDetail);
				existingUserTest.setModifiedBy(userTestDetail.getUserLoginId()+"");
				existingUserTest.setModifiedDate(AramUtil.getCurrentTimeStamp());
				em.merge(existingUserTest);
				em.flush();
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new AramException(e.getMessage());
		}
		
		return true;
	}

}
