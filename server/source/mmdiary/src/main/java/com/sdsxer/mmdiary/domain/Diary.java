package com.sdsxer.mmdiary.domain;

import java.util.List;

public class Diary {

  private long id;
  private String title;
  private String content;
  private int attachmentType;
  private String attachmentUrl;
  private User author;
  private List<Comment> comments;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public int getAttachmentType() {
    return attachmentType;
  }

  public void setAttachmentType(int attachmentType) {
    this.attachmentType = attachmentType;
  }

  public String getAttachmentUrl() {
    return attachmentUrl;
  }

  public void setAttachmentUrl(String attachmentUrl) {
    this.attachmentUrl = attachmentUrl;
  }

  public User getAuthor() {
    return author;
  }

  public void setAuthor(User author) {
    this.author = author;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }
}
