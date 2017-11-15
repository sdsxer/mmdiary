package com.sdsxer.mmdiary.repository;


import com.sdsxer.mmdiary.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface CommentDao extends JpaRepository<Comment, Long>,
    JpaSpecificationExecutor<Comment> {

}