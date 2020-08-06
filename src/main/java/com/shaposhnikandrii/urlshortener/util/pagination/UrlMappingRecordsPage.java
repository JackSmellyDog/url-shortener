package com.shaposhnikandrii.urlshortener.util.pagination;

import com.shaposhnikandrii.urlshortener.domain.entity.UrlMappingRecord;

import java.util.List;
import java.util.Objects;

public final class UrlMappingRecordsPage implements Page<UrlMappingRecord> {
  private final List<UrlMappingRecord> content;
  private final int number;
  private final int totalPages;
  private final long totalElements;

  public UrlMappingRecordsPage(List<UrlMappingRecord> content, int number, int totalPages, long totalElements) {
    Objects.requireNonNull(content);

    this.content = content;
    this.number = number;
    this.totalPages = totalPages;
    this.totalElements = totalElements;
  }

  @Override
  public List<UrlMappingRecord> getContent() {
    return content;
  }

  @Override
  public int getNumber() {
    return number;
  }

  @Override
  public int getTotalPages() {
    return totalPages;
  }

  @Override
  public long getTotalElements() {
    return totalElements;
  }
}
