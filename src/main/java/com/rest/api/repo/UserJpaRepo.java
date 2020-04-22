package com.rest.api.repo;

import java.util.Optional;

import com.rest.api.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepo extends JpaRepository<User, Long>{
  
  Optional<User> findByUid(String email);
  
}