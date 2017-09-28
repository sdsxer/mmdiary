package com.sdsxer.mmdiary.utils;

import com.google.common.base.Strings;

public class FileUtils {

  public static String getFileSuffix(String filename) {
    String suffix = null;
    if(!Strings.isNullOrEmpty(filename)) {
      int dotIndex = filename.lastIndexOf(".");
      if(dotIndex > 0 && dotIndex < filename.length() - 1) {
        suffix = filename.substring(dotIndex + 1);
      }
    }
    return suffix;
  }
}
