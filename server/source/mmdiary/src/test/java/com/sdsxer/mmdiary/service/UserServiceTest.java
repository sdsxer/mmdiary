package com.sdsxer.mmdiary.service;

import com.sdsxer.mmdiary.domain.User;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserServiceTest {

  @Autowired
  private UserService userService;

  @Before
  public void setUp() throws Exception {

  }

  @Test
  public void findAllUsersTest() {
    List<User> users = userService.findAllUsers();
    System.out.println(users);
  }
}
