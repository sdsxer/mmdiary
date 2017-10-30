package com.sdsxer.mmdiary.dto;


import com.sdsxer.mmdiary.domain.User;
import com.sdsxer.mmdiary.utils.HostUtils;

public class UserDto {

  private long id;
  private String name;
  private String mobile;
  private String avatarUrl;
  private int gender;

  public UserDto(User user) {
    this.id = user.getId();
    this.name = user.getName();
    this.mobile = user.getMobile();
    this.avatarUrl = HostUtils.getIntegrateUrl(user.getAvatarUrl());
    this.gender = user.getGender();
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getMobile() {
    return mobile;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public int getGender() {
    return gender;
  }
}
