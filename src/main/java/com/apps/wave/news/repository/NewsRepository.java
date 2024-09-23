package com.apps.wave.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apps.wave.news.entity.News;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findAllByStatus(News.Status status);
}

