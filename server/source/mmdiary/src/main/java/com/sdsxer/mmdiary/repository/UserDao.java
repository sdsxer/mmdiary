package com.sdsxer.mmdiary.repository;

import com.sdsxer.mmdiary.domain.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {

  @Select("SELECT * FROM user where mobile=#{mobile}")
  public User findByMobile(String mobile);
}
