package com.aram.connect.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aram.connect.dataObjects.LatestNews;
import com.aram.connect.persistence.LatestNewsDBService;
import com.aram.connect.service.LatestNewsService;

@Service
@Transactional
public class LatestNewsServiceImpl implements LatestNewsService {
	
	@Autowired
	LatestNewsDBService dbService;

	@Override
	public List<LatestNews> getAllNews() {
		// TODO Auto-generated method stub
		return dbService.getAllNews();
	}

	@Override
	public List<LatestNews> getActiveNews() {
		// TODO Auto-generated method stub
		return dbService.getActiveNews();
	}

	@Override
	public void saveLatestNews(LatestNews latestNews) {
		dbService.saveLatestNews(latestNews);
	}

	@Override
	public LatestNews getNews(Long id) {
		return dbService.getNews(id);
	}

	@Override
	public void activateLatestNews(Long id) {
		dbService.activateLatestNews(id);
	}
	@Override
	public void inActivateLatestNews(Long id) {
		dbService.inActivateLatestNews(id);
	}
	@Override
	public void updateLatestNews(LatestNews latestNews) {
		dbService.updateLatestNews(latestNews);
	}

}
