package com.sdsxer.mmdiary.common;


/**
 * Created by leon on 2017/9/25.
 */
public class Constants {

  public static final int TOKEN_EXPIRES_HOUR = 72;

  public static final String DOMAIN_NAME_DEV = "127.0.0.1";
  public static final int LISTENING_PORT_DEV = 8080;

  public static final String DOMAIN_NAME_QA = "123.125.32.192";
  public static final int LISTENING_PORT_QA = 8080;

  public static final String DOMAIN_NAME_PROD = "sdsxer.com";
  public static final int LISTENING_PORT_PROD = 8080;

  public static final String[] SUPPORTED_IMAGE_FORMAT = new String[]{"png", "jpg", "jpeg", "gif"};
  public static final String[] SUPPORTED_VIDEO_FORMAT = new String[]{"rmvb", "avi", "mp4", "3gp", "flv", "mkv", "mov", "wmv"};
  public static final String[] SUPPORTED_AUDIO_FORMAT = new String[]{"mp3", "aac", "mav"};
  public static final String[] SUPPORTED_WORD_FORMAT = new String[]{"doc", "docx"};
  public static final String[] SUPPORTED_PPT_FORMAT = new String[]{"ppt", "pptx"};
  public static final String[] SUPPORTED_EXCEL_FORMAT = new String[]{"xls", "xlsx"};
  public static final String[] SUPPORTED_TXT_FORMAT = new String[]{"txt"};

  public static final int AVATAR_WIDTH = 100;
  public static final int AVATAR_HEIGHT = 100;

  public static final int DIARY_COVER_WIDTH = 200;
  public static final int DIARY_COVER_HEIGHT = 150;

  public static final String CONFIG_ENV_DEV = "dev";
  public static final String CONFIG_ENV_QA = "qa";
  public static final String CONFIG_ENV_PROD = "prod";

  public static final String CONFIG_ORM_JPA = "jpa";
  public static final String CONFIG_ORM_MYBATIS = "mybatis";

  public static final String REQUEST_HEADER_TOKEN = "Token";
}
