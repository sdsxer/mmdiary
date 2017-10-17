package com.sdsxer.mmdiary.web.response.diary;

import com.sdsxer.mmdiary.domain.Diary;
import com.sdsxer.mmdiary.dto.DiaryDto;
import com.sdsxer.mmdiary.dto.PageDto;
import com.sdsxer.mmdiary.web.response.SuccessResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;

public class RetrieveDiaryListResponse extends SuccessResponse {

  private PageDto page;

  public RetrieveDiaryListResponse(Page<Diary> diaryPage) {
    this.page = new PageDto(diaryPage, new DiaryConverter());
  }

  public PageDto getPage() {
    return page;
  }

  class DiaryConverter implements Converter<Diary, DiaryDto> {

    @Override
    public DiaryDto convert(Diary source) {
      return new DiaryDto(source);
    }
  }
}
