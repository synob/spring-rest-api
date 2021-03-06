package com.rest.api.controller.v1;

import com.rest.api.advice.exception.CUserNotFoundException;
import com.rest.api.entity.User;
import com.rest.api.model.response.CommonResult;
import com.rest.api.model.response.ListResult;
import com.rest.api.model.response.SingleResult;
import com.rest.api.repo.UserJpaRepo;
import com.rest.api.service.ResonpseService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api(tags = { "2. User" })
@RequiredArgsConstructor
@RequestMapping(value = "/v1")
@RestController
public class UserController {

  private final ResonpseService responseService;
  private final UserJpaRepo userJpaRepo;

  // @ApiOperation(value = "회원 조회", notes = "모든 회원을 조회한다")
  // @GetMapping(value = "/user")
  // public List<User> findAllUser(){
  //   return userJpaRepo.findAll();
  // }
  
  @ApiImplicitParams({@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공후 access_token", required = true, dataType = "String", paramType = "header")})
  @ApiOperation(value = "회원 목록 조회", notes = "모든 회원을 조회한다")
  @GetMapping(value = "/users")
  public ListResult<User> findAllUser(){
    return responseService.getListResult(userJpaRepo.findAll());
  }

  @ApiImplicitParams({@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공후 access_token", required = true, dataType = "String", paramType = "header")})
  @ApiOperation(value = "회원 단건 조회", notes = "userId 회원을 조회한다")
  @GetMapping(value = "/user/{msrl}")
  public SingleResult<User> findUserById(
    @ApiParam(value = "회원아이디", required = true) @PathVariable long msrl
   ,@ApiParam(value = "언어", defaultValue = "ko") @RequestParam String lang
    ) {
    return responseService.getSingleResult(userJpaRepo.findById(msrl).orElseThrow(CUserNotFoundException::new));
  }

  @ApiImplicitParams({@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공후 access_token", required = true, dataType = "String", paramType = "header")})
  @ApiOperation(value = "회원 입력", notes = "회원을 입력한다.")
  @PostMapping(value = "/user")
  public SingleResult<User> save(@ApiParam(value = "회원아이디", required = true) @RequestParam String uid,
  @ApiParam(value = "회원이름", required = true) @RequestParam String name){
    User user = User.builder()
    .uid(uid)
    .name(name).build();
    return responseService.getSingleResult(userJpaRepo.save(user));
  }

  @ApiImplicitParams({@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공후 access_token", required = true, dataType = "String", paramType = "header")})
  @ApiOperation(value = "회원 수정", notes = "회원을 수정한다.")
  @PutMapping(value = "/user/{msrl}")
  public SingleResult<User> modify(@ApiParam(value = "회원번호", required = true) @RequestParam long msrl,
  @ApiParam(value = "회원아이디", required = true) @RequestParam String uid,
  @ApiParam(value = "회원이름", required = true) @RequestParam String name){
      User user = User.builder()
      .msrl(msrl).uid(uid).name(name).build();
      return responseService.getSingleResult(userJpaRepo.save(user));
  }

  @ApiImplicitParams({@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공후 access_token", required = true, dataType = "String", paramType = "header")})
  @ApiOperation(value = "회원 삭제", notes = "회원을 삭제한다.")
  @DeleteMapping(value = "/user/{msrl}")
  public CommonResult delete(@ApiParam(value = "회원번호", required = true) @RequestParam long msrl){
    userJpaRepo.deleteById(msrl);
    return responseService.getSuccessResult();

  }
}