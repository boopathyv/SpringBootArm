package com.aram.connect.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aram.connect.dataObjects.OnlineTest;
import com.aram.connect.dataObjects.TestAttemptDetailVO;
import com.aram.connect.dataObjects.UserTestDetailVO;
import com.aram.connect.persistence.dao.LuTestName;

public interface OnlineTestService {
	
	public UserTestDetailVO calculateTestResults(List<OnlineTest> testList, List<TestAttemptDetailVO> attemptList);
	
	public List<UserTestDetailVO> getTestAttemptDetails(int userLoginId , int testId);
	
	public String storeOnlineTest(MultipartFile file);
	
	public List<LuTestName> getAllTestName();

}
