package com.aram.connect.service.impl;

import com.aram.connect.service.SubscribeService;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aram.connect.persistence.dao.Subcribe;

@Service
public class SubscribeServiceImpl implements SubscribeService {

	@Autowired
	com.aram.connect.persistence.SubscribeService subscribeService;
	
	@Override
	public void subscribe(String emailId) {
		// TODO Auto-generated method stub
		Subcribe subscribe = createSubcribe(emailId);
		subscribe.setActiveFlag("Y");
		subscribeService.storeSubcribtion(subscribe);
		
	}

	@Override
	public void unSubscribe(String emailId) {
		// TODO Auto-generated method stub
		Subcribe subscribe = createSubcribe(emailId);
		subscribe.setActiveFlag("N");
		subscribeService.unSubscribe(subscribe);
	}
	
	public Subcribe createSubcribe(String emailId) {
		Subcribe subscribe = new Subcribe();
		subscribe.setEmailId(emailId);
		subscribe.setSubscribedDate(new Timestamp(System.currentTimeMillis()));
		return subscribe;
	}

	


}
