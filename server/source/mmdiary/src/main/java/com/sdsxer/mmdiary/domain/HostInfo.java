package com.sdsxer.mmdiary.domain;

public class HostInfo {

  private String hostAddress;
  private int listeningPort;

  public HostInfo(String hostAddress, int listeningPort) {
    this.hostAddress = hostAddress;
    this.listeningPort = listeningPort;
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
}
