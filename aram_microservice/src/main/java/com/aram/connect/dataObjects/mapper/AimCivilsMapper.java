package com.aram.connect.dataObjects.mapper;

import java.sql.Timestamp;

import com.aram.connect.dataObjects.CurrentAffairsJson;
import com.aram.connect.dataObjects.FortnightCompliation;
import com.aram.connect.dataObjects.OnlineTest;
import com.aram.connect.dataObjects.OnlineTestJson;
import com.aram.connect.dataObjects.OnlineTestMbAppJson;
import com.aram.connect.dataObjects.PrelimsQuizJson;
import com.aram.connect.persistence.dao.MainsCurrentAffairs;
import com.aram.connect.persistence.dao.MainsQuiz;
import com.aram.connect.persistence.dao.PrelimsCurrentAffairs;
import com.aram.connect.persistence.dao.PrelimsQuiz;
import com.aram.connect.util.AramUtil;

public class AimCivilsMapper {



	public PrelimsCurrentAffairs convertJsonToPrelimsCA(CurrentAffairsJson cAffairs , String location) {
		PrelimsCurrentAffairs pcAffairs = new PrelimsCurrentAffairs();
		pcAffairs.setHeading(cAffairs.getHeading());
		pcAffairs.setLocation(location);
		pcAffairs.setShortDescription(cAffairs.getShortDescription());
		pcAffairs.setPrelimsDate(AramUtil.toDate(cAffairs.getFileDate()));
		pcAffairs.setCreatedBy("Admin");
		pcAffairs.setModifiedBy("Admin");
		pcAffairs.setModifiedDate(new Timestamp(System.currentTimeMillis()));

		return pcAffairs;
	}

	public MainsCurrentAffairs convertJsonToMainsCA(CurrentAffairsJson cAffairs,String location) {
		MainsCurrentAffairs mcAffairs = new MainsCurrentAffairs();
		mcAffairs.setHeading(cAffairs.getHeading());
		mcAffairs.setLocation(location);
		mcAffairs.setShortDescription(cAffairs.getShortDescription());
		mcAffairs.setMainsDate(AramUtil.toDate(cAffairs.getFileDate()));
		mcAffairs.setCreatedBy("Admin");
		mcAffairs.setModifiedBy("Admin");
		mcAffairs.setModifiedDate(new Timestamp(System.currentTimeMillis()));

		return mcAffairs;
	}


	public MainsQuiz convertJsonToMainsQuiz(CurrentAffairsJson cAffairs,String location) {
		MainsQuiz mquiz = new MainsQuiz();
		mquiz.setHeading(cAffairs.getHeading());
		mquiz.setLocation(location);
		mquiz.setShortDescription(cAffairs.getShortDescription());
		mquiz.setMainsQuizDate(AramUtil.toDate(cAffairs.getFileDate()));
		mquiz.setCreatedBy("Admin");
		mquiz.setModifiedBy("Admin");
		mquiz.setModifiedDate(new Timestamp(System.currentTimeMillis()));

		return mquiz;

	}

	public void convertPrelimsQuizIntoJson(PrelimsQuiz pq , PrelimsQuizJson pqj) {

		pqj.setPrelimsQuestion(pq.getPrelimsQuestion());
		pqj.setAnswer(pq.getAnswer());
		pqj.setPrelimsQuizDate( AramUtil.dateToString(pq.getPrelimsQuizDate(),"dd-MM-yyyy"));
		pqj.setExplanation(pq.getExplanation());
		pqj.setPrelimsQuizId(pq.getPrelimsQuizId());
		String[] options = new String[5];
		options[0] = pq.getOptionA();
		options[1] = pq.getOptionB();
		options[2] = pq.getOptionC();
		options[3] = pq.getOptionD();
		options[4] = pq.getOptionE();

		pqj.setOptions(options);


	}
	
	public OnlineTestJson  convertOnlineTestToJson(OnlineTest ot) {
		
		OnlineTestJson otJson = new OnlineTestJson();
		
		otJson.setQuestionId(ot.getQuestionId());
		otJson.setOnlineTestQuestion(ot.getOnlineTestQuestion());
		otJson.setTestId(ot.getTestId());
		otJson.setAnswer(ot.getAnswer());
		otJson.setExplanation(ot.getExplanation());
		otJson.setParagraph(ot.getParagraph());
		otJson.setImagePath(ot.getImagePath());
		otJson.setExplanationImage(ot.getExplanationImage());
		String[] options = new String[4];
		options[0] = ot.getOptionA();
		options[1] = ot.getOptionB();
		options[2] = ot.getOptionC();
		options[3] = ot.getOptionD();
		otJson.setOptions(options);
		
		return otJson;
	}
	
	
	public OnlineTestMbAppJson  convertOnlineMobileAppTestToJson(OnlineTest ot) {
		
	OnlineTestMbAppJson otJson = new OnlineTestMbAppJson();
		
		otJson.setId(ot.getQuestionId());
		otJson.setQuestion(ot.getOnlineTestQuestion());
		otJson.setTestId(ot.getTestId());
		otJson.setQ_no(ot.getQuestionId());
		otJson.setAnswer(ot.getAnswer());
		otJson.setExplanation(ot.getExplanation());
		otJson.setA(ot.getOptionA());
		otJson.setB(ot.getOptionB());
		otJson.setC(ot.getOptionC());
		otJson.setD(ot.getOptionD());
		
		 
		
		return otJson;
	}
	
	public FortnightCompliation convertFortnightIntoJson(CurrentAffairsJson cAffairs ,String location ) {
		FortnightCompliation fortnight = new FortnightCompliation();
		fortnight.setHeading(cAffairs.getHeading());
		fortnight.setShortDescription(cAffairs.getShortDescription());
		fortnight.setFortnightDate(AramUtil.toDate(cAffairs.getFileDate()));
		fortnight.setLocation(location);
		fortnight.setCreatedBy("Admin");
		fortnight.setModifiedBy("Admin");
		fortnight.setModifiedDate(new Timestamp(System.currentTimeMillis()));
				
		return fortnight;
	}
	
	

	
}
