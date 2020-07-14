package com.shaposhnikandrii.urlshortener.service;

import com.shaposhnikandrii.urlshortener.domain.Base62Converter;
import com.shaposhnikandrii.urlshortener.domain.entity.UrlMappingRecord;
import com.shaposhnikandrii.urlshortener.repository.UrlMappingRecordRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UrlMappingRecordService {
  private final UrlMappingRecordRepository urlMappingRecordRepository;
  private final Base62Converter base62Converter;

  public void save(UrlMappingRecord urlMappingRecord) {
    urlMappingRecordRepository.save(urlMappingRecord);

    final String shortUrl = base62Converter.base62From10(urlMappingRecord.getId());
    urlMappingRecord.setShortUrl(shortUrl);

    urlMappingRecordRepository.save(urlMappingRecord);
  }





}