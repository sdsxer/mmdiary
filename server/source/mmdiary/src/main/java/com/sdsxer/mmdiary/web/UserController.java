package com.sdsxer.mmdiary.web;

import com.sdsxer.mmdiary.service.UserService;
import com.sdsxer.mmdiary.web.response.BaseResponse;
import com.sdsxer.mmdiary.web.response.user.LoginResponse;
import org.apache.commons.lang3.StringUtils;
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
  public BaseResponse login(@RequestParam(value = "mobile") String mobile,
      @RequestParam(value = "password") String password) {

    if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {

    }

//      Map<String, User> map = new HashMap<>();
//      map.put("user", userService.login(mobile, password));

    BaseResponse loginResponse = new LoginResponse("lalala", null);

//    return userService.login(mobile, password);


    return loginResponse;
  }

  @RequestMapping(value = "/user/logout")
  public BaseResponse logout(@RequestParam(value = "token") String token) {
    return null;
  }
}
