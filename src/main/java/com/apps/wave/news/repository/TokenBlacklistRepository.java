package com.apps.wave.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apps.wave.news.entity.TokenBlacklist;

import java.util.Optional;

public interface TokenBlacklistRepository extends JpaRepository<TokenBlacklist, Long> {
    Optional<TokenBlacklist> findByToken(String token);
}