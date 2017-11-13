package com.sdsxer.mmdiary.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class AppConfig {

  private String staticResourceServerAddress;
  private int staticResourceServerPort;

  public String getStaticResourceServerAddress() {
    return staticResourceServerAddress;
  }

  public void setStaticResourceServerAddress(String staticResourceServerAddress) {
    this.staticResourceServerAddress = staticResourceServerAddress;
  }

  public int getStaticResourceServerPort() {
    return staticResourceServerPort;
  }

  public void setStaticResourceServerPort(int staticResourceServerPort) {
    this.staticResourceServerPort = staticResourceServerPort;
  }
}
