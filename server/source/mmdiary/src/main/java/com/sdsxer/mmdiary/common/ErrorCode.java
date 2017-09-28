package com.sdsxer.mmdiary.common;

import java.util.HashMap;
import java.util.Map;

public class ErrorCode {

  private static final Map<Integer, String> codeMessageMap = new HashMap<Integer, String>();

  public static final int SUCCESS = 0;
  public static final int FAILED = -1;

  public class User {
    private static final int BASE = 100;
    public static final int EMPTY_MOBILE_OR_PASSWORD = BASE + 1;
    public static final int USER_NOT_EXIST = BASE + 2;
    public static final int PASSWORD_NOT_CORRECT = BASE + 3;
    public static final int INVALID_USER = BASE + 4;
  }

  public class Diary {
    private static final int BASE = 200;
    public static final int EMPTY_TITLE = 201;
    public static final int EMPTY_CONTENT = 202;
    public static final int EMPTY_COVER_IMAGE = 203;
    public static final int ILLEGAL_IMAGE_FORMAT = 204;
    public static final int FAILED_TO_SAVE_IMAGE = 205;
  }

  public class Comment {
    private static final int BASE = 300;
  }

  static {
    codeMessageMap.put(SUCCESS, "请求成功");
    codeMessageMap.put(FAILED, "未知错误");

    codeMessageMap.put(User.EMPTY_MOBILE_OR_PASSWORD, "手机或密码不能为空");
    codeMessageMap.put(User.USER_NOT_EXIST, "用户不存在");
    codeMessageMap.put(User.PASSWORD_NOT_CORRECT, "密码不正确");
    codeMessageMap.put(User.INVALID_USER, "无效的用户");

    codeMessageMap.put(Diary.EMPTY_TITLE, "标题不能为空");
    codeMessageMap.put(Diary.EMPTY_CONTENT, "内容不能为空");
    codeMessageMap.put(Diary.EMPTY_COVER_IMAGE, "封面图片不能为空");
    codeMessageMap.put(Diary.ILLEGAL_IMAGE_FORMAT, "无效的图片格式");
    codeMessageMap.put(Diary.FAILED_TO_SAVE_IMAGE, "无法保存图片");
  }

  public static String getErrorMessage(int errorCode) {
      return codeMessageMap.get(errorCode);
  }
}
