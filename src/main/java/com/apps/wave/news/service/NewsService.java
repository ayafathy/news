package com.apps.wave.news.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.wave.news.entity.News;
import com.apps.wave.news.exception.BusinessExceptions;
import com.apps.wave.news.repository.NewsRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    // Create a news article (added by a content writer with 'PENDING' status)
    public News createNews(News news) {
        news.setStatus(News.Status.PENDING);
        news.setCreatedAt(LocalDateTime.now());
        news.setUpdatedAt(LocalDateTime.now());
        return newsRepository.save(news);
    }

    // Retrieve all approved news for normal users
    public List<News> getAllApprovedNews() {
        return newsRepository.findAllByStatus(News.Status.APPROVED);
    }

    // Retrieve all news (for admins/content writers)
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    // Retrieve a single news article by ID
    public Optional<News> getNewsById(Long id) {
        return newsRepository.findById(id);
    }

    // Update a news article
    public News updateNews(Long id, News updatedNews)  {
        Optional<News> existingNews = newsRepository.findById(id);

        if (existingNews.isPresent()) {
            News news = existingNews.get();
            news.setTitle(updatedNews.getTitle());
            news.setTitleInArabic(updatedNews.getTitleInArabic());
            news.setDescription(updatedNews.getDescription());
            news.setDescriptionInArabic(updatedNews.getDescriptionInArabic());
            news.setPublishDate(updatedNews.getPublishDate());
            news.setImage(updatedNews.getImage());
            news.setUpdatedAt(LocalDateTime.now());
            return newsRepository.save(news);
        } else {
            throw new BusinessExceptions("News with ID " + id + " not found");
        }
    }

    // Admin approves a pending news article
    public News approveNews(Long id) {
        Optional<News> existingNews = newsRepository.findById(id);

        if (existingNews.isPresent()) {
            News news = existingNews.get();
            news.setStatus(News.Status.APPROVED);
            news.setUpdatedAt(LocalDateTime.now());
            return newsRepository.save(news);
        } else {
            throw new BusinessExceptions("News with ID " + id + " not found");
        }
    }

    // Delete news article (Content Writer can delete if pending, Admin approval needed if approved)
    public void deleteNews(Long id, String requesterRole) {
        Optional<News> existingNews = newsRepository.findById(id);

        if (existingNews.isPresent()) {
            News news = existingNews.get();

            if (news.getStatus() == News.Status.PENDING) {
                // Content writer can delete directly if status is PENDING
                newsRepository.deleteById(id);
            } else if ("ADMIN".equals(requesterRole)) {
                // Only admin can delete if the status is APPROVED
                newsRepository.deleteById(id);
            } else {
                throw new BusinessExceptions("Only Admin can delete approved news");
            }
        } else {
            throw new BusinessExceptions("News with ID " + id + " not found");
        }
    }
}
