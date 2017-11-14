package com.sdsxer.mmdiary.web.response.diary;

import com.sdsxer.mmdiary.domain.Diary;
import com.sdsxer.mmdiary.dto.DiaryDto;
import com.sdsxer.mmdiary.web.response.SuccessResponse;

public class UpdateDiaryResponse extends SuccessResponse {

  private DiaryDto diary;

  public UpdateDiaryResponse(Diary diary) {
    this.diary = new DiaryDto(diary);
  }

  public DiaryDto getDiary() {
    return diary;
  }

  public void setDiary(DiaryDto diary) {
    this.diary = diary;
  }
}
