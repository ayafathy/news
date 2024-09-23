package com.apps.wave.news.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.wave.news.entity.TokenBlacklist;
import com.apps.wave.news.repository.TokenBlacklistRepository;
@Service
public class TokenBlacklistService {
	  @Autowired
	    private TokenBlacklistRepository tokenBlacklistRepository;

	    // Add token to blacklist
	    public void blacklistToken(String token) {
	        if (!isTokenBlacklisted(token)) {
	            tokenBlacklistRepository.save(new TokenBlacklist(token));
	        }
	    }

	    // Check if a token is blacklisted
	    public boolean isTokenBlacklisted(String token) {
	        return tokenBlacklistRepository.findByToken(token).isPresent();
	    }
}
