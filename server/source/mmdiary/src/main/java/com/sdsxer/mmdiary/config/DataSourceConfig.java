package com.sdsxer.mmdiary.config;

import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.h2.server.web.WebServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jndi.JndiObjectFactoryBean;

/**
 * 数据源配置信息
 * Created by leon on 2017/9/15.
 */
@Configuration
public class DataSourceConfig {

  @Profile("dev")
  @Bean
  public DataSource dataSourceEmbedded() {
    return new EmbeddedDatabaseBuilder()
        .setType(EmbeddedDatabaseType.H2)
        .addDefaultScripts()
        .build();
  }

//  @Profile("dev")
//  @Bean
//  public ServletRegistrationBean h2servletRegistration() {
//    ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
//    registration.addUrlMappings("/console/*");
//    return registration;
//  }

  @Profile("qa")
  @Bean
  public BasicDataSource dataSourcePool() {
    BasicDataSource basicDataSource = new BasicDataSource();
    basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
    basicDataSource.setUrl("jdbc:mysql://localhost:3306/mmdiary");
    basicDataSource.setUsername("root");
    basicDataSource.setPassword("leon@1988");
    basicDataSource.setInitialSize(5);
    basicDataSource.setMaxActive(10);
    return basicDataSource;
  }

  @Profile("prod")
  @Bean
  public DataSource dataSourceLookup() {
    JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
    jndiObjectFactoryBean.setJndiName("java:comp/env/jdbc/mmdiary");
    jndiObjectFactoryBean.setResourceRef(true);
    jndiObjectFactoryBean.setProxyInterface(javax.sql.DataSource.class);
    return (DataSource) jndiObjectFactoryBean.getObject();
  }
}
