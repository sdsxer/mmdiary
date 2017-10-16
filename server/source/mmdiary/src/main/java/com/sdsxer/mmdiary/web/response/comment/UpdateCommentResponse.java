package com.sdsxer.mmdiary.web.response.comment;

import com.sdsxer.mmdiary.domain.Comment;
import com.sdsxer.mmdiary.web.response.SuccessResponse;

public class UpdateCommentResponse extends SuccessResponse {

  private Comment comment;

  public UpdateCommentResponse(Comment comment) {
    this.comment = comment;
  }
}
