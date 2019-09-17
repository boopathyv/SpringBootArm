package com.aram.connect.service;

import com.aram.connect.persistence.dao.Subcribe;

public interface SubscribeService {
	
	public void subscribe(String emailId);
	
	public void unSubscribe(String emailId);

}
