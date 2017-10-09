package com.sdsxer.mmdiary.utils;

import static com.sdsxer.mmdiary.common.Constants.SUPPORTED_IMAGE_FORMAT;

import com.google.common.base.Strings;

public class ImageUtils {

  public static boolean isFormatSupported(String format) {
    boolean supported = false;
    if(!Strings.isNullOrEmpty(format)) {
      for(String supportedFormat : SUPPORTED_IMAGE_FORMAT){
        if(supportedFormat.equalsIgnoreCase(format)) {
          supported = true;
          break;
        }
      }
    }
    return supported;
  }
}
