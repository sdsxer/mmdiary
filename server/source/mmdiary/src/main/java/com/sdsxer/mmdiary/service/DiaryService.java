package com.sdsxer.mmdiary.service;

import com.sdsxer.mmdiary.repository.DiaryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaryService {

  @Autowired
  private DiaryDao diaryDao;
}
