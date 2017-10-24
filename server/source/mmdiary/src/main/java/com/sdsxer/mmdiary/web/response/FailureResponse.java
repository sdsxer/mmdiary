package com.sdsxer.mmdiary.web.response;

import com.sdsxer.mmdiary.common.ErrorCode;

public class FailureResponse extends BaseResponse {

  public FailureResponse() {
    this.errorCode = ErrorCode.Common.UNKNOWN;
    this.errorMessage = ErrorCode.getErrorMessage(this.errorCode);
  }

  public FailureResponse(int errorCode) {
    this.errorCode = errorCode;
    this.errorMessage = ErrorCode.getErrorMessage(this.errorCode);
  }
}
