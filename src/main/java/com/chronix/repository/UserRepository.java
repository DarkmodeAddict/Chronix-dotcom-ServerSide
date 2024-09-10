package com.chronix.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chronix.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    
}
