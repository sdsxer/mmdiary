package com.sdsxer.mmdiary.utils;

import com.sdsxer.mmdiary.domain.HostInfo;
import java.io.File;
import javax.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HostUtils {

  private static HostUtils hostUtils;

  @Autowired
  private HostInfo hostInfo;

  @PostConstruct
  public void init() {
    hostUtils = this;
    hostUtils.hostInfo = this.hostInfo;
  }

  public static String getIntegrateUrl(String relativePath) {
    StringBuilder builder = new StringBuilder();
    builder.append("http://");
    builder.append(hostUtils.hostInfo.getHostAddress());
    builder.append(":");
    builder.append(hostUtils.hostInfo.getNginxPort());
    if(!StringUtils.startsWith(relativePath, File.separator)) {
      builder.append(File.separator);
    }
    builder.append(relativePath);
    return builder.toString();
  }
}
