package com.sdsxer.mmdiary;


import com.sdsxer.mmdiary.common.Constants;
import com.sdsxer.mmdiary.config.AppConfig;
import com.sdsxer.mmdiary.config.StorageProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Application entrance
 * Created by leon on 2017/9/14.
 */
@SpringBootApplication
@EnableConfigurationProperties({StorageProperties.class, AppConfig.class})
public class MMDiaryApplication extends SpringBootServletInitializer implements
    EmbeddedServletContainerCustomizer {

  private static Logger logger = LoggerFactory.getLogger(MMDiaryApplication.class);

//  @Autowired
//  private HostInterface hostInfo;

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

  // custom server config
  @Override
  public void customize(ConfigurableEmbeddedServletContainer container) {
//    logger.info("Service listening port: {}", hostInfo.getListeningPort());
//    container.setPort(hostInfo.getListeningPort());
  }

  // set 404 response handler
//  @Bean
//  public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
//    ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
//    dispatcherServlet.setThrowExceptionIfNoHandlerFound(true); // 当出现 404 错误时, 直接抛出异常
//    // 不要为工程中的资源文件建立映射
//    return registration;
//  }
}
