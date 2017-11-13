package com.sdsxer.mmdiary.web;

import com.sdsxer.mmdiary.common.Constant;
import java.io.FileInputStream;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


public class UserControllerTest extends BasicControllerTest {

  @Test
  public void login() throws Exception {
    mvc.perform(MockMvcRequestBuilders
        .post("/mmdiary/user/login")
        .contextPath(Constant.CONTEXT_PATH)
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .accept(MediaType.APPLICATION_JSON)
        .param("mobile", "18810789600")
        .param("password", "E10ADC3949BA59ABBE56E057F20F883E"))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andDo(MockMvcResultHandlers.print())
      .andReturn();
  }

  @Test
  public void logout() throws Exception {
    mvc.perform(MockMvcRequestBuilders
        .post("/mmdiary/user/logout")
        .contextPath(Constant.CONTEXT_PATH)
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .accept(MediaType.APPLICATION_JSON)
        .header(Constant.HEADER_KEY_TOKEN, token))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andDo(MockMvcResultHandlers.print())
      .andReturn();
  }

  @Test
  public void editProfile() throws Exception {

    FileInputStream fileInputStream = new FileInputStream("/Users/leon/Desktop/image/test1.png");
    MockMultipartFile multipartFile = new MockMultipartFile("avatar", "test1.png",
        "application/octet-stream", fileInputStream);

    mvc.perform(MockMvcRequestBuilders
        .fileUpload("/mmdiary/user/profile/edit")
        .file(multipartFile)
        .contextPath(Constant.CONTEXT_PATH)
        .contentType(MediaType.MULTIPART_FORM_DATA)
        .accept(MediaType.APPLICATION_JSON)
        .header(Constant.HEADER_KEY_TOKEN, token)
        .param("name", "leon")
        .param("gender", "1"))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andDo(MockMvcResultHandlers.print())
      .andReturn();
  }

  @Test
  public void modifyPassword() throws Exception {
    mvc.perform(MockMvcRequestBuilders
          .post("/mmdiary/user/password/modify")
          .contextPath(Constant.CONTEXT_PATH)
          .contentType(MediaType.APPLICATION_FORM_URLENCODED)
          .accept(MediaType.APPLICATION_JSON)
          .header(Constant.HEADER_KEY_TOKEN, token)
          .param("oldPassword", "E10ADC3949BA59ABBE56E057F20F883E")
          .param("newPassword", "C33367701511B4F6020EC61DED352059"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print())
        .andReturn();
  }
}
