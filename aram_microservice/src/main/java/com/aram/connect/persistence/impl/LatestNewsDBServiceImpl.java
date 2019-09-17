/**
 * 
 */
package com.aram.connect.persistence.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.aram.connect.dataObjects.LatestNews;
import com.aram.connect.persistence.LatestNewsDBService;

/**
 * @author Ramasamy
 *
 */
@Repository
@Transactional
public class LatestNewsDBServiceImpl implements LatestNewsDBService {

	/* (non-Javadoc)
	 * @see com.aram.connect.persistence.LatestNewsDBService#getAllNews()
	 */
	
	@PersistenceContext
    private EntityManager em;
	
	
	@Override
	public List<LatestNews> getAllNews() {
		// TODO Auto-generated method stub
		TypedQuery<LatestNews> query = em.createNamedQuery("LatestNews.getAllNews", LatestNews.class);
		return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see com.aram.connect.persistence.LatestNewsDBService#getActiveNews()
	 */
	@Override
	public List<LatestNews> getActiveNews() {

		TypedQuery<LatestNews> query = em.createNamedQuery("LatestNews.findActiveNews", LatestNews.class);
		return query.getResultList();
		
	}

	@Override
	public void saveLatestNews(LatestNews latestNews) {
		em.persist(latestNews);
		em.flush();
	}

	@Override
	public void updateLatestNews(LatestNews latestNews) {
		em.merge(latestNews);
		em.flush();
	}

	@Override
	public LatestNews getNews(Long id) {
		TypedQuery<LatestNews> query = em.createNamedQuery("LatestNews.getNews", LatestNews.class).setParameter("latestnewsId", id);
		return query.getSingleResult();
	}

	@Override
	public void inActivateLatestNews (Long id) {
		LatestNews latestNews = this.getNews(id);
		latestNews.setActiveFlag("N");
		em.merge(latestNews);
		em.flush();
	}

	@Override
	public void activateLatestNews (Long id) {
		LatestNews latestNews = this.getNews(id);
		latestNews.setActiveFlag("Y");
		em.merge(latestNews);
		em.flush();
	}


}
