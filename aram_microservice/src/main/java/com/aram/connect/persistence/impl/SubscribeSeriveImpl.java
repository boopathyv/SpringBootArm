package com.aram.connect.persistence.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.aram.connect.persistence.SubscribeService;
import com.aram.connect.persistence.dao.PrelimsCurrentAffairs;
import com.aram.connect.persistence.dao.Subcribe;

@Repository
@Transactional
public class SubscribeSeriveImpl implements SubscribeService {
	
	@PersistenceContext
    private EntityManager em;

	@Override
	public void storeSubcribtion(Subcribe subscribe) {
		// TODO Auto-generated method stub
		Subcribe existing = findSubscribtion(subscribe.getEmailId());
		if( null != existing) {
			existing.setActiveFlag("Y");
			em.merge(existing);
			em.flush();
			return ;
		}
		
		em.persist(subscribe);
		em.flush();

	}
	
	public Subcribe findSubscribtion(String emailId) {
		TypedQuery<Subcribe> query = em.createNamedQuery("Subcribe.findUsingEmailId", Subcribe.class);
		query.setParameter("emailId", emailId);
		Subcribe subscribe = query.getResultList().stream().findFirst().orElse(null);
		
		return subscribe;
	}

	@Override
	public void unSubscribe(Subcribe subscribe) {
		// TODO Auto-generated method stub
		
		Subcribe existing = findSubscribtion(subscribe.getEmailId());
		if(null == existing) return;
		existing.setActiveFlag("N");
		em.merge(existing);
		em.flush();

	}

}
