package com.sdsxer.mmdiary.utils;

import com.google.common.base.Strings;
import com.sdsxer.mmdiary.common.Constants;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
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

  private RedisTemplate<String, Long> redis;

  @Autowired
  public void setRedis(RedisTemplate redis) {
    this.redis = redis;
    // 泛型设置成Long后必须更改对应的序列化方案
//    redis.setKeySerializer(new JdkSerializationRedisSerializer());
  }


  @Override
  public String createToken(long userId) {
    String token = tokenGenerator.next();
    // 存储到redis并设置过期时间
    redis.boundValueOps(token).set(userId, Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
    return null;
  }

  @Override
  public boolean checkToken(String token) {
    if(Strings.isNullOrEmpty(token)) {
      return false;
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
    return redis.boundValueOps(token).get();
  }

  @Override
  public void deleteToken(String token) {
    redis.delete(token);
  }
}
