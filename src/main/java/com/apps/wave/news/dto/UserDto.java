package com.apps.wave.news.dto;

import java.time.LocalDate;

import com.apps.wave.news.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private Long id;
    private LocalDate dateOfBirth;
    private String fullName ;
	private String token;
    private String email;
    public UserDto(User user){
        setId(user.getId());
        setFullName(user.getFullName());
        setEmail(user.getEmail());
    }


}
