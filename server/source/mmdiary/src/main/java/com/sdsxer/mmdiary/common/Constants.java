package com.sdsxer.mmdiary.common;


/**
 * Created by leon on 2017/9/25.
 */
public class Constants {

  public static final int TOKEN_EXPIRES_HOUR = 72;

  public static final String[] SUPPORTED_IMAGE_FORMAT = new String[]{"png", "jpg", "jpeg", "gif"};

  public static final String CONFIG_ENV_DEV = "dev";
  public static final String CONFIG_ENV_QA = "qa";
  public static final String CONFIG_ENV_PROD = "prod";

  public static final String CONFIG_ORM_JPA = "jpa";
  public static final String CONFIG_ORM_MYBATIS = "mybatis";

  public static final String REQUEST_HEADER_TOKEN = "Token";
}
