package com.sdsxer.mmdiary.service;

import com.sdsxer.mmdiary.domain.Comment;
import com.sdsxer.mmdiary.domain.Diary;
import com.sdsxer.mmdiary.repository.CommentDao;
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
public class CommentService {

  private static Logger logger = LoggerFactory.getLogger(CommentService.class);

  @Autowired
  private CommentDao commentDao;

  public Comment createComment(int diaryId, String content) {
    Comment comment = new Comment();
    comment.setContent(content);
    Diary diary = new Diary();
    diary.setId(diaryId);
    comment.setDiary(diary);
    Date now = new Date();
    comment.setCreateTime(now);
    comment.setLastModifiedTime(now);
    return commentDao.save(comment);
  }

  public Comment retrieveComment(long id) {
    return commentDao.findOne(id);
  }

  public Page<Comment> retrieveCommentList(long index, int size) {
    Page<Comment> commentPage = commentDao.findAll(new PageRequest((int) index, size,
        Sort.Direction.ASC,"id"));
    return commentPage;
  }

  public Comment updateComment(String content) {
    Comment comment = new Comment();
    comment.setContent(content);
    comment.setLastModifiedTime(new Date());
    return commentDao.saveAndFlush(comment);
  }

  public void deleteComment(long id) {
    commentDao.delete(id);
  }
}
