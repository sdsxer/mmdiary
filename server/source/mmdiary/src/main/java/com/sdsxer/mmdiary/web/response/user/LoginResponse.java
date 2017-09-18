package com.sdsxer.mmdiary.web.response.user;

import com.sdsxer.mmdiary.dto.user.UserDto;
import com.sdsxer.mmdiary.web.response.SuccessResponse;

public class LoginResponse extends SuccessResponse {

  private String token;
  private UserDto user;

  public LoginResponse(String token, UserDto user) {
    this.token = token;
    this.user = user;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public UserDto getUser() {
    return user;
  }

  public void setUser(UserDto user) {
    this.user = user;
  }
}
