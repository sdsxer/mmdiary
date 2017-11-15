package com.sdsxer.mmdiary.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class CommonUtils {

  public static String printStackTraceToString(Throwable t) {
    StringWriter sw = new StringWriter();
    t.printStackTrace(new PrintWriter(sw, true));
    return sw.getBuffer().toString();
  }
}
