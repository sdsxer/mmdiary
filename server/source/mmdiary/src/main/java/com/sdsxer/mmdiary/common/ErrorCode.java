package com.sdsxer.mmdiary.common;

import java.util.HashMap;
import java.util.Map;

public class ErrorCode {

  private static final Map<Integer, String> codeMessageMap = new HashMap<Integer, String>();

  public static final int SUCCESS = 0;
  public static final int FAILED = -1;

  public class Common {
    private static final int BASE = 0;
    public static final int UNAUTHORIZED = BASE + 1;
    public static final int ILLEGAL_PARAM = BASE + 2;
  }

  public class User {
    private static final int BASE = 100;
    public static final int EMPTY_MOBILE_OR_PASSWORD = BASE + 1;
    public static final int USER_NOT_EXIST = BASE + 2;
    public static final int PASSWORD_NOT_CORRECT = BASE + 3;
    public static final int INVALID_USER = BASE + 4;
  }

  public class Diary {
    private static final int BASE = 200;
    public static final int EMPTY_TITLE = BASE +1;
    public static final int EMPTY_CONTENT = BASE + 2;
    public static final int EMPTY_COVER_IMAGE = BASE + 3;
    public static final int ILLEGAL_IMAGE_FORMAT = BASE + 4;
    public static final int FAILED_TO_SAVE_IMAGE = BASE + 5;
    public static final int ILLEGAL_ID = BASE + 6;
    public static final int DIARY_NOT_EXIST = BASE + 7;
  }

  public class Comment {
    private static final int BASE = 300;
  }

  static {
    codeMessageMap.put(SUCCESS, "请求成功");
    codeMessageMap.put(FAILED, "未知错误");
    codeMessageMap.put(Common.UNAUTHORIZED, "没有权限");
    codeMessageMap.put(Common.ILLEGAL_PARAM, "非法的参数");

    codeMessageMap.put(User.EMPTY_MOBILE_OR_PASSWORD, "手机或密码不能为空");
    codeMessageMap.put(User.USER_NOT_EXIST, "用户不存在");
    codeMessageMap.put(User.PASSWORD_NOT_CORRECT, "密码不正确");
    codeMessageMap.put(User.INVALID_USER, "无效的用户");

    codeMessageMap.put(Diary.EMPTY_TITLE, "标题不能为空");
    codeMessageMap.put(Diary.EMPTY_CONTENT, "内容不能为空");
    codeMessageMap.put(Diary.EMPTY_COVER_IMAGE, "封面图片不能为空");
    codeMessageMap.put(Diary.ILLEGAL_IMAGE_FORMAT, "无效的图片格式");
    codeMessageMap.put(Diary.FAILED_TO_SAVE_IMAGE, "无法保存图片");
    codeMessageMap.put(Diary.ILLEGAL_ID, "无效的ID");
  }

  public static String getErrorMessage(int errorCode) {
      return codeMessageMap.get(errorCode);
  }
}
