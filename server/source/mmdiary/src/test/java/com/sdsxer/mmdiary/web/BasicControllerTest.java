package com.sdsxer.mmdiary.web;

import com.sdsxer.mmdiary.common.Constant;
import com.sdsxer.mmdiary.utils.TokenManager;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BasicControllerTest {

  @Autowired
  protected WebApplicationContext context;
  protected MockMvc mvc;
  @Autowired
  protected TokenManager tokenManager;
  protected String token;

  @Before
  public void setUp() throws Exception {
    mvc = MockMvcBuilders.webAppContextSetup(context).build();
    token = tokenManager.createToken(Constant.TEST_USER_ID);
  }
}
