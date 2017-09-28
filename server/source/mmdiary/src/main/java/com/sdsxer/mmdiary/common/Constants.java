package com.sdsxer.mmdiary.common;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by leon on 2017/9/25.
 */
public class Constants {

  public static final int TOKEN_EXPIRES_HOUR = 72;

  public static final Set<String> SUPPORTED_IMAGE_FORMAT
      = new HashSet<String>(Arrays.asList("png", "jpg", "jpeg", "gif"));
}
