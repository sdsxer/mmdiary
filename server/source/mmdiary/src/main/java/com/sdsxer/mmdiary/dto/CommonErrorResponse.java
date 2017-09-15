package com.sdsxer.mmdiary.dto;

public class CommonErrorResponse extends CommonResponse {

  public CommonErrorResponse(int errorCode, String errorMessage) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }
}
