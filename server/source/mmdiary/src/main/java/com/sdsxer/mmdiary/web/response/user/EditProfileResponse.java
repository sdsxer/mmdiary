package com.sdsxer.mmdiary.web.response.user;

import com.sdsxer.mmdiary.domain.User;
import com.sdsxer.mmdiary.dto.UserDto;
import com.sdsxer.mmdiary.web.response.SuccessResponse;

public class EditProfileResponse extends SuccessResponse {

  private UserDto user;

  public EditProfileResponse(User user) {
    this.user = new UserDto(user);
  }

  public UserDto getUser() {
    return user;
  }
}
