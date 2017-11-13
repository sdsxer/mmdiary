package com.sdsxer.mmdiary;

import com.sdsxer.mmdiary.common.Constants;
import com.sdsxer.mmdiary.config.DataSourceConfig;
import javax.sql.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class MMApplicationTest {




  @Test
  public void transferTenDollars() {

    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
    ctx.getEnvironment().setActiveProfiles(Constants.CONFIG_ENV_PROD);
    ctx.register(DataSourceConfig.class);
    ctx.refresh();

    DataSource dataSource = ctx.getBean(DataSource.class);


    if(dataSource != null) {
      System.out.println("lalal");
    }
    else {
      System.out.println("ololol");
    }

//    HostInfo hostInfo = ctx.getBean(HostInfo.class);
//
//
//    if (hostInfo != null) {
//      System.out.println(hostInfo.getHostAddress());
//      System.out.println(hostInfo.getListeningPort());
//      System.out.println(hostInfo.getNginxPort());
//    } else {
//      System.out.println("ololol");
//    }
  }
}
