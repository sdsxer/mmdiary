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

  /**
   * 如果hibernate.hbm2ddl.auto属性为none，数据库会执行schema.sql创建，并且加载data.sql导入数据
   * 如果hibernate.hbm2ddl.auto属性为create或create-drop，嵌入式数据库会从Entity自动创建表，并且加载import.sql
   * @return
   */
  @Profile("dev")
  @Bean
  public DataSource dataSourceEmbedded() {
    return new EmbeddedDatabaseBuilder()
        .setType(EmbeddedDatabaseType.H2)
//        .addScript("classpath:schema.sql")
//        .addScript("classpath:import.sql")
//        .addScript("classpath:data.sql")
        .setScriptEncoding("utf-8")
        .build();
  }

  @Profile("dev")
  @Bean
  public ServletRegistrationBean h2servletRegistration() {
    ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
    registration.addUrlMappings("/console/*");
    return registration;
  }

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
