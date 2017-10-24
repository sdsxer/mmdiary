package com.sdsxer.mmdiary.web.response;

import com.sdsxer.mmdiary.common.ErrorCode;

public class SuccessResponse extends BaseResponse {

  public SuccessResponse() {
    this.errorCode = ErrorCode.Common.SUCCEED;
    this.errorMessage = ErrorCode.getErrorMessage(this.errorCode);
  }
}
