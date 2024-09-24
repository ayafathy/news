package com.apps.wave.news.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.apps.wave.news.dto.GeneralResponse;
import com.apps.wave.news.entity.News;
import com.apps.wave.news.service.NewsService;
import com.apps.wave.news.validator.ArabicOnly;
@Validated 
@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @PostMapping(value = "/add" )
    public ResponseEntity<GeneralResponse> addNews( 
    		@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date publishDate,
    		                               @RequestParam("title") String title,
    		                              @RequestParam("titleInArabic") @Valid @ArabicOnly String  titleInArabic ,
                                          @RequestParam("description") String description,
                                          @RequestParam("descriptionInArabic") @Valid @ArabicOnly String descriptionInArabic,
                                          @RequestPart(name = "img") MultipartFile imageFile) {
        try {
        	   if (imageFile.isEmpty()) {
        		   return ResponseEntity.ok(new GeneralResponse(HttpStatus.BAD_REQUEST.value(),"File is empty!"));
        	    }
        	    // Get the content type of the uploaded file
        	    String contentType = imageFile.getContentType();
        	 if (!"image/png".equals(contentType)) {
        	        return ResponseEntity.ok(new GeneralResponse(HttpStatus.BAD_REQUEST.value(),"File must be in PNG format!"));
        	    }
            News news = new News();
            news.setTitle(title);
            news.setTitleInArabic(titleInArabic);
            news.setPublishDate(publishDate);
            news.setDescription(description);
            news.setDescriptionInArabic(descriptionInArabic);
            news.setImage(imageFile.getBytes()); // Convert image to byte array
            newsService.createNews(news);
            return ResponseEntity.ok(new GeneralResponse(200,"News added successfully!"));
        } catch (IOException e) {
        	return ResponseEntity.ok(new GeneralResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Failed to upload image."));
        }
    }
    
 // Get all approved news (For normal users)
    @GetMapping("/Approvedlist")
    public ResponseEntity<GeneralResponse> getAllApprovedNews( ) {
        return new GeneralResponse().response((newsService.getAllApprovedNews()));
    }
    // Get all  news (For amdin and )
    @GetMapping("/list")
    public ResponseEntity<GeneralResponse> getAllNews(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return new GeneralResponse().response((newsService.list(PageRequest.of(page, size))));
    }
    // Admin approves a news article
    @PutMapping("/approve/{id}")
    public ResponseEntity<GeneralResponse> approveNews(@PathVariable Long id) {
        return new GeneralResponse().response((newsService.approveNews(id)));
    }

    // Delete news (Content Writer or Admin based on status)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteNews(@PathVariable Long id, @RequestHeader("Authorization") String token ) {
       
        return ResponseEntity.ok(new GeneralResponse(200, newsService.deleteNews(id, token))); 
    }
    // Delete news (Content Writer or Admin based on status)
    @DeleteMapping("/approveRequestDeleteNews/{id}")
    public ResponseEntity<?> ApproveRequestDeleteNews(@PathVariable Long id ) {
        newsService.approveRequestDeleteNews(id);
        return ResponseEntity.ok(new GeneralResponse(200,"News successfull deleted")); 
    }
    // Update news (for Content Writers/Admins)
    @PutMapping("update/{id}")
    public ResponseEntity<GeneralResponse> updateNews(@PathVariable Long id, @RequestBody @Valid News updatedNews) {
    	
        return new GeneralResponse().response((newsService.updateNews(id, updatedNews)));
    }

}

