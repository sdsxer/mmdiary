package com.sdsxer.mmdiary.web;


import com.sdsxer.mmdiary.common.Constants;
import com.sdsxer.mmdiary.common.ErrorCode;
import com.sdsxer.mmdiary.common.ErrorCode.Common;
import com.sdsxer.mmdiary.config.StorageProperties;
import com.sdsxer.mmdiary.domain.Diary;
import com.sdsxer.mmdiary.service.DiaryService;
import com.sdsxer.mmdiary.storage.StorageException;
import com.sdsxer.mmdiary.storage.StorageService;
import com.sdsxer.mmdiary.utils.FileUtils;
import com.sdsxer.mmdiary.utils.ImageUtils;
import com.sdsxer.mmdiary.utils.TokenManager;
import com.sdsxer.mmdiary.web.response.BaseResponse;
import com.sdsxer.mmdiary.web.response.FailureResponse;
import com.sdsxer.mmdiary.web.response.SuccessResponse;
import com.sdsxer.mmdiary.web.response.diary.CreateDiaryResponse;
import com.sdsxer.mmdiary.web.response.diary.RetrieveDiaryListResponse;
import com.sdsxer.mmdiary.web.response.diary.RetrieveDiaryResponse;
import com.sdsxer.mmdiary.web.response.diary.UpdateDiaryResponse;
import java.nio.file.Path;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

  @RequestMapping(value = "/diary/create", method = RequestMethod.POST)
  public BaseResponse createDiary(@RequestHeader(Constants.REQUEST_HEADER_TOKEN) String token,
      @RequestParam("title") String title, @RequestParam("content") String content,
      @RequestParam("file") MultipartFile file) {
    BaseResponse response = null;

    // empty title
    if(StringUtils.isEmpty(title)) {
      response = new FailureResponse(ErrorCode.Diary.EMPTY_TITLE);
      return response;
    }

    // empty content
    if(StringUtils.isEmpty(content)) {
      response = new FailureResponse(ErrorCode.Diary.EMPTY_CONTENT);
      return response;
    }

    // empty cover image
    if(file == null) {
      response = new FailureResponse(ErrorCode.Diary.EMPTY_COVER_IMAGE);
      return response;
    }

    // unsupported image format
    if(!ImageUtils.isFormatSupported(FileUtils.getFileSuffix(file.getOriginalFilename()))) {
      response = new FailureResponse(ErrorCode.Diary.ILLEGAL_IMAGE_FORMAT);
      return response;
    }

    // save image
    Path absolutePath = null;
    try {
      absolutePath = storageService.store(file);
    }
    catch (StorageException e) {
      logger.error("Could not save file", e);
    }
    if(absolutePath == null) {
      response = new FailureResponse(ErrorCode.Diary.FAILED_TO_SAVE_IMAGE);
      return response;
    }

    // covert to relative path and store to database
    Path relativePath = storageService.getRelativePath(absolutePath);

    // encapsulate response
    Diary diary = diaryService.createDiary(tokenManager.getUserId(token), title, content, relativePath.toString());
    response = new CreateDiaryResponse(diary);
    return response;
  }


  @RequestMapping(value = "/diary/update", method = RequestMethod.POST)
  public BaseResponse updateDiary(@RequestHeader(Constants.REQUEST_HEADER_TOKEN) String token,
      @RequestParam("id") long id, @RequestParam("title") String title,
      @RequestParam("content") String content, @RequestParam("file") MultipartFile file) {
    BaseResponse response = null;

    // retrieve original diary
    Diary originalDiary = diaryService.retrieveDiary(id);
    if(originalDiary == null) {
      response = new FailureResponse(ErrorCode.Diary.DIARY_NOT_EXIST);
      return response;
    }

    // check authority
    long userId = tokenManager.getUserId(token);
    if(originalDiary.getUser() == null || originalDiary.getUser().getId() != userId) {
      response = new FailureResponse(Common.UNAUTHORIZED);
      return response;
    }

    // check id's legality
    if(id <= 0) {
      response = new FailureResponse(ErrorCode.Diary.ILLEGAL_ID);
      return response;
    }

    // check params's legality
    if(StringUtils.isEmpty(title) || StringUtils.isEmpty(content)) {
      response = new FailureResponse(Common.ILLEGAL_PARAM);
      return response;
    } else {
      originalDiary.setTitle(title);
      originalDiary.setContent(content);
    }

    // update image
    if(file != null) {
      // check image format
      if(!ImageUtils.isFormatSupported(FileUtils.getFileSuffix(file.getOriginalFilename()))) {
        response = new FailureResponse(ErrorCode.Diary.ILLEGAL_IMAGE_FORMAT);
        return response;
      }

      // save image
      Path filePath = null;
      try {
        filePath = storageService.store(file);
      }
      catch (Exception e) {
        logger.error("could not save file", e);
      }
      if(filePath == null) {
        response = new FailureResponse(ErrorCode.Diary.FAILED_TO_SAVE_IMAGE);
        return response;
      }
      originalDiary.setCoverUrl(filePath.toString());
    }

    // encapsulate response
    Diary diary = diaryService.updateDiary(originalDiary.getTitle(), originalDiary.getContent(),
        originalDiary.getCoverUrl());
    response = new UpdateDiaryResponse(diary);
    return response;
  }

  @RequestMapping(value = "/diary/retrieve", method = RequestMethod.POST)
  public BaseResponse retrieveDiary(@RequestParam("id") long id) {
    BaseResponse response = null;

    // check id's legality
    if(id <= 0) {
      response = new FailureResponse(ErrorCode.Diary.ILLEGAL_ID);
      return response;
    }

    // retrieve diary
    Diary diary = diaryService.retrieveDiary(id);

    // check diary's existence
    if(diary == null) {
      response = new FailureResponse(ErrorCode.Diary.DIARY_NOT_EXIST);
      return response;
    }

    // encapsulate response
    response = new RetrieveDiaryResponse(diary);
    return response;
  }

  @RequestMapping(value = "/diary/retrieve/list", method = RequestMethod.POST)
  public BaseResponse retrieveDiaryList(@RequestParam("index") int index, @RequestParam("size") int size) {
    BaseResponse response = null;

    // check params's legality
    if(index < 0 || size <= 0) {
      response = new FailureResponse(Common.ILLEGAL_PARAM);
      return response;
    }

    // retrieve diary list
    Page<Diary> diaryPage = diaryService.retrieveDiaryList(index, size);

    // encapsulate response
    response = new RetrieveDiaryListResponse(diaryPage);
    return response;
  }

  @RequestMapping(value = "/diary/delete", method = RequestMethod.POST)
  public BaseResponse deleteDiary(@RequestHeader(Constants.REQUEST_HEADER_TOKEN) String token,
      @RequestParam("id") long id) {
    BaseResponse response = null;

    // check id's legality
    if(id <= 0) {
      response = new FailureResponse(ErrorCode.Diary.ILLEGAL_ID);
      return response;
    }

    Diary diary = diaryService.retrieveDiary(id);

    // check whether diary exist
    if(diary == null) {
      response = new FailureResponse(ErrorCode.Diary.DIARY_NOT_EXIST);
      return response;
    }

    // only author and admin have authorize to delete diary
    long userId = tokenManager.getUserId(token);
    if(diary.getUser() == null || diary.getUser().getId() != userId) {
      response = new FailureResponse(ErrorCode.Common.UNAUTHORIZED);
      return response;
    }

    // delete diary
    diaryService.deleteDiary(id);

    // encapsulate response
    response = new SuccessResponse();
    return response;
  }
}
