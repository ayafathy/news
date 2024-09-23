package com.apps.wave.news.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;



@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDto {
	private Long id;
    private String fullName;
    private String email;
    private LocalDate dateOfBirth;
    private com.apps.wave.news.entity.Role Role;
    

  
}
