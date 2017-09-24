package com.sdsxer.mmdiary.utils;

import org.springframework.stereotype.Component;

/**
 * Created by leon on 2017/9/24.
 */
@Component
public class GuavaTokenManager implements TokenManager {

  @Override
  public String createToken(long userId) {
    return null;
  }

  @Override
  public boolean checkToken(String token) {
    return false;
  }

  @Override
  public String getToken(String authentication) {
    return null;
  }

  @Override
  public void deleteToken(long userId) {

  }
}
