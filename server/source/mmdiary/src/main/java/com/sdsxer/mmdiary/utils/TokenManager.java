package com.sdsxer.mmdiary.utils;

/**
 * Created by leon on 2017/9/24.
 */
public interface TokenManager {

  /**
   * 创建Token
   * @param userId
   * @return
   */
  public String createToken(long userId);

  /**
   * 检查Token的有效性
   * @param token
   * @return
   */
  public boolean checkToken(String token);


  /**
   * 返回Token对应的用户id
   * @param token
   * @return
   */
  public long getUserId(String token);

  /**
   * 删除Token
   * @param token
   */
  public void deleteToken(String token);
}
