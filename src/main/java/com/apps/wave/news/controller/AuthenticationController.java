package com.apps.wave.news.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apps.wave.news.dto.GeneralResponse;
import com.apps.wave.news.dto.LoginRequest;
import com.apps.wave.news.dto.RefreshRequest;
import com.apps.wave.news.dto.UserSignupDTO;
import com.apps.wave.news.enums.Role;
import com.apps.wave.news.exception.BusinessExceptions;
import com.apps.wave.news.service.AuthenticationService;
import com.apps.wave.news.service.UserService;



@RestController()
@RequestMapping(path ="/api/auth")
public class AuthenticationController {
	@Autowired
    private   AuthenticationService  authenticationService;
 
	@Autowired
	private UserService userService ;
    @Autowired
    private com.apps.wave.news.service.TokenBlacklistService tokenBlacklistService ;
    
    @PostMapping("/login")
    public ResponseEntity<GeneralResponse> login(@RequestBody @Valid  LoginRequest request) throws BusinessExceptions {
        return authenticationService.login(request);
    }

    @PostMapping("/signup")
    public ResponseEntity<GeneralResponse> signup(@RequestBody @Valid UserSignupDTO userSignupDTO) throws BusinessExceptions {
        userService.addUser(userSignupDTO, Role.NORMAL);
        return ResponseEntity.ok(new GeneralResponse(200,"User registered successfully"));
    }
    @PostMapping("/logout")
    public ResponseEntity<GeneralResponse> logout(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            tokenBlacklistService.blacklistToken(token);
        }

        // Clear the security context
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok(new GeneralResponse(200,"Logout successful. Token has been blacklisted."));
      
    }
    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshRequest refreshToken) {
    	return authenticationService.refreshToken(refreshToken.getRefreshToken());
    }
}
