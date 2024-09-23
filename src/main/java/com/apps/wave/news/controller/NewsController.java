package com.apps.wave.news.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.apps.wave.news.dto.GeneralResponse;
import com.apps.wave.news.entity.News;
import com.apps.wave.news.service.NewsService;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @PostMapping("/add")
    public ResponseEntity<GeneralResponse> addNews(@RequestParam("title") String title,
    		                              @RequestParam("titleInArabic") String titleInArabic,
                                          @RequestParam("description") String description,
                                          @RequestParam("descriptionInArabic") String descriptionInArabic,
                                          @RequestParam("image") MultipartFile imageFile) {
        try {
            News news = new News();
            news.setTitle(title);
            news.setDescription(description);
            news.setImage(imageFile.getBytes()); // Convert image to byte array
            newsService.createNews(news);
            return ResponseEntity.ok(new GeneralResponse(200,"News added successfully!"));
        } catch (IOException e) {
        	return ResponseEntity.ok(new GeneralResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Failed to upload image."));
        }
    }
    
 // Get all approved news (For normal users)
    @GetMapping
    public ResponseEntity<GeneralResponse> getAllApprovedNews() {
        return new GeneralResponse().response((newsService.getAllApprovedNews()));
    }

    // Admin approves a news article
    @PutMapping("/approve/{id}")
    public ResponseEntity<GeneralResponse> approveNews(@PathVariable Long id) {
        return new GeneralResponse().response((newsService.approveNews(id)));
    }

    // Delete news (Content Writer or Admin based on status)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNews(@PathVariable Long id, @RequestHeader("Role") String role) {
        newsService.deleteNews(id, role);
        return ResponseEntity.ok(new GeneralResponse(200,"News successfull deleted")); 
    }

    // Update news (for Content Writers/Admins)
    @PutMapping("/{id}")
    public ResponseEntity<GeneralResponse> updateNews(@PathVariable Long id, @RequestBody News updatedNews) {
        return new GeneralResponse().response((newsService.updateNews(id, updatedNews)));
    }

}

