package com.aram.connect.persistence;

import java.util.List;

import com.aram.connect.Exception.AramException;
import com.aram.connect.persistence.dao.TestAttemptDetail;


public interface TestAttemptDetailService {
	
	public List<TestAttemptDetail> getTestAttemptDetail(int testId, int userLoginId , int attempt);
	
	public boolean storeTestAttemptPerUser(List<TestAttemptDetail> attemptList) throws AramException;

}
