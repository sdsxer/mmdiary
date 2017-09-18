package com.sdsxer.mmdiary.web.response;

public class FailureResponse extends BaseResponse {

  public FailureResponse() {
    this.errorCode = -1;
    this.errorMessage = "unknown";
  }

  public FailureResponse(int errorCode, String errorMessage) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }
}
