package com.sdsxer.mmdiary.dto;

import com.sdsxer.mmdiary.domain.Diary;

public class DiaryDto {

  private long id;
  private String title;
  private String content;
  private String coverUrl;
  private UserDto author;

  public DiaryDto(Diary diary) {
    this.id = diary.getId();
    this.title = diary.getTitle();
    this.content = diary.getContent();
    this.coverUrl = diary.getCoverUrl();
    if(diary.getUser() != null) {
      this.author = new UserDto(diary.getUser());
    }
  }

  public long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }

  public String getCoverUrl() {
    return coverUrl;
  }

  public UserDto getAuthor() {
    return author;
  }
}
