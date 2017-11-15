package com.sdsxer.mmdiary.web.response.comment;

import com.sdsxer.mmdiary.domain.Comment;
import com.sdsxer.mmdiary.dto.CommentDto;
import com.sdsxer.mmdiary.dto.PageDto;
import com.sdsxer.mmdiary.web.response.SuccessResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;

public class RetrieveCommentListResponse extends SuccessResponse {

  private PageDto page;

  public RetrieveCommentListResponse(Page<Comment> commentPage) {
    this.page = new PageDto(commentPage, new CommentConverter());
  }

  public PageDto getPage() {
    return page;
  }

  public void setPage(PageDto page) {
    this.page = page;
  }


  class CommentConverter implements Converter<Comment, CommentDto> {

    @Override
    public CommentDto convert(Comment source) {
      return new CommentDto(source);
    }
  }
}
