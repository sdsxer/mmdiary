package com.sdsxer.mmdiary;


import com.sdsxer.mmdiary.common.Constants;
import com.sdsxer.mmdiary.config.StorageProperties;
import com.sdsxer.mmdiary.domain.HostInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Application entrance
 * Created by leon on 2017/9/14.
 */
@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class MMDiaryApplication extends SpringBootServletInitializer implements
    EmbeddedServletContainerCustomizer {

  private static Logger logger = LoggerFactory.getLogger(MMDiaryApplication.class);

  @Autowired
  private HostInfo hostInfo;

  // war entry
  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    application.profiles(Constants.CONFIG_ENV_DEV, Constants.CONFIG_ORM_JPA);
    return application.sources(MMDiaryApplication.class);
  }

  // jar entry
  public static void main(String[] args) throws Exception {
    SpringApplication.run(MMDiaryApplication.class, args);
  }

  @Override
  public void customize(ConfigurableEmbeddedServletContainer container) {
    logger.info("Server listening port: {}", hostInfo.getListeningPort());
    container.setPort(hostInfo.getListeningPort());
  }
}
