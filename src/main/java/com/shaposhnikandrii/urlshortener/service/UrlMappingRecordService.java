package com.shaposhnikandrii.urlshortener.service;

import com.shaposhnikandrii.urlshortener.domain.Base62Converter;
import com.shaposhnikandrii.urlshortener.domain.entity.UrlMappingRecord;
import com.shaposhnikandrii.urlshortener.exception.NoSuchHashException;
import com.shaposhnikandrii.urlshortener.repository.UrlMappingRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class UrlMappingRecordService {
  private final UrlMappingRecordRepository urlMappingRecordRepository;
  private final Base62Converter base62Converter;

  @Value("${record.expires-in-days}")
  private Integer expiresInDays;

  public UrlMappingRecord create(String url) {
    final LocalDateTime currentTime = LocalDateTime.now();

    final UrlMappingRecord urlMappingRecord = new UrlMappingRecord()
        .setOriginalUrl(url)
        .setCreationDate(currentTime)
        .setExpirationDate(currentTime.plusDays(expiresInDays))
        .setVisitors(0);

    urlMappingRecordRepository.save(urlMappingRecord);
    urlMappingRecord.setUrlHash(base62Converter.base62From10(urlMappingRecord.getId()));
    urlMappingRecordRepository.save(urlMappingRecord);

    return urlMappingRecord;
  }

  public void save(UrlMappingRecord urlMappingRecord) {
    urlMappingRecordRepository.save(urlMappingRecord);
  }

  public String findOriginalUrlByHash(String hash) {
    return urlMappingRecordRepository.findByUrlHash(hash)
        .map(UrlMappingRecord::getOriginalUrl)
        .orElseThrow(NoSuchHashException::new);
  }
}