package com.sdsxer.mmdiary.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * 数据源配置信息
 * Created by leon on 2017/9/15.
 */
@Configuration
public class DataSourceConfig {

  @Bean(destroyMethod = "shutdown")
  public DataSource embeddedDataSource() {
    return new EmbeddedDatabaseBuilder()
        .setType(EmbeddedDatabaseType.H2)
        .addDefaultScripts()
        .build();
  }
}
