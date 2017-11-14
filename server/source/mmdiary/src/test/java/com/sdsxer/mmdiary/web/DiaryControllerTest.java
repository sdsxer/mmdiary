package com.sdsxer.mmdiary.web;


import com.sdsxer.mmdiary.common.Constant;
import com.sdsxer.mmdiary.service.DiaryService;
import java.io.InputStream;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class DiaryControllerTest extends BasicControllerTest {

  @Autowired
  private DiaryService diaryService;

  @Override
  public void setUp() throws Exception {
    super.setUp();
  }

  @Test
  public void createDiary() throws Exception {
    InputStream fileInputStream = new ClassPathResource("cover_1.jpg").getInputStream();
    MockMultipartFile multipartFile = new MockMultipartFile("file", "cover_1.jpg",
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

  @Test
  public void updateDiary() throws Exception {
    InputStream fileInputStream = new ClassPathResource("cover_2.jpg").getInputStream();
    MockMultipartFile multipartFile = new MockMultipartFile("file", "cover_2.jpg",
        "application/octet-stream", fileInputStream);
    mvc.perform(MockMvcRequestBuilders
          .fileUpload("/mmdiary/diary/update")
          .file(multipartFile)
          .contextPath(Constant.CONTEXT_PATH)
          .contentType(MediaType.MULTIPART_FORM_DATA)
          .accept(MediaType.APPLICATION_JSON)
          .header(Constant.HEADER_KEY_TOKEN, token)
          .param("id", "1")
          .param("title", "update title")
          .param("content", "update content"))
//        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print())
        .andReturn();
  }

  @Test
  public void deleteDiary() throws Exception {
    mvc.perform(MockMvcRequestBuilders
          .post("/mmdiary/diary/delete")
          .contextPath(Constant.CONTEXT_PATH)
          .contentType(MediaType.APPLICATION_FORM_URLENCODED)
          .accept(MediaType.APPLICATION_JSON)
          .header(Constant.HEADER_KEY_TOKEN, token)
          .param("id", "5"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print())
        .andReturn();
  }

  @Test
  public void retrieveDiary() throws Exception {
    mvc.perform(MockMvcRequestBuilders
          .post("/mmdiary/diary/retrieve")
          .contextPath(Constant.CONTEXT_PATH)
          .contentType(MediaType.APPLICATION_FORM_URLENCODED)
          .accept(MediaType.APPLICATION_JSON)
          .header(Constant.HEADER_KEY_TOKEN, token)
          .param("id", "3"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print())
        .andReturn();
  }

  @Test
  public void retrieveDiaryList() throws Exception {
    mvc.perform(MockMvcRequestBuilders
          .post("/mmdiary/diary/retrieve/list")
          .contextPath(Constant.CONTEXT_PATH)
          .contentType(MediaType.APPLICATION_FORM_URLENCODED)
          .accept(MediaType.APPLICATION_JSON)
          .header(Constant.HEADER_KEY_TOKEN, token)
          .param("index", "0")
          .param("size", "20"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print())
        .andReturn();
  }
}
