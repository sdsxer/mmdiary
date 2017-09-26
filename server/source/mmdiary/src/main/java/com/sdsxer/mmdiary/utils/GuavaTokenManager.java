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
  public long getUserId(String token) {
    return 0;
  }

  @Override
  public void deleteToken(String token) {

  }
}
