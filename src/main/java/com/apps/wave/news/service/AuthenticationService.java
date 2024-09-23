package com.apps.wave.news.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.apps.wave.news.dto.AccessTokenResponse;
import com.apps.wave.news.dto.GeneralResponse;
import com.apps.wave.news.dto.LoginRequest;
import com.apps.wave.news.dto.LoginResponse;
import com.apps.wave.news.entity.User;
import com.apps.wave.news.exception.BusinessExceptions;
import com.apps.wave.news.repository.UserRepository;
import  com.apps.wave.news.security.JwtTokenProvider ;

@Service
public class AuthenticationService {
	    @Autowired
	    UserRepository userRepository;
	    @Autowired
	    AuthenticationManager authenticationManager;
	    @Autowired
	    JwtTokenProvider jwtTokenProvider ;



		public ResponseEntity<GeneralResponse> login(LoginRequest request) throws BusinessExceptions {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getEmail(),
					request.getPassword());
			
			authenticationManager.authenticate(token);
			
			// if there is no exception thrown from authentication manager,
			// we can generate a JWT token and give it to user.
			User user = userRepository.findByEmailIgnoreCase(request.getEmail());
			String jwt = jwtTokenProvider.generate(request.getEmail(),user.getRole().getName());
			String refreshToken =jwtTokenProvider.generateRefreshToken(request.getEmail());
			user.setRefreshToken(refreshToken);
			userRepository.save(user);
			LoginResponse adminDto = new LoginResponse(user);
			adminDto.setToken(jwt);
			return new GeneralResponse().response(adminDto);
		}
		
	    public ResponseEntity<GeneralResponse> refreshToken(String token) {
	     
	        String username;
	        String role ;
	        try {
	        	  username = jwtTokenProvider.getUsername(token);
	            // Optionally, check if the refresh token is stored and valid for the user
	           User user = userRepository.findByEmailIgnoreCase(username);
	              role =user.getRole().getName() ;
	            if (!user.getRefreshToken().equals(token)) {
	                 return ResponseEntity.ok(new GeneralResponse(HttpStatus.BAD_REQUEST.value(), "Invalid refresh token"));
	             }
	        } catch (Exception e) {
	            return ResponseEntity.ok(new GeneralResponse(HttpStatus.BAD_REQUEST.value(),"Invalid refresh token"));
	        }

	        // Generate new access token
	        String newAccessToken = jwtTokenProvider.generate(username,role);
	        return new GeneralResponse().response(new AccessTokenResponse(newAccessToken));
	    }
}
