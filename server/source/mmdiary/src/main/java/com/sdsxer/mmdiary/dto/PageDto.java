package com.sdsxer.mmdiary.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;

public class PageDto {

  private int totalPages;
  private long totalItems;
  private int pageSize;
  private List list = new ArrayList();

  public PageDto(Page page, Converter converter) {
    totalPages = page.getTotalPages();
    totalItems = page.getTotalElements();
    pageSize = page.getSize();
    Iterator iterator = page.iterator();
    while (iterator.hasNext()) {
      list.add(converter.convert(iterator.next()));
    }
  }

  public int getTotalPages() {
    return totalPages;
  }

  public long getTotalItems() {
    return totalItems;
  }

  public List getList() {
    return list;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }
}
