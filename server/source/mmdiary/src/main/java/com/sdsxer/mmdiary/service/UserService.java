package com.sdsxer.mmdiary.service;

import com.sdsxer.mmdiary.domain.User;
import com.sdsxer.mmdiary.repository.UserDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserDao userDao;

  public User login(String mobile) {

//    List<User> userList = userDao.findAll();
//    System.out.println(userList);
    return userDao.findByMobile(mobile);
  }
}
