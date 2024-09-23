package com.apps.wave.news.security;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.apps.wave.news.entity.User;
import com.apps.wave.news.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private  UserRepository userRepository;;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		  User user = userRepository.findByEmailIgnoreCase(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		String role =user.getRole().getName();
		return  org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(role)
                .build();
	}
}
