package com.sdsxer.mmdiary.web;

import com.sdsxer.mmdiary.service.DiaryService;
import com.sdsxer.mmdiary.storage.StorageService;
import com.sdsxer.mmdiary.web.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class DiaryController extends BaseController {

  @Autowired
  private DiaryService diaryService;

  @Autowired
  private StorageService storageService;

  @RequestMapping(value = "/diary/create", method = RequestMethod.POST)
  public BaseResponse createDiary(@RequestParam("title") String title, @RequestParam("content") String content,
      @RequestParam("file") MultipartFile file) {

    BaseResponse response = null;

    return response;
  }


}
