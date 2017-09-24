package com.sdsxer.mmdiary.utils;

/**
 * Created by leon on 2017/9/24.
 */
public interface TokenManager {


  public String createToken(long userId);

  public boolean checkToken(String token);

  public String getToken(String authentication);

  public void deleteToken(long userId);
}
