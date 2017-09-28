package com.sdsxer.mmdiary.service;

import com.sdsxer.mmdiary.domain.Diary;
import com.sdsxer.mmdiary.repository.DiaryDao;
import com.sdsxer.mmdiary.web.UserController;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
}
