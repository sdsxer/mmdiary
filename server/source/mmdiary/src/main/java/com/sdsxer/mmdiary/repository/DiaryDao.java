package com.sdsxer.mmdiary.repository;

import com.sdsxer.mmdiary.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryDao extends JpaRepository<Diary, Long> {

}
