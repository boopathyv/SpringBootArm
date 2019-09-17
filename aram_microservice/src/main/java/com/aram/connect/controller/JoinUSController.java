package com.aram.connect.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aram.connect.persistence.dao.JoinUs;
import com.aram.connect.service.JoinUSService;

@CrossOrigin
@RestController
public class JoinUSController {
	
	@Autowired
	JoinUSService joinUSService;
	
	@GetMapping("/getAllJoinUS")
	public ResponseEntity<List<JoinUs>> getAllAccounts() {
		List<JoinUs> joinsUS = null;
		System.out.println("getAllAccounts");
		try{
			joinsUS = joinUSService.getAllJoinUs();	
			System.out.println("joinus size is " + joinsUS.size() );
			return ResponseEntity.ok(joinsUS);	
		}catch(Exception e){
			System.out.println("Exception in fetching all JoinUS"+e);
			return ResponseEntity.notFound().build();
		}	
	}
	@CrossOrigin
	@PostMapping("/joinUS")
	public ResponseEntity<JoinUs> createAccount(@Valid @RequestBody  JoinUs joinUs) {
		JoinUs joinsUS = null;
		System.out.println("createAccount");
		try {
			joinsUS = joinUSService.createJoinUS(joinUs); 
		 return ResponseEntity.ok(joinsUS);
		} catch(Exception e) {
		 System.out.println("Exception in creating JoinUs"+e);
		 return ResponseEntity.notFound().build();
		}
	}
}
