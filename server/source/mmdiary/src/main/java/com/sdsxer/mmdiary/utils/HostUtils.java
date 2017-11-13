package com.sdsxer.mmdiary.utils;

import com.sdsxer.mmdiary.config.AppConfig;
import java.io.File;
import javax.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HostUtils {

  private static HostUtils hostUtils;

  @Autowired
  private AppConfig appConfig;

  @PostConstruct
  public void init() {
    hostUtils = this;
    hostUtils.appConfig = this.appConfig;
  }

  public static String getIntegrateUrl(String relativePath) {
    StringBuilder builder = new StringBuilder();
    builder.append("http://");
    builder.append(hostUtils.appConfig.getStaticResourceServerAddress());
    builder.append(":");
    builder.append(hostUtils.appConfig.getStaticResourceServerPort());
    if(!StringUtils.startsWith(relativePath, File.separator)) {
      builder.append(File.separator);
    }
    builder.append(relativePath);
    return builder.toString();
  }
}
