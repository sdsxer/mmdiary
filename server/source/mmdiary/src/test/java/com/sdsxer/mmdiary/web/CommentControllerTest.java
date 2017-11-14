package com.sdsxer.mmdiary.web;


import com.sdsxer.mmdiary.common.Constant;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class CommentControllerTest extends BasicControllerTest {

  @Test
  public void createComment() throws Exception {
    mvc.perform(MockMvcRequestBuilders
          .post("/mmdiary/comment/create")
          .contextPath(Constant.CONTEXT_PATH)
          .contentType(MediaType.APPLICATION_FORM_URLENCODED)
          .accept(MediaType.APPLICATION_JSON)
          .header(Constant.HEADER_KEY_TOKEN, token)
          .param("diaryId", "1")
          .param("content", "go go go"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print())
        .andReturn();
  }
}
