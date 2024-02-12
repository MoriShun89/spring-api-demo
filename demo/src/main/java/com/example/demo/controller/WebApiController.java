package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// RestAPI用のController
@RestController
@RequestMapping("api")
public class WebApiController {
  
  /*
   * "api/hello"にGETリクエストが送信された場合、"SpringBoot!"という文字列をHTTPレスポンスを返す。
   */
  @RequestMapping("hello")
  private String hello() {
    return "SpringBoot!";
  }

}
