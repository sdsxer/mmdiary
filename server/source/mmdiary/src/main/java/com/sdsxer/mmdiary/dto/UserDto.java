package com.sdsxer.mmdiary.dto;


import com.sdsxer.mmdiary.domain.User;

public class UserDto {

  private String name;
  private String mobile;
  private String avatarUrl;
  private int gender;

  public UserDto(User user) {
    this.name = user.getName();
    this.mobile = user.getMobile();
    this.avatarUrl = user.getAvatarUrl();
    this.gender = user.getGender();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }

  public int getGender() {
    return gender;
  }

  public void setGender(int gender) {
    this.gender = gender;
  }
}
