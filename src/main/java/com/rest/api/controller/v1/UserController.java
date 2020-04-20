package com.rest.api.controller.v1;

import java.util.List;

import com.rest.api.entity.User;
import com.rest.api.repo.UserJpaRepo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api(tags = {"1. User"})
@RequiredArgsConstructor
@RequestMapping(value = "/v1")
@RestController
public class UserController {

  private final UserJpaRepo userJpaRepo;

  @ApiOperation(value = "회원 조회", notes = "모든 회원을 조회한다")
  @GetMapping(value = "/user")
  public List<User> findAllUser(){
    return userJpaRepo.findAll();
  }

  @ApiOperation(value = "회원 입력", notes = "회원을 입력한다.")
  @PostMapping(value = "/user")
  public User save(@ApiParam(value = "회원아이디", required = true) @RequestParam String uid,
  @ApiParam(value = "회원이름", required = true) @RequestParam String name){
    User user = User.builder()
    .uid("syno@kt.com")
    .name("syno").build();
    return userJpaRepo.save(user);
  }
}