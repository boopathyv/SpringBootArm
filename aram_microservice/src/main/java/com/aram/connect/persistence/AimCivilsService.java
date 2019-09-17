package com.aram.connect.persistence;

import java.util.List;

import com.aram.connect.dataObjects.FortnightCompliation;
import com.aram.connect.persistence.dao.MainsCurrentAffairs;
import com.aram.connect.persistence.dao.MainsQuiz;
import com.aram.connect.persistence.dao.PrelimsCurrentAffairs;

public interface AimCivilsService {


	public PrelimsCurrentAffairs mergePrelimsCurrentAffairs(PrelimsCurrentAffairs pcAffairs);

	public List<PrelimsCurrentAffairs> getAllPrelimsCurrentAffairs();
	
	public List<MainsCurrentAffairs> getAllMainsCurrentAffairs();

	public MainsCurrentAffairs mergeMainsCurrentAffairs(MainsCurrentAffairs mcAffairs);

	public List<MainsCurrentAffairs> getMainsCurrentAffairs();

	public MainsQuiz mergeMainsQuiz(MainsQuiz mQuiz);

	public List<MainsQuiz> getMainsQuizList();
	
	public FortnightCompliation mergeFortnight(FortnightCompliation fortnight);
	

}
