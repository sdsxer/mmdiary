package com.sdsxer.mmdiary.web;

import static com.sdsxer.mmdiary.common.Constants.SUPPORTED_IMAGE_FORMAT;

import com.google.common.base.Strings;
import com.sdsxer.mmdiary.common.Constants;
import com.sdsxer.mmdiary.common.ErrorCode;
import com.sdsxer.mmdiary.domain.Diary;
import com.sdsxer.mmdiary.service.DiaryService;
import com.sdsxer.mmdiary.storage.StorageService;
import com.sdsxer.mmdiary.utils.FileUtils;
import com.sdsxer.mmdiary.web.response.BaseResponse;
import com.sdsxer.mmdiary.web.response.FailureResponse;
import com.sdsxer.mmdiary.web.response.diary.CreateDiaryResponse;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Set;
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

    if(Strings.isNullOrEmpty(title)) {
      response = new FailureResponse(ErrorCode.Diary.EMPTY_TITLE);
      return response;
    }

    if(Strings.isNullOrEmpty(content)) {
      response = new FailureResponse(ErrorCode.Diary.EMPTY_CONTENT);
      return response;
    }

    if(file == null) {
      response = new FailureResponse(ErrorCode.Diary.EMPTY_COVER_IMAGE);
      return response;
    }

    if(!SUPPORTED_IMAGE_FORMAT.contains(
        FileUtils.getFileSuffix(file.getOriginalFilename()).toLowerCase())) {
      response = new FailureResponse(ErrorCode.Diary.ILLEGAL_IMAGE_FORMAT);
      return response;
    }

    String filename = null;
    try {
      filename = storageService.store(file);
    }
    catch (Exception e) {

    }
    if(Strings.isNullOrEmpty(filename)) {
      response = new FailureResponse(ErrorCode.Diary.FAILED_TO_SAVE_IMAGE);
      return response;
    }

    Path filePath = storageService.load(filename);
    Diary diary = diaryService.createDiary(title, content, filePath.toString());
    response = new CreateDiaryResponse(diary);

    return response;
  }


}
