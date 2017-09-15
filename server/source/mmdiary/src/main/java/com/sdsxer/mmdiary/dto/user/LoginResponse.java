package com.sdsxer.mmdiary.dto.user;

import com.sdsxer.mmdiary.domain.user.MMUser;
import com.sdsxer.mmdiary.dto.CommonSuccessResponse;

public class LoginResponse extends CommonSuccessResponse {

  private MMUser user;

  public LoginResponse(MMUser user) {
    this.user = user;
  }
}
