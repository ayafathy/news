package com.apps.wave.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.apps.wave.news.entity.News;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findAllByStatus(News.Status status);
    @Transactional
    @Modifying
    @Query("update News n set n.status =:status where n.publishDate <:currentDate ")
    int UpdateNewStatusExceedPublishDate(News.Status status ,@Param("currentDate") Date currentDate);
}

