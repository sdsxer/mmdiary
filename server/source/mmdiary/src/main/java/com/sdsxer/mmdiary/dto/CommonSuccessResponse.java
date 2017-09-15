package com.sdsxer.mmdiary.dto;

public class CommonSuccessResponse extends CommonResponse {

  public CommonSuccessResponse() {
    this.errorCode = 0;
    this.errorMessage = "success";
  }

  public CommonSuccessResponse(String message) {
    this.errorCode = 0;
    this.errorMessage = message;
  }
}
