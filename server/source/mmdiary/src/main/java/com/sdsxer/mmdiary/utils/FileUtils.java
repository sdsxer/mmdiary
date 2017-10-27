package com.sdsxer.mmdiary.utils;

import com.google.common.base.Strings;
import com.sdsxer.mmdiary.common.FileType;
import java.io.File;
import org.springframework.util.StringUtils;

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

  public static long getDirSize(String path) {
    if(StringUtils.isEmpty(path)) {
      return 0;
    }
    File file = new File(path);
    if (file.exists()) {
      if (file.isDirectory()) {
        File[] children = file.listFiles();
        long size = 0;
        if(children != null) {
          for (File f : children) {
            size += getDirSize(f.getAbsolutePath());
          }
        }
        return size;
      } else {
        return file.length();
      }
    } else {
      return 0;
    }
  }

  public static FileType getFileType(String filename) {
    return null;
  }
}
