package com.sdsxer.mmdiary.dto;

import com.sdsxer.mmdiary.domain.Comment;
import com.sdsxer.mmdiary.domain.User;

public class CommentDto {

  private long id;
  private String content;
  private User author;

  public CommentDto(Comment comment) {
    this.id = comment.getId();
    this.content = comment.getContent();
    this.author = comment.getUser();
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public User getAuthor() {
    return author;
  }

  public void setAuthor(User author) {
    this.author = author;
  }
}
