package com.sdsxer.mmdiary.web.response;

import com.sdsxer.mmdiary.common.ErrorCode;
import org.springframework.http.HttpStatus;

public class FailureResponse extends BaseResponse {

  public FailureResponse() {
    this(HttpStatus.INTERNAL_SERVER_ERROR);
  }

  public FailureResponse(int errorCode) {
    this.errorCode = errorCode;
    this.errorMessage = ErrorCode.getErrorMessage(this.errorCode);
  }

  public FailureResponse(int errorCode, String errorMessage) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }

  // standard error
  public FailureResponse(HttpStatus status) {
    this(status.value(), status.getReasonPhrase());
  }
}
