package com.sdsxer.mmdiary.config;

import com.sdsxer.mmdiary.common.Constants;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

@Configuration
@Profile(Constants.CONFIG_ORM_JPA)
public class JpaConfig {

  @Autowired
  private DataSource dataSource;

  @Autowired
  private Properties properties;

  @Bean
  public LocalSessionFactoryBean hibernateSqlSessionFactory() {
    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
    sessionFactory.setDataSource(dataSource);
    sessionFactory.setPackagesToScan(new String[] {"com.sdsxer.mmdiary.repository"});
    sessionFactory.setHibernateProperties(properties);
    return sessionFactory;
  }


  @Bean
  @Profile(Constants.CONFIG_ENV_DEV)
  public Properties devProperties() {
    Properties properties = new Properties();
    properties.setProperty("hibernate.show_sql", "true");
    properties.setProperty("hibernate.hbm2ddl.auto", "validate");
    properties.setProperty("dialect", "org.hibernate.dialect.H2Dialect");
    return properties;
  }

  @Bean
  @Profile(Constants.CONFIG_ENV_QA)
  public Properties qaProperties() {
    Properties properties = new Properties();
    properties.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
    return properties;
  }

  @Bean
  @Profile(Constants.CONFIG_ENV_PROD)
  public Properties prodProperties() {
    Properties properties = new Properties();
    properties.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
    return properties;
  }
}
