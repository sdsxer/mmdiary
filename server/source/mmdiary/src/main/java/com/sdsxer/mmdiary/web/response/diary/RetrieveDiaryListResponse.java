package com.sdsxer.mmdiary.web.response.diary;

import com.sdsxer.mmdiary.domain.Diary;
import com.sdsxer.mmdiary.dto.DiaryDto;
import com.sdsxer.mmdiary.web.response.SuccessResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;

public class RetrieveDiaryListResponse extends SuccessResponse {

  private Page<DiaryDto> diaryPage;

  public RetrieveDiaryListResponse(Page<Diary> diaryPage) {
    this.diaryPage = diaryPage.map(new DiaryConverter());
  }

  class DiaryConverter implements Converter<Diary, DiaryDto> {

    @Override
    public DiaryDto convert(Diary source) {
      return new DiaryDto(source);
    }
  }
}
