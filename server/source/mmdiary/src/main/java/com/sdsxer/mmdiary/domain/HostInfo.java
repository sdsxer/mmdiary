package com.sdsxer.mmdiary.domain;

public class HostInfo {

  private String hostAddress;
  private int listeningPort;
  private int nginxPort;

  public HostInfo(String hostAddress, int listeningPort, int nginxPort) {
    this.hostAddress = hostAddress;
    this.listeningPort = listeningPort;
    this.nginxPort = nginxPort;
  }

  public String getHostAddress() {
    return hostAddress;
  }

  public void setHostAddress(String hostAddress) {
    this.hostAddress = hostAddress;
  }

  public int getListeningPort() {
    return listeningPort;
  }

  public void setListeningPort(int listeningPort) {
    this.listeningPort = listeningPort;
  }

  public int getNginxPort() {
    return nginxPort;
  }

  public void setNginxPort(int nginxPort) {
    this.nginxPort = nginxPort;
  }
}
