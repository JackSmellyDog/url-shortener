package com.shaposhnikandrii.urlshortener.service;

import com.shaposhnikandrii.urlshortener.domain.entity.UrlMappingRecord;
import com.shaposhnikandrii.urlshortener.util.pagination.UrlMappingRecordsPage;

public interface UrlMappingRecordService {
  UrlMappingRecord create(String url);

  UrlMappingRecord findUrlMappingRecordByHash(String hash);

  void incrementVisitorsCounter(UrlMappingRecord urlMappingRecord);

  void save(UrlMappingRecord urlMappingRecord);

  UrlMappingRecordsPage findAllOnPage(int page, int size);
}
