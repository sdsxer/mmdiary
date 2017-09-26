package com.sdsxer.mmdiary.web;

import com.sdsxer.mmdiary.common.ErrorCode;
import com.sdsxer.mmdiary.domain.User;
import com.sdsxer.mmdiary.service.UserService;
import com.sdsxer.mmdiary.utils.TokenManager;
import com.sdsxer.mmdiary.web.response.BaseResponse;
import com.sdsxer.mmdiary.web.response.FailureResponse;
import com.sdsxer.mmdiary.web.response.SuccessResponse;
import com.sdsxer.mmdiary.web.response.user.LoginResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends BaseController {

  @Autowired
  private UserService userService;

  @Autowired
  private TokenManager tokenManager;

  @RequestMapping(value = "/user/login", method = RequestMethod.POST)
  public BaseResponse login(@RequestParam(value = "mobile") String mobile,
      @RequestParam(value = "password") String password) {

    BaseResponse loginResponse;

    if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
      loginResponse = new FailureResponse(ErrorCode.User.EMPTY_MOBILE_OR_PASSWORD);
      return loginResponse;
    }

    User user = userService.login(mobile);
    if(user == null) {
      loginResponse = new FailureResponse(ErrorCode.User.USER_NOT_EXIST);
      return loginResponse;
    }

    if(!user.getPassword().equals(password)) {
      loginResponse = new FailureResponse(ErrorCode.User.PASSWORD_NOT_CORRECT);
      return loginResponse;
    }

    loginResponse = new LoginResponse(tokenManager.createToken(user.getId()), user);
    return loginResponse;
  }

  @RequestMapping(value = "/user/logout", method = RequestMethod.POST)
  public BaseResponse logout(@RequestParam(value = "token") String token) {

    BaseResponse logoutResponse;
    if(!tokenManager.checkToken(token)) {
      logoutResponse = new FailureResponse(ErrorCode.User.INVALID_USER);
    } else {
      tokenManager.deleteToken(token);
      logoutResponse = new SuccessResponse();
    }
    return logoutResponse;
  }
}
