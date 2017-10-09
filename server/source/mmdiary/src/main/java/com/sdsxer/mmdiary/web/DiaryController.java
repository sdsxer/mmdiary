package com.sdsxer.mmdiary.web;


import com.google.common.base.Strings;
import com.sdsxer.mmdiary.common.ErrorCode;
import com.sdsxer.mmdiary.domain.Diary;
import com.sdsxer.mmdiary.service.DiaryService;
import com.sdsxer.mmdiary.storage.StorageService;
import com.sdsxer.mmdiary.utils.FileUtils;
import com.sdsxer.mmdiary.utils.ImageUtils;
import com.sdsxer.mmdiary.utils.TokenManager;
import com.sdsxer.mmdiary.web.response.BaseResponse;
import com.sdsxer.mmdiary.web.response.FailureResponse;
import com.sdsxer.mmdiary.web.response.diary.CreateDiaryResponse;
import java.nio.file.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class DiaryController extends BaseController {

  private static Logger logger = LoggerFactory.getLogger(DiaryController.class);

  @Autowired
  private DiaryService diaryService;

  @Autowired
  private StorageService storageService;

  @Autowired
  private TokenManager tokenManager;

  /**
   * create diary
   * @param title
   * @param content
   * @param file
   * @return
   */
  @RequestMapping(value = "/diary/create", method = RequestMethod.POST)
  public BaseResponse createDiary(@RequestParam("title") String title,
      @RequestParam("content") String content, @RequestParam("file") MultipartFile file) {

    BaseResponse response = null;

    // empty title
    if(Strings.isNullOrEmpty(title)) {
      response = new FailureResponse(ErrorCode.Diary.EMPTY_TITLE);
      return response;
    }

    // empty content
    if(Strings.isNullOrEmpty(content)) {
      response = new FailureResponse(ErrorCode.Diary.EMPTY_CONTENT);
      return response;
    }

    // empty cover image
    if(file == null) {
      response = new FailureResponse(ErrorCode.Diary.EMPTY_COVER_IMAGE);
      return response;
    }

    // unsupported cover image format
    if(!ImageUtils.isFormatSupported(FileUtils.getFileSuffix(file.getOriginalFilename()))) {
      response = new FailureResponse(ErrorCode.Diary.ILLEGAL_IMAGE_FORMAT);
      return response;
    }

    // save cover image
    String filename = null;
    try {
      filename = storageService.store(file);
    }
    catch (Exception e) {
      logger.error("could not save file", e);
    }
    if(Strings.isNullOrEmpty(filename)) {
      response = new FailureResponse(ErrorCode.Diary.FAILED_TO_SAVE_IMAGE);
      return response;
    }

    // create response
    Path filePath = storageService.load(filename);
    Diary diary = diaryService.createDiary(title, content, filePath.toString());
    response = new CreateDiaryResponse(diary);
    return response;
  }

  /**
   * update diary
   * @param id
   * @param title
   * @param content
   * @param file
   * @return
   */
  @RequestMapping(value = "/diary/update", method = RequestMethod.POST)
  public BaseResponse updateDiary(@RequestHeader("Token") String token, @RequestParam("id") long id,
      @RequestParam("title") String title, @RequestParam("content") String content,
      @RequestParam("file") MultipartFile file) {

    BaseResponse response = null;

    // illegal diary id
    if(id <= 0) {
      response = new FailureResponse(ErrorCode.Diary.ILLEGAL_ID);
      return response;
    }

    // diary not exist
    Diary originalDiary = diaryService.retrieveDiary(id);
    if(originalDiary == null) {
      response = new FailureResponse(ErrorCode.Diary.DIARY_NOT_EXIST);
      return response;
    }

    long userId = tokenManager.getUserId(token);
    if(originalDiary.getUser() == null && originalDiary.getUser().getId() == userId) {

    }

    return response;
  }

  @RequestMapping(value = "/diary/retrieve", method = RequestMethod.POST)
  public BaseResponse retrieveDiary(@RequestParam("id") String title) {
    return null;
  }



  @RequestMapping(value = "/diary/delete", method = RequestMethod.POST)
  public BaseResponse deleteDiary(@RequestParam("id") String title) {
    return null;
  }
}
