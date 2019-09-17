package com.aram.connect.persistence;

import java.util.List;

import com.aram.connect.Exception.AramException;
import com.aram.connect.persistence.dao.UserTestDetail;

public interface UserTestDetailService {
	
	public List<UserTestDetail> getTestDetailPerUser(int userLoginId , int testId);
	
	public UserTestDetail getTestDetailPerAttempt(int userLoginId , int testId, int attemptId);
	
	public boolean updateUserTestDetail(UserTestDetail userTestDetail) throws AramException;

}
