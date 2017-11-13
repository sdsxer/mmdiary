package com.sdsxer.mmdiary.config;

import com.sdsxer.mmdiary.common.Constants;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.h2.server.web.WebServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jndi.JndiObjectFactoryBean;

/**
 * Data source config
 * Created by leon on 2017/9/15.
 */
@Configuration
public class DataSourceConfig {

  /**
   * schema.sql and data.sql is spring's default script
   * import.sql is hibernate's default script
   *
   * spring will set hibernate.hbm2ddl.auto to create or create-drop by default when using hibernate
   * when it find an embedded data source was defined.
   * @return
   */
  @Profile(Constants.CONFIG_ENV_DEV)
  @Bean(destroyMethod = "shutdown")
  @Primary
  public DataSource dataSourceEmbedded() {
    return new EmbeddedDatabaseBuilder()
        .setType(EmbeddedDatabaseType.H2)
        .setScriptEncoding("UTF-8")
        .build();
  }

  /**
   * this enables embedded h2 database access by console
   * @return
   */
  @Profile(Constants.CONFIG_ENV_DEV)
  @Bean
  public ServletRegistrationBean h2servletRegistration() {
    ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
    registration.addUrlMappings("/console/*");
    return registration;
  }

  /**
   * use connection pool under qa environment
   * @return
   */
  @Profile(Constants.CONFIG_ENV_QA)
  @Bean(destroyMethod = "close")
  public DataSource dataSourcePool() {
    BasicDataSource basicDataSource = new BasicDataSource();
    basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
    basicDataSource.setUrl("jdbc:mysql://localhost:3306/mmdiary");
    basicDataSource.setUsername("root");
    basicDataSource.setPassword("leon@1988");
    basicDataSource.setInitialSize(5);
    basicDataSource.setMaxActive(10);
    return basicDataSource;
  }

  /**
   * use jndi under prod environment
   * @return
   */
  @Profile(Constants.CONFIG_ENV_PROD)
  @Bean
  public DataSource dataSourceLookup() {
    JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
    jndiObjectFactoryBean.setJndiName("java:comp/env/jdbc/mmdiary");
    jndiObjectFactoryBean.setResourceRef(true);
    jndiObjectFactoryBean.setProxyInterface(javax.sql.DataSource.class);
    return (DataSource) jndiObjectFactoryBean.getObject();
  }
}
