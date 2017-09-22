package com.sdsxer.mmdiary.repository;


import com.sdsxer.mmdiary.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

  public User findByMobile(String mobile);
}
