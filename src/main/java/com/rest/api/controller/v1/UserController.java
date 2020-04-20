package com.rest.api.controller.v1;

import java.util.List;

import com.rest.api.entity.User;
import com.rest.api.repo.UserJpaRepo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(value = "/v1")
@RestController
public class UserController {

  private final UserJpaRepo userJpaRepo;

  @GetMapping(value = "/user")
  public List<User> findAllUser(){
    return userJpaRepo.findAll();
  }

  @PostMapping(value = "/user")
  public User save(){
    User user = User.builder()
    .uid("syno@kt.com")
    .name("syno").build();
    return userJpaRepo.save(user);
  }
}