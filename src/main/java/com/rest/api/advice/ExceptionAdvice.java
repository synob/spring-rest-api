package com.rest.api.advice;

import javax.servlet.http.HttpServletRequest;

import com.rest.api.advice.exception.CAuthenticationEntryPointException;
import com.rest.api.advice.exception.CEmailSigninFailedException;
import com.rest.api.advice.exception.CUserNotFoundException;
import com.rest.api.model.response.CommonResult;
import com.rest.api.service.ResonpseService;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

  private final ResonpseService responseService;

  private final MessageSource messagesource;

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  protected CommonResult defaultException(HttpServletRequest req, Exception e){
    return responseService.getFailResult(Integer.valueOf(getMessage("unKnown.code")), getMessage("unKnown.msg"));
  }

  @ExceptionHandler(CUserNotFoundException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  protected CommonResult userNotFoundException(HttpServletRequest req, CUserNotFoundException e){
    return responseService.getFailResult(Integer.valueOf(getMessage("userNotFound.code")), getMessage("userNotFound.msg"));
  }

  @ExceptionHandler(CEmailSigninFailedException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  protected CommonResult emailSignFailed(HttpServletRequest req, CEmailSigninFailedException e){
    return responseService.getFailResult(Integer.valueOf(getMessage("emailSignFailed.code")),
    getMessage("emailSignFailed.msg"));
  }

  @ExceptionHandler(CAuthenticationEntryPointException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  protected CommonResult authencationEntryPointException(HttpServletRequest req, CAuthenticationEntryPointException e){
    return responseService.getFailResult(Integer.valueOf(getMessage("entryPointException.code")),
    getMessage("entryPointException.msg"));
  }

  @ExceptionHandler(AccessDeniedException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  protected CommonResult accessdeniedException(HttpServletRequest req, AccessDeniedException e){
    return responseService.getFailResult(Integer.valueOf(getMessage("accessDenied.code")),
    getMessage("accessDenied.msg"));
  }

  private String getMessage(String code){
    return getMessage(code, null);
  }

  private String getMessage(String code, Object[] args){
    return messagesource.getMessage(code, args, LocaleContextHolder.getLocale());
  }
}