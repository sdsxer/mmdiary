package com.sdsxer.mmdiary.controller;

import com.sdsxer.mmdiary.domain.user.User;
import com.sdsxer.mmdiary.dto.CommonResponse;
import com.sdsxer.mmdiary.dto.user.LoginResponse;
import com.sdsxer.mmdiary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/user/login", method = RequestMethod.POST)
  public User login(@RequestParam(value = "username", defaultValue = "") String username,
      @RequestParam(value = "password", defaultValue = "") String password) {

//    LoginResponse loginResponse = new LoginResponse(userService.login(username, password));
//    return loginResponse;

    return userService.login(username, password);
  }

  @RequestMapping(value = "/user/logout")
  public CommonResponse logout() {
    return null;
  }
}
