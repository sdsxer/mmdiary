package com.sdsxer.mmdiary.config;

import com.sdsxer.mmdiary.common.Constants;
import com.sdsxer.mmdiary.domain.HostInfo;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class HostConfig {

//  private static final Logger logger = LoggerFactory.getLogger(HostConfig.class);

  @Bean
  @Profile(Constants.CONFIG_ENV_DEV)
  public HostInfo devHostInfo() {
    return new HostInfo(Constants.DOMAIN_NAME_DEV, Constants.LISTENING_PORT_DEV,
        Constants.NGINX_PORT_DEV);
  }

  @Bean
  @Profile(Constants.CONFIG_ENV_QA)
  public HostInfo qaHostInfo() {
    return new HostInfo(Constants.DOMAIN_NAME_QA, Constants.LISTENING_PORT_QA,
        Constants.NGINX_PORT_QA);
  }

  @Bean
  @Profile(Constants.CONFIG_ENV_PROD)
  public HostInfo prodHostInfo() {
    return new HostInfo(Constants.DOMAIN_NAME_PROD, Constants.LISTENING_PORT_PROD,
        Constants.NGINX_PORT_PROD);
  }
}
