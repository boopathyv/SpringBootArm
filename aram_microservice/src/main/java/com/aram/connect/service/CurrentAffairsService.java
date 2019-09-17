package com.aram.connect.service;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aram.connect.Exception.FileStorageException;
import com.aram.connect.dataObjects.CurrentAffairsJson;
import com.aram.connect.dataObjects.ErrorObject;
import com.aram.connect.dataObjects.FortnightCompliation;
import com.aram.connect.dataObjects.OnlineTest;
import com.aram.connect.persistence.dao.MainsCurrentAffairs;
import com.aram.connect.persistence.dao.MainsQuiz;
import com.aram.connect.persistence.dao.PrelimsCurrentAffairs;
import com.aram.connect.persistence.dao.PrelimsQuiz;

public interface CurrentAffairsService {
	
	public String storeCurrentAffairsFile(MultipartFile file,CurrentAffairsJson cAffairs) throws FileStorageException;
	
	public Path getCurrentAffairs(String fileName) ;
	
	public String storeCurrentAffairs(CurrentAffairsJson cAffairs) ;
	
	public List<MainsCurrentAffairs> getAllMainsCurrentAffairs(String strMinDate,String strMaxDate) throws Exception;
	
	public List<PrelimsCurrentAffairs> getAllPrelimsCurrentAffairs(String strMinDate,String strMaxDate) throws Exception;
	
	public List<MainsQuiz> getAllMainsQuiz(String strMinDate,String strMaxDate) throws Exception;
	
	public List<PrelimsQuiz> getPrelimsQuiz(String strMaxDate) throws Exception;
	
	public List<ErrorObject> dataUploadQues(InputStream inputStream,String strFileName, Date prelimsDate)  throws Exception;
	
	public boolean prelimsQuizIfExist(Date prelimsQuizDate) throws Exception;
	
	public boolean deletePrelimsQuizUsingDate(Date prelimsQuizDate) throws Exception;

	public List<FortnightCompliation> getFornightCompilations(String strMinDate,String strMaxDate) throws Exception;
	
	public List<OnlineTest> getOnlineTest(long testId) throws Exception;
	
	public String getLuTestName(long testId) throws Exception;
	



}
