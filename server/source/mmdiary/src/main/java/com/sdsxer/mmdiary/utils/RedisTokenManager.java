package com.sdsxer.mmdiary.utils;

import com.google.common.base.Strings;
import com.sdsxer.mmdiary.common.Constants;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Token缓存的Redis解决方案
 * Created by leon on 2017/9/24.
 */
@Component
@Primary
public class RedisTokenManager implements TokenManager {

  @Autowired
  private TokenGenerator tokenGenerator;

  @Autowired
  private RedisTemplate<String, Long> redis;

  private final String TEST_TOKEN = "gdgFPDye6V8qXJRLctEXU";
  private final long TEST_USER_ID = 1;

  @Override
  public String createToken(long userId) {
    // check if test user
    if(userId == TEST_USER_ID) {
      return TEST_TOKEN;
    }
    String token = tokenGenerator.next();
    // 存储到redis并设置过期时间
    redis.boundValueOps(token).set(userId, Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
    return token;
  }

  @Override
  public boolean checkToken(String token) {
    if(Strings.isNullOrEmpty(token)) {
      return false;
    }
    if(StringUtils.equals(TEST_TOKEN, token)) {
      return true;
    }
    long userId = redis.boundValueOps(token).get();
    if(userId <= 0) {
      return false;
    }
    redis.boundValueOps(token).expire(Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
    return true;
  }

  @Override
  public long getUserId(String token) {
    if(Strings.isNullOrEmpty(token)) {
      return 0;
    }
    if(StringUtils.equals(TEST_TOKEN, token)) {
      return TEST_USER_ID;
    }
    return redis.boundValueOps(token).get();
  }

  @Override
  public void deleteToken(String token) {
    if(StringUtils.equals(TEST_TOKEN, token)) {
      return;
    }
    redis.delete(token);
  }
}
