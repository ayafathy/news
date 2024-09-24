package com.apps.wave.news.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER")
@Entity
public class User {

    public User(String fullName, String email, LocalDate dateOfBirth) {
		super();
		this.fullName = fullName;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
	}
	@GeneratedValue
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

   
    @Column(name = "FULL_NAME", nullable = false, length = 250)
    private String fullName;

    @Column(name = "PASSWORD", nullable = false, length = 150)
    private String password ;
    @Column(name = "REFRESH_TOKEN" , length = 500)
    private String refreshToken;
    @Column(name = "EMAIL", nullable = false, length = 250)
    private String email;
    @Column(name = "DATE_OF_BIRTH", nullable = false, length = 250)
    private LocalDate dateOfBirth;
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Role Role;
    

  
}
