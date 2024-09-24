package com.apps.wave.news.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.apps.wave.news.entity.News;
import com.apps.wave.news.repository.NewsRepository;

@Service
public class NewsCleanupService {
	@Autowired
	NewsRepository newsRepository ;
	
	@Scheduled(fixedRateString = "${news.cleanup.milliseconds}")
	public void deleteTokens() {
		softDeleteNewsExceedPubishDate();
	}
	void softDeleteNewsExceedPubishDate(){
		newsRepository.UpdateNewStatusExceedPublishDate(News.Status.DELETED,new java.sql.Timestamp(System.currentTimeMillis()));
	}
}
