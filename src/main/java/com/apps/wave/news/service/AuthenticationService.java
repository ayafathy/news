package com.apps.wave.news.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.apps.wave.news.dto.GeneralResponse;
import com.apps.wave.news.dto.LoginRequest;
import com.apps.wave.news.dto.UserDto;
import com.apps.wave.news.entity.User;
import com.apps.wave.news.exception.BusinessExceptions;
import com.apps.wave.news.repository.UserRepository;
import  com.apps.wave.news.security.JwtTokenProvider ;

@Service
public class AuthenticationService {
	
	 private final UserRepository userRepository;

	    @Autowired
	    AuthenticationManager authenticationManager;
	    @Autowired
	    JwtTokenProvider JwtTokenProvider ;


	    public AuthenticationService(UserRepository userRepository) {
	        this.userRepository = userRepository;
	    }

		public ResponseEntity<GeneralResponse> login(LoginRequest request) throws BusinessExceptions {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getEmail(),
					request.getPassword());
			
			authenticationManager.authenticate(token);
			
			// if there is no exception thrown from authentication manager,
			// we can generate a JWT token and give it to user.
			User user = userRepository.findByEmailIgnoreCase(request.getEmail());
			String jwt = JwtTokenProvider.generate(request.getEmail(),user.getRole().getName());	
			UserDto adminDto = new UserDto(user);
			adminDto.setToken(jwt);
			return new GeneralResponse().response(adminDto);
		}
}
