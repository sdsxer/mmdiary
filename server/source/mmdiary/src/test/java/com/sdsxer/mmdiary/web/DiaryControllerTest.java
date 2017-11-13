package com.sdsxer.mmdiary.web;


import com.sdsxer.mmdiary.common.Constant;
import java.io.InputStream;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class DiaryControllerTest extends BasicControllerTest {

  @Test
  public void createDiary() throws Exception {
    InputStream fileInputStream = new ClassPathResource("avatar_1.png").getInputStream();
    MockMultipartFile multipartFile = new MockMultipartFile("file", "avatar_1.png",
        "application/octet-stream", fileInputStream);
    mvc.perform(MockMvcRequestBuilders
        .fileUpload("/mmdiary/diary/create")
        .file(multipartFile)
        .contextPath(Constant.CONTEXT_PATH)
        .contentType(MediaType.MULTIPART_FORM_DATA)
        .accept(MediaType.APPLICATION_JSON)
        .header(Constant.HEADER_KEY_TOKEN, token)
        .param("title", "new to the world")
        .param("content", "our baby was born!"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print())
        .andReturn();
  }

//  @Test
//  public void updateDiary() {
//
//  }
//
//  @Test
//  public void deleteDiary() {
//
//  }
//
//  @Test
//  public void retrieveDiary() {
//
//  }
//
//  @Test
//  public void retrieveDiaryList() {
//
//  }
}
