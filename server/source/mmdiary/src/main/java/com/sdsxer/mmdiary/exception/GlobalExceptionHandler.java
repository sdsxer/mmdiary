package com.sdsxer.mmdiary.exception;

import com.sdsxer.mmdiary.web.response.FailureResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

  private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(value = { NoHandlerFoundException.class })
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public FailureResponse noHandlerFoundException(Exception e) {
    return new FailureResponse(HttpStatus.NOT_FOUND.value(), "not found");
  }

  @ExceptionHandler(value = { Exception.class })
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public FailureResponse unknownException(Exception e) {
    return new FailureResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getClass().getName());
  }
}
