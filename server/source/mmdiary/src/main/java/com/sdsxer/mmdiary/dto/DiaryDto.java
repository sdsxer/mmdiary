package com.sdsxer.mmdiary.dto;

import com.sdsxer.mmdiary.domain.Diary;
import com.sdsxer.mmdiary.domain.User;

public class DiaryDto {

  private long id;
  private String title;
  private String content;
  private String coverUrl;
  private User author;

  public DiaryDto(Diary diary) {
    this.id = diary.getId();
    this.title = diary.getTitle();
    this.content = diary.getTitle();
    this.coverUrl = diary.getCoverUrl();
    this.author = diary.getUser();
  }

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

  public String getCoverUrl() {
    return coverUrl;
  }

  public void setCoverUrl(String coverUrl) {
    this.coverUrl = coverUrl;
  }

  public User getAuthor() {
    return author;
  }

  public void setAuthor(User author) {
    this.author = author;
  }
}
