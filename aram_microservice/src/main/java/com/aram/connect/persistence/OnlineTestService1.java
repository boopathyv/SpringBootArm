package com.aram.connect.persistence;

import java.util.List;

import com.aram.connect.dataObjects.OnlineTest;
import com.aram.connect.persistence.dao.LuTestName;

public interface OnlineTestService1 {
	
	public String storeOnlineTest(List<OnlineTest> testList);
	
	public List<LuTestName> getAllTest();
	
	public String getTestName(long testId);

}
