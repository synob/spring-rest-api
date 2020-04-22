package com.rest.api.advice.exception;

public class CAuthenticationEntryPointException extends RuntimeException {

  
  private static final long serialVersionUID = 1L;

  public CAuthenticationEntryPointException() {
    super();
  }

  public CAuthenticationEntryPointException(String message) {
    super(message);
  }

  public CAuthenticationEntryPointException(String message, Throwable cause) {
    super(message, cause);
  }

    
}