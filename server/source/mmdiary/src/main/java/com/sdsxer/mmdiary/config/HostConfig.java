package com.sdsxer.mmdiary.config;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HostConfig implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {

  private static final Logger logger = LoggerFactory.getLogger(HostConfig.class);

  private String hostAddress ;
  private int listeningPort;

  public HostConfig() {
    try {
      hostAddress = InetAddress.getLocalHost().getHostAddress();
      logger.info("Server address: {}", hostAddress);
    } catch (UnknownHostException e) {
      logger.error("Get server host exception", e);
    }
  }

  @Override
  public void onApplicationEvent(EmbeddedServletContainerInitializedEvent event) {
    listeningPort = event.getEmbeddedServletContainer().getPort();
    logger.info("App listening port: {}", listeningPort);
  }


  public String getHostAddress() {
    return hostAddress;
  }

  public int getListeningPort() {
    return listeningPort;
  }
}
