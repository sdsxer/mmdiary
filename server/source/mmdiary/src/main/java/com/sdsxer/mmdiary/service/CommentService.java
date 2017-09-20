package com.sdsxer.mmdiary.service;

import com.sdsxer.mmdiary.repository.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

  @Autowired
  private CommentDao commentDao;
}
