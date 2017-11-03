package com.sdsxer.mmdiary.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

  @Autowired
  private WebApplicationContext context;
  private MockMvc mvc;

  @Before
  public void setUp() throws Exception {
    mvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  public void login() throws Exception {
      mvc.perform(MockMvcRequestBuilders.post("/mmdiary/user/login")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .accept(MediaType.APPLICATION_JSON)
            .param("mobile", "18810789600")
            .param("password", "E10ADC3949BA59ABBE56E057F20F883E"))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andDo(MockMvcResultHandlers.print())
          .andReturn();
  }
}
