package com.sdsxer.mmdiary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis config
 * Created by leon on 2017/9/26.
 */
@Configuration
public class RedisConfig {

  @Bean
  public JedisConnectionFactory jedisConnectionFactory() {
    JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
    jedisConFactory.setHostName("localhost");
    jedisConFactory.setPort(6379);
    return jedisConFactory;
  }

  @Bean
  public RedisTemplate<String, Long> redisTemplate() {
    RedisTemplate<String, Long> template = new RedisTemplate<String, Long>();
    template.setConnectionFactory(jedisConnectionFactory());
    template.setKeySerializer(new StringRedisSerializer());
    template.setValueSerializer(new JdkSerializationRedisSerializer());
    return template;
  }
}
