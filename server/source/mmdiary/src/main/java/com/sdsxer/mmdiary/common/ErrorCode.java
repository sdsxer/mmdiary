package com.sdsxer.mmdiary.common;

import java.util.HashMap;
import java.util.Map;

public class ErrorCode {

  private static final Map<Integer, String> codeMessageMap = new HashMap<Integer, String>();

  public class Common {
    private static final int BASE = 0;
    public static final int SUCCEED = BASE;
    public static final int UNAUTHORIZED = BASE + 1;
    public static final int ILLEGAL_PARAM = BASE + 2;
    public static final int OPERATION_EXCEPTION = BASE + 3;
    public static final int UNSUPPORTED_IMAGE_FORMAT = BASE + 4;
    public static final int UNABLE_SAVE_FILE = BASE + 5;
    public static final int UNSUPPORTED_IMAGE_SIZE = BASE + 6;
    public static final int UNKNOWN = -1;
  }

  public class User {
    private static final int BASE = 100;
    public static final int EMPTY_MOBILE_OR_PASSWORD = BASE + 1;
    public static final int USER_NOT_EXIST = BASE + 2;
    public static final int PASSWORD_NOT_CORRECT = BASE + 3;
    public static final int INVALID_USER = BASE + 4;
    public static final int ILLEGAL_MOBILE = BASE + 5;
    public static final int ILLEGAl_PASSWORD = BASE + 6;
    public static final int EMPTY_OLD_PASSWORD = BASE + 7;
    public static final int EMPTY_NEW_PASSWORD = BASE + 8;
    public static final int ILLEGAL_OLD_PASSWORD = BASE + 9;
    public static final int ILLEGAL_NEW_PASSWORD = BASE + 10;
    public static final int INCORRECT_OLD_PASSWORD = BASE + 11;
    public static final int EMPTY_NAME = BASE + 12;
    public static final int ILLEGAL_GENDER = BASE + 13;
    public static final int TOKEN_EXPIRED = BASE + 14;
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
    public static final int UNABLE_CREATE_DIARY = BASE + 8;
    public static final int UNABLE_UPDATE_DIARY = BASE + 9;
    public static final int UNABLE_RETRIEVE_DIARY_LIST = BASE + 10;
  }

  public class Comment {
    private static final int BASE = 300;
    public static final int EMPTY_CONTENT = BASE + 1;
    public static final int COMMENT_NOT_EXIST = BASE + 2;
  }

  static {
    codeMessageMap.put(Common.SUCCEED, "请求成功");
    codeMessageMap.put(Common.UNKNOWN, "未知错误");
    codeMessageMap.put(Common.UNAUTHORIZED, "没有权限");
    codeMessageMap.put(Common.ILLEGAL_PARAM, "非法的参数");
    codeMessageMap.put(Common.OPERATION_EXCEPTION, "操作失败");
    codeMessageMap.put(Common.UNSUPPORTED_IMAGE_FORMAT, "图片格式不支持");
    codeMessageMap.put(Common.UNSUPPORTED_IMAGE_SIZE, "图片尺寸错误");
    codeMessageMap.put(Common.UNABLE_SAVE_FILE, "无法保存文件");

    codeMessageMap.put(User.TOKEN_EXPIRED, "Token已过期");
    codeMessageMap.put(User.EMPTY_MOBILE_OR_PASSWORD, "手机或密码不能为空");
    codeMessageMap.put(User.USER_NOT_EXIST, "用户不存在");
    codeMessageMap.put(User.PASSWORD_NOT_CORRECT, "密码不正确");
    codeMessageMap.put(User.INVALID_USER, "无效的用户，请重新登录");
    codeMessageMap.put(User.ILLEGAL_MOBILE, "手机号格式错误");
    codeMessageMap.put(User.ILLEGAl_PASSWORD, "密码格式错误");
    codeMessageMap.put(User.EMPTY_OLD_PASSWORD, "原始密码不能为空");
    codeMessageMap.put(User.ILLEGAL_OLD_PASSWORD, "原始密码格式错误");
    codeMessageMap.put(User.EMPTY_NEW_PASSWORD, "新密码不能为空");
    codeMessageMap.put(User.ILLEGAL_NEW_PASSWORD, "新密码格式错误");
    codeMessageMap.put(User.INCORRECT_OLD_PASSWORD, "原始密码错误");
    codeMessageMap.put(User.EMPTY_NAME, "姓名不能为空");
    codeMessageMap.put(User.ILLEGAL_GENDER, "非法的性别值");


    codeMessageMap.put(Diary.EMPTY_TITLE, "标题不能为空");
    codeMessageMap.put(Diary.EMPTY_CONTENT, "内容不能为空");
    codeMessageMap.put(Diary.EMPTY_COVER_IMAGE, "封面图片不能为空");
    codeMessageMap.put(Diary.ILLEGAL_IMAGE_FORMAT, "无效的图片格式");
    codeMessageMap.put(Diary.FAILED_TO_SAVE_IMAGE, "无法保存图片");
    codeMessageMap.put(Diary.ILLEGAL_ID, "无效的ID");
    codeMessageMap.put(Diary.DIARY_NOT_EXIST, "日记不存在");
    codeMessageMap.put(Diary.UNABLE_CREATE_DIARY, "无法创建日记");
    codeMessageMap.put(Diary.UNABLE_UPDATE_DIARY, "无法保存日记");
    codeMessageMap.put(Diary.UNABLE_RETRIEVE_DIARY_LIST, "无法获取日记列表");

    codeMessageMap.put(Comment.EMPTY_CONTENT, "评论内容不能为空");
    codeMessageMap.put(Comment.COMMENT_NOT_EXIST, "评论不存在");
  }

  public static String getErrorMessage(int errorCode) {
      return codeMessageMap.get(errorCode);
  }
}
