package com.rest.api.advice;

import javax.servlet.http.HttpServletRequest;

import com.rest.api.advice.exception.CUserNotFoundException;
import com.rest.api.model.response.CommonResult;
import com.rest.api.service.ResonpseService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

  private final ResonpseService responseService;

  // @ExceptionHandler(Exception.class)
  // @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  // protected CommonResult defaultException(HttpServletRequest req, Exception e){
  //   return responseService.getFailResult();
  // }

  @ExceptionHandler(CUserNotFoundException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  protected CommonResult defaultException(HttpServletRequest req, CUserNotFoundException e){
    return responseService.getFailResult();
  }
}