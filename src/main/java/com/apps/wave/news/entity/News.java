package com.apps.wave.news.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.apps.wave.news.validator.ArabicOnly;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "NEWS")
@Entity
public class News {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String title;
	    @ArabicOnly
	    private String titleInArabic;
	    private String description;
	    @ArabicOnly
	    private String descriptionInArabic;
	    private Date publishDate;
	    @Lob
	    private byte[] image;

	    @Enumerated(EnumType.STRING)
	    private Status status; // PENDING, APPROVED, etc.

	    private String author;  // Content writer or admin
	    private LocalDateTime createdAt;
	    private LocalDateTime updatedAt;

	    private boolean isRequestToDelete ;  
	   

	    public enum Status {
	        PENDING,
	        APPROVED,
	        DELETED
	    }

}

