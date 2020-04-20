package com.rest.api.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Data;

@Controller
public class HelloController {

  //1. string 반환
  @GetMapping(value = "/hello/string")
  @ResponseBody
  public String helloString(){

    return "hello syno world!!";
  }
  //2. json 타입으로 반환
  @GetMapping(value = "/hello/json")
  @ResponseBody
  public Hello helloJson(){
    Hello hello = new Hello();
    hello.setMessage("hello json world");
    return hello;
  }

  //3. page: freemaker 타입으로 반환
  @GetMapping(value = "/hello/page")
  public String helloPage(Map<String,String> map){
    
    map.put("title", "hello world");
    map.put("content", "syno world !!!");
    return "helloworld";
  }

  @Data
  public class Hello {
    private String message;
  }
}