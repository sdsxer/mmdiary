package com.sdsxer.mmdiary.utils;

import com.sdsxer.mmdiary.domain.Comment;
import com.sdsxer.mmdiary.domain.Diary;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class CustomSpecifications {

  public static Specification<Comment> specDiaryId(long diaryId) {
    return new Specification<Comment>() {
      @Override
      public Predicate toPredicate(Root<Comment> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Path<Diary> diaryPath = root.get("diary");
        Path<Long> diaryIdPath = diaryPath.get("id");
        return cb.equal(diaryIdPath, diaryId);
      }
    };
  }
}
