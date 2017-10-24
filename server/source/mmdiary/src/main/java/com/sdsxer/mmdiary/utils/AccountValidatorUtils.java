package com.sdsxer.mmdiary.utils;

import java.util.regex.Pattern;

public class AccountValidatorUtils {


  public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,20}$";


  public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,20}$";


  public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";


  public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";


  public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";


  public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";


  public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";


  public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";


  public static final String REGEX_MD5_32 = "^[a-zA-Z0-9]{32}$";


  public static final String REGEX_MD5_16 = "^[a-zA-Z0-9]{16}$";


  /**
   * validate username
   *
   * @param username
   * @return 校验通过返回true，否则返回false
   */
  public static boolean isUsername(String username) {
    return Pattern.matches(REGEX_USERNAME, username);
  }

  /**
   * 校验密码
   *
   * @param password
   * @return 校验通过返回true，否则返回false
   */
  public static boolean isPassword(String password) {
    return Pattern.matches(REGEX_PASSWORD, password);
  }

  /**
   * 校验手机号
   *
   * @param mobile
   * @return 校验通过返回true，否则返回false
   */
  public static boolean isMobile(String mobile) {
    return Pattern.matches(REGEX_MOBILE, mobile);
  }

  /**
   * 校验邮箱
   *
   * @param email
   * @return 校验通过返回true，否则返回false
   */
  public static boolean isEmail(String email) {
    return Pattern.matches(REGEX_EMAIL, email);
  }

  /**
   * 校验汉字
   *
   * @param chinese
   * @return 校验通过返回true，否则返回false
   */
  public static boolean isChinese(String chinese) {
    return Pattern.matches(REGEX_CHINESE, chinese);
  }

  /**
   * 校验身份证
   *
   * @param idCard
   * @return 校验通过返回true，否则返回false
   */
  public static boolean isIDCard(String idCard) {
    return Pattern.matches(REGEX_ID_CARD, idCard);
  }

  /**
   * 校验URL
   *
   * @param url
   * @return 校验通过返回true，否则返回false
   */
  public static boolean isUrl(String url) {
    return Pattern.matches(REGEX_URL, url);
  }

  /**
   * 校验IP地址
   *
   * @param ipAddr
   * @return
   */
  public static boolean isIPAddr(String ipAddr) {
    return Pattern.matches(REGEX_IP_ADDR, ipAddr);
  }

  /**
   * 校验MD5(32)格式
   * @param md5_32
   * @return
   */
  public static boolean isMD5_32(String md5_32) {
    return Pattern.matches(REGEX_MD5_32, md5_32);
  }

  /**
   * 校验MD5(16)格式
   * @param md5_16
   * @return
   */
  public static boolean isMD5_16 (String md5_16) {
    return Pattern.matches(REGEX_MD5_16, md5_16);
  }
}
