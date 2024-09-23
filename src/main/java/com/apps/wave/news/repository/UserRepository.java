package com.apps.wave.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apps.wave.news.entity.User;


import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmailIgnoreCase(String email);
	Optional<User> findByFullName(String userName);
	User findByEmailIgnoreCase(String email);
}
