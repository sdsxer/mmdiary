package com.sdsxer.mmdiary.web.response.user;

import com.sdsxer.mmdiary.domain.User;
import com.sdsxer.mmdiary.dto.UserDto;
import com.sdsxer.mmdiary.web.response.SuccessResponse;

public class LoginResponse extends SuccessResponse {

  private String token;
  private UserDto user;

  public LoginResponse(String token, User user) {
    this.user = new UserDto(user);
  }

  public UserDto getUser() {
    return user;
  }

  public void setUser(UserDto user) {
    this.user = user;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
