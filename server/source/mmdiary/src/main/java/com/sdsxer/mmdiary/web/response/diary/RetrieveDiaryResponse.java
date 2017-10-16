package com.sdsxer.mmdiary.web.response.diary;

import com.sdsxer.mmdiary.domain.Diary;
import com.sdsxer.mmdiary.dto.DiaryDto;
import com.sdsxer.mmdiary.web.response.SuccessResponse;

public class RetrieveDiaryResponse extends SuccessResponse {

  private DiaryDto diary;

  public RetrieveDiaryResponse(Diary diary) {
    this.diary = new DiaryDto(diary);
  }
}
