package com.sdsxer.mmdiary.dto.user;

import com.sdsxer.mmdiary.domain.user.User;
import com.sdsxer.mmdiary.dto.CommonSuccessResponse;

public class LoginResponse extends CommonSuccessResponse {

  private String token;
  private User user;

  public LoginResponse(User user) {
    this.user = user;
  }
}
