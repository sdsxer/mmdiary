package com.sdsxer.mmdiary.web.response.diary;

import com.sdsxer.mmdiary.domain.Diary;
import com.sdsxer.mmdiary.dto.DiaryDto;
import com.sdsxer.mmdiary.web.response.SuccessResponse;

public class CreateDiaryResponse extends SuccessResponse {

  private DiaryDto diary;

  public CreateDiaryResponse(Diary diary) {
    this.diary = new DiaryDto(diary);
  }

  public DiaryDto getDiary() {
    return diary;
  }

  public void setDiary(DiaryDto diary) {
    this.diary = diary;
  }
}
