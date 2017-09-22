package com.sdsxer.mmdiary.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment {

  @Id
  private long id;
  private String content;
  @Column(name = "user_id")
  private long userId;
  @Column(name = "diary_id")
  private long diaryId;

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

  public long getDiaryId() {
    return diaryId;
  }

  public void setDiaryId(long diaryId) {
    this.diaryId = diaryId;
  }
}
