package com.apps.wave.news.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class UserSignupDTO {
	 @NotBlank
	    private String fullName;
	    
	    @Email
	    @NotBlank
	    private String email;

	    @NotBlank
	    private String password;

	    @NotNull
	    private LocalDate dateOfBirth;

}
