package com.aram.connect.persistence;

import com.aram.connect.persistence.dao.Subcribe;

public interface SubscribeService {
	
	public void storeSubcribtion(Subcribe subscribe);
	
	public void unSubscribe(Subcribe subscribe);

}
