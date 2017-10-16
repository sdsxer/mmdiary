package com.sdsxer.mmdiary.service;

import com.sdsxer.mmdiary.domain.Diary;
import com.sdsxer.mmdiary.repository.DiaryDao;
import java.util.Date;
import java.util.List;
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

  public Diary createDiary(String title, String content, String coverUrl) {
    Diary diary = new Diary();
    diary.setTitle(title);
    diary.setContent(content);
    diary.setCoverUrl(coverUrl);
    Date now = new Date();
    diary.setCreatedTime(now);
    diary.setLastModifiedTime(now);
    return diaryDao.save(diary);
  }

  public Diary retrieveDiary(long id) {
    return diaryDao.findOne(id);
  }

  public Page<Diary> retrieveDiaryList(long index, int size) {
    Page<Diary> diaryPage = diaryDao.findAll(new PageRequest((int) index, size,
        Sort.Direction.ASC,"id"));
    return diaryPage;
  }

  public Diary updateDiary(String title, String content, String coverUrl) {
    Diary diary = new Diary();
    diary.setTitle(title);
    diary.setContent(content);
    diary.setCoverUrl(coverUrl);
    Date now = new Date();
    diary.setLastModifiedTime(now);
    return diaryDao.saveAndFlush(diary);
  }

  public void deleteDiary(long id) {
    diaryDao.delete(id);
  }
}
