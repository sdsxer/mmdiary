package com.sdsxer.mmdiary.utils;

import com.sdsxer.mmdiary.common.Constants;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.web.context.support.StandardServletEnvironment;

public class EnvironmentUtils {

  public static boolean isDevelopmentEnvironment() {
    return true;
  }
}
