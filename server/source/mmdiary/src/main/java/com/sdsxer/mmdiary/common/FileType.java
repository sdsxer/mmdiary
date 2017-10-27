package com.sdsxer.mmdiary.common;

public enum FileType {

  NONE("none"),
  IMAGE("image"),
  VIDEO("video"),
  AUDIO("audio"),
  WORD("word"),
  EXCEL("excel"),
  PDF("pdf"),
  PPT("ppt"),
  TXT("txt"),
  UNKNOWN("unknown");

  private String value;

  private FileType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
