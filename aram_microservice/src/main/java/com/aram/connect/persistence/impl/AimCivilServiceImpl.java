package com.aram.connect.persistence.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.aram.connect.dataObjects.FortnightCompliation;
import com.aram.connect.persistence.AimCivilsService;
import com.aram.connect.persistence.dao.MainsCurrentAffairs;
import com.aram.connect.persistence.dao.MainsQuiz;
import com.aram.connect.persistence.dao.PrelimsCurrentAffairs;


@Repository
@Transactional
public class AimCivilServiceImpl implements AimCivilsService{
	
	@PersistenceContext
    private EntityManager em;
	
	@Override
	public PrelimsCurrentAffairs mergePrelimsCurrentAffairs(PrelimsCurrentAffairs pcAffairs) {
		// TODO Auto-generated method stub
		PrelimsCurrentAffairs updated = null;
		PrelimsCurrentAffairs pcaExisting = getPrelimsCA(pcAffairs.getPrelimsDate());
		if(null == pcaExisting) {
			pcAffairs.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			em.persist(pcAffairs);
			em.flush();
			updated = pcAffairs;
		}else {
			mergeDataBasePrelims(pcAffairs,pcaExisting);
			em.merge(pcaExisting);
			em.flush();
			updated = pcaExisting;
		}
		
		return updated;
	}
	
	public void mergeDataBasePrelims(PrelimsCurrentAffairs pcAffairs,PrelimsCurrentAffairs pcaExisting) {
		pcaExisting.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		pcaExisting.setDescription(pcAffairs.getDescription());
		pcaExisting.setHeading(pcAffairs.getHeading());
		pcaExisting.setShortDescription(pcAffairs.getShortDescription());
		
	}
	
	public PrelimsCurrentAffairs getPrelimsCA(Date prelimsDate) {
		TypedQuery<PrelimsCurrentAffairs> query = em.createNamedQuery("PrelimsCurrentAffairs.findUsingDate", PrelimsCurrentAffairs.class);
		query.setParameter("prelimsDate", prelimsDate);
		PrelimsCurrentAffairs pca = query.getResultList()
									.stream().findFirst().orElse(null);
		return pca;
	}

	@Override
	public List<PrelimsCurrentAffairs> getAllPrelimsCurrentAffairs() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public MainsCurrentAffairs mergeMainsCurrentAffairs(MainsCurrentAffairs mcAffairs) {
		MainsCurrentAffairs updated = null;
		MainsCurrentAffairs mcaExisting = getMainsCA(mcAffairs.getMainsDate());
		if(null == mcaExisting) {
			mcAffairs.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			em.persist(mcAffairs);
			em.flush();
			updated = mcAffairs;
		}else {
			mergeDataBaseMains(mcAffairs,mcaExisting);
			em.merge(mcaExisting);
			em.flush();
			updated = mcaExisting;
		}
		
		return updated;
	}
	
	public MainsCurrentAffairs getMainsCA(Date mDate) {
		TypedQuery<MainsCurrentAffairs> query = em.createNamedQuery("MainsCurrentAffairs.findUsingDate", MainsCurrentAffairs.class);
		query.setParameter("mDate", mDate);
		MainsCurrentAffairs pca = query.getResultList()
				.stream().findFirst().orElse(null);
		return pca;
	}
	
	public void mergeDataBaseMains(MainsCurrentAffairs mcAffairs,MainsCurrentAffairs mcaExisting) {
		mcaExisting.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		mcaExisting.setDescription(mcAffairs.getDescription());
		mcaExisting.setHeading(mcAffairs.getHeading());
		mcaExisting.setShortDescription(mcAffairs.getShortDescription());
	}

	@Override
	public List<MainsCurrentAffairs> getMainsCurrentAffairs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MainsQuiz mergeMainsQuiz(MainsQuiz mQuiz) {
		MainsQuiz updated = null;
		MainsQuiz mQuizExisting = getMainsQuiz(mQuiz.getMainsQuizDate());
		if(null == mQuizExisting) {
			mQuiz.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			em.persist(mQuiz);
			em.flush();
			updated = mQuiz;
		}else {
			mergeDataBaseMainsQuiz(mQuiz,mQuizExisting);
			em.merge(mQuizExisting);
			em.flush();
			updated = mQuizExisting;
		}
		
		return updated;
	}
	public MainsQuiz getMainsQuiz(Date mquizDate) {
		TypedQuery<MainsQuiz> query = em.createNamedQuery("MainsQuiz.findUsingDate", MainsQuiz.class);
		query.setParameter("mquizDate", mquizDate);
		MainsQuiz pca = query.getResultList()
				.stream().findFirst().orElse(null);
		return pca;
	}
	
	public void mergeDataBaseMainsQuiz(MainsQuiz mQuiz,MainsQuiz mQuizExisting) {
		mQuizExisting.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		mQuizExisting.setDescription(mQuiz.getDescription());
		mQuizExisting.setHeading(mQuiz.getHeading());
		mQuizExisting.setShortDescription(mQuiz.getShortDescription());
	}

	@Override
	public List<MainsQuiz> getMainsQuizList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MainsCurrentAffairs> getAllMainsCurrentAffairs() {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public FortnightCompliation mergeFortnight(FortnightCompliation fortnight) {

		FortnightCompliation returnFortnight ;
		FortnightCompliation fExisting = getFortnightCompliation(fortnight.getFortnightDate());
		if(null == fExisting) {
			fortnight.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			em.persist(fortnight);
			em.flush();
			returnFortnight = fortnight;
		}else {
			mergeDBFortnightCompliation(fortnight,fExisting);
			em.merge(fExisting);
			em.flush();
			returnFortnight = fExisting;
		}
		return returnFortnight;
	}
	
	public FortnightCompliation getFortnightCompliation(Date fDate) {
		TypedQuery<FortnightCompliation> query = em.createNamedQuery("FortnightCompliation.findUsingDate", FortnightCompliation.class);
		query.setParameter("fDate", fDate);
		FortnightCompliation pca = query.getResultList()
				.stream().findFirst().orElse(null);
		return pca;
	}
	
	public void mergeDBFortnightCompliation(FortnightCompliation mQuiz,FortnightCompliation mQuizExisting) {
		mQuizExisting.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		mQuizExisting.setDescription(mQuiz.getDescription());
		mQuizExisting.setHeading(mQuiz.getHeading());
		mQuizExisting.setShortDescription(mQuiz.getShortDescription());
	}
	
	
	

}
