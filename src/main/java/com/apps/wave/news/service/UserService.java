package com.apps.wave.news.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apps.wave.news.dto.AddUserRequest;
import com.apps.wave.news.dto.GeneralResponse;
import com.apps.wave.news.dto.UserDto;
import com.apps.wave.news.dto.UserSignupDTO;
import com.apps.wave.news.entity.User;
import com.apps.wave.news.enums.Role;
import com.apps.wave.news.exception.BusinessExceptions;
import com.apps.wave.news.repository.UserRepository;

@Service
public class UserService {

   @Autowired
    private  UserRepository userRepository;
   @Autowired
    private PasswordEncoder passwordEncoder;
   

 


    public ResponseEntity<GeneralResponse> list(Pageable pageable){
        return new GeneralResponse().response(userRepository.findAll(pageable));
    }

   
    @Transactional
    public void deleteUser(Long id) {
        // Check if the user exists
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            // Delete user if found
            userRepository.deleteById(id);
        } else {
            // Handle case when the user doesn't exist
            throw new BusinessExceptions("User with ID " + id + " not found");
        }
    }
    public User addUser(UserSignupDTO request, Role role) throws BusinessExceptions {
    	if (userRepository.existsByEmailIgnoreCase(request.getEmail())) {
            throw new BusinessExceptions("email.exist");
        }
        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setDateOfBirth(request.getDateOfBirth());
        user.setRole(new com.apps.wave.news.entity.Role(role.getValue()));

        return userRepository.save(user);
    }


	public void  addUser(AddUserRequest request) throws BusinessExceptions {
		addUser( UserSignupDTO.builder().email(request.getEmail()).fullName(request.getFullName())
				.dateOfBirth(request.getDateOfBirth()).password(request.getPassword()).build(),request.getRole());
	
	}
}
