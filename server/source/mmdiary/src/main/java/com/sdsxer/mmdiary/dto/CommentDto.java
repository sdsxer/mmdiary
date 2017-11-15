package com.sdsxer.mmdiary.dto;

import com.sdsxer.mmdiary.domain.Comment;
import java.util.Date;

public class CommentDto {

  private long id;
  private String content;
  private UserDto author;
  private Date createTime;
  private Date lastModifiedTime;

  public CommentDto(Comment comment) {
    this.id = comment.getId();
    this.content = comment.getContent();
    this.createTime = comment.getCreateTime();
    this.lastModifiedTime = comment.getLastModifiedTime();
    this.author = new UserDto(comment.getUser());
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

  public UserDto getAuthor() {
    return author;
  }

  public void setAuthor(UserDto author) {
    this.author = author;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getLastModifiedTime() {
    return lastModifiedTime;
  }

  public void setLastModifiedTime(Date lastModifiedTime) {
    this.lastModifiedTime = lastModifiedTime;
  }
}
