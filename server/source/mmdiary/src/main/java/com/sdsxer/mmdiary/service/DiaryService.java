package com.sdsxer.mmdiary.service;

import com.sdsxer.mmdiary.domain.Diary;
import com.sdsxer.mmdiary.domain.User;
import com.sdsxer.mmdiary.repository.DiaryDao;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DiaryService {

  private static Logger logger = LoggerFactory.getLogger(DiaryService.class);

  @Autowired
  private DiaryDao diaryDao;

  public Diary createDiary(long userId, String title, String content,
      String coverUrl, Date createTime) {
    Diary diary = new Diary();
    diary.setTitle(title);
    diary.setContent(content);
    diary.setCoverUrl(coverUrl);
    User author = new User();
    author.setId(userId);
    diary.setUser(author);
    diary.setCreateTime(createTime);
    diary.setLastModifiedTime(createTime);
    return diaryDao.save(diary);
  }

  public Diary retrieveDiary(long id) {
    return diaryDao.findOne(id);
  }

  public Page<Diary> retrieveDiaryList(int index, int size) {
    return diaryDao.findAll(new PageRequest(index, size, Sort.Direction.ASC,"id"));
  }

  public Diary updateDiary(String title, String content, String coverUrl) {
    Diary diary = new Diary();
    diary.setTitle(title);
    diary.setContent(content);
    diary.setCoverUrl(coverUrl);
    diary.setLastModifiedTime(new Date());
    return diaryDao.saveAndFlush(diary);
  }

  public void deleteDiary(long id) {
    diaryDao.delete(id);
  }
}
