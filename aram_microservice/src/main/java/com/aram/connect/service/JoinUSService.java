package com.aram.connect.service;

import java.util.List;

import com.aram.connect.persistence.dao.JoinUs;

public interface JoinUSService {
	
	public List<JoinUs> getAllJoinUs() throws Exception;
	public JoinUs createJoinUS(JoinUs joinUs) throws Exception;

}
