package com.apps.wave.news.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.apps.wave.news.enums.Role;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserRequest  implements Serializable {

    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String fullName ;
    @NotBlank 
    private String password ;
    @NotNull 
    private LocalDate dateOfBirth ;
    @NotNull 
    private Role role ;


}
