package com.sdsxer.mmdiary.storage;

public class PermissionDeniedException extends StorageException {

  public PermissionDeniedException(String message) {
    super(message);
  }

  public PermissionDeniedException(String message, Throwable cause) {
    super(message, cause);
  }
}
