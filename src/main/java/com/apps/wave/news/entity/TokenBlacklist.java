package com.apps.wave.news.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class TokenBlacklist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( length = 500)
    private String token;
    
    private LocalDateTime blacklistedAt;

    // Constructors, getters, and setters
    public TokenBlacklist() {}

    public TokenBlacklist(String token) {
        this.token = token;
        this.blacklistedAt = LocalDateTime.now();
    }

    public String getToken() {
        return token;
    }

    public LocalDateTime getBlacklistedAt() {
        return blacklistedAt;
    }
}
