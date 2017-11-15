package com.sdsxer.mmdiary.service;

import com.sdsxer.mmdiary.domain.Comment;
import com.sdsxer.mmdiary.domain.Diary;
import com.sdsxer.mmdiary.domain.User;
import com.sdsxer.mmdiary.repository.CommentDao;
import com.sdsxer.mmdiary.repository.DiaryDao;
import com.sdsxer.mmdiary.utils.CustomSpecifications;
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

  public Comment createComment(long authorId, long diaryId, String content) {
    Comment comment = new Comment();
    comment.setContent(content);
    // set target diary
    Diary diary = new Diary();
    diary.setId(diaryId);
    comment.setDiary(diary);
    // set author
    User author = new User();
    author.setId(authorId);
    comment.setUser(author);
    Date now = new Date();
    comment.setCreateTime(now);
    comment.setLastModifiedTime(now);
    return commentDao.saveAndFlush(comment);
  }

  public Comment retrieveComment(long id) {
    return commentDao.findOne(id);
  }

  public Page<Comment> retrieveCommentList(long diaryId, long index, int size) {
    Page<Comment> commentPage = commentDao.findAll(CustomSpecifications.specDiaryId(diaryId),
        new PageRequest((int) index, size, Sort.Direction.ASC,"id"));
    return commentPage;
  }

  public Comment updateComment(long id, String content) {
    Comment comment = new Comment();
    comment.setId(id);
    comment.setContent(content);
    comment.setLastModifiedTime(new Date());
    return commentDao.saveAndFlush(comment);
  }

  public void deleteComment(long id) {
    commentDao.delete(id);
  }
}
