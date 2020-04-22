package com.rest.api.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class JwtAuthenticationFilter extends GenericFilterBean {

  private  JwtTokenProvider jwtTokenprovider;

  public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider){
    this.jwtTokenprovider = jwtTokenProvider;
  }

  // Request로 들어오는 Jwt Token의 유효성을 검증(jwtTokenProvider.validateToken)하는 filter를 filterChain에 등록합니다.
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
      throws IOException, ServletException {
    String token = jwtTokenprovider.resolveToken((HttpServletRequest) request);
    if(token != null && jwtTokenprovider.validateToken(token)){
      Authentication auth = jwtTokenprovider.gAuthentication(token);
      SecurityContextHolder.getContext().setAuthentication(auth);

    }
    filterChain.doFilter(request, response);
  }



  

}