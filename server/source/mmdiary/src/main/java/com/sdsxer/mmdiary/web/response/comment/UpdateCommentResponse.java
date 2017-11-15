package com.sdsxer.mmdiary.web.response.comment;

import com.sdsxer.mmdiary.domain.Comment;
import com.sdsxer.mmdiary.dto.CommentDto;
import com.sdsxer.mmdiary.web.response.SuccessResponse;

public class UpdateCommentResponse extends SuccessResponse {

  private CommentDto comment;

  public UpdateCommentResponse(Comment comment) {
    this.comment = new CommentDto(comment);
    this.comment.setAuthor(null);
  }

  public CommentDto getComment() {
    return comment;
  }

  public void setComment(CommentDto comment) {
    this.comment = comment;
  }
}
