package com.aram.connect.service;

import java.util.List;

import com.aram.connect.dataObjects.LatestNews;

public interface LatestNewsService {

	public List<LatestNews> getAllNews();

	public List<LatestNews> getActiveNews();

	public void saveLatestNews(LatestNews latestNews);

	public LatestNews getNews(Long id);

	public void activateLatestNews(Long id);

	public void inActivateLatestNews(Long id);

	public void updateLatestNews(LatestNews latestNews);

}
