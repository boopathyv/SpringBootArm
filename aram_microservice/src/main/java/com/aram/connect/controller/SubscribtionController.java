package com.aram.connect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aram.connect.service.SubscribeService;

@RestController
public class SubscribtionController {
	
	@Autowired
	SubscribeService subscribeService;
	
	@PostMapping("/subscribe")
	public void subscribe(@RequestParam("emailId") String emailId) {
		
		if(null == emailId ) return ;
		
		subscribeService.subscribe(emailId);
		
	}
	
	@PostMapping("/unsubscribe")
	public void unSubscribe(@RequestParam("emailId") String emailId) {
		
		if(null == emailId ) return ;
		
		subscribeService.unSubscribe(emailId);
		
	}
	
	

}
