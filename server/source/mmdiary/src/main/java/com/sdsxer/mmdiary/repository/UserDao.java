package com.sdsxer.mmdiary.repository;

import com.sdsxer.mmdiary.domain.user.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserDao {

  @Select("select * from user where mobile = #{mobile}")
  public User findByMobile(@Param("mobile") String mobile);
}
