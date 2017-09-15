package com.sdsxer.mmdiary.controller;

import com.sdsxer.mmdiary.dto.CommonResponse;
import com.sdsxer.mmdiary.dto.CommonSuccessResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserEndpoint {

  @RequestMapping(value = "/test")
  public CommonResponse test() {
    return new CommonSuccessResponse();
  }

  @RequestMapping(value = "/user/login")
  public CommonResponse login(@RequestParam(value = "username", defaultValue = "") String username,
      @RequestParam(value = "password", defaultValue = "") String password) {
    return null;
  }

  @RequestMapping(value = "/user/logout")
  public CommonResponse logout() {
    return null;
  }
}
