package com.sdsxer.mmdiary.storage;

public class OverCapacityLimitException extends StorageException {

  public OverCapacityLimitException(String message) {
    super(message);
  }

  public OverCapacityLimitException(String message, Throwable cause) {
    super(message, cause);
  }
}
