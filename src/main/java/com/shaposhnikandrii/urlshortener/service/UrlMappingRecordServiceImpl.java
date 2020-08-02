package com.shaposhnikandrii.urlshortener.service;

import com.shaposhnikandrii.urlshortener.domain.Base62Converter;
import com.shaposhnikandrii.urlshortener.domain.entity.UrlMappingRecord;
import com.shaposhnikandrii.urlshortener.exception.NoSuchHashException;
import com.shaposhnikandrii.urlshortener.repository.UrlMappingRecordRepository;
import com.shaposhnikandrii.urlshortener.util.pagination.UrlMappingRecordsPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UrlMappingRecordServiceImpl implements UrlMappingRecordService {
  private final UrlMappingRecordRepository urlMappingRecordRepository;
  private final Base62Converter base62Converter;

  @Value("${record.expires-in-days}")
  private Integer expiresInDays;

  @Override
  public UrlMappingRecord create(String url) {
    final LocalDateTime currentTime = LocalDateTime.now();

    final UrlMappingRecord urlMappingRecord = new UrlMappingRecord()
        .setOriginalUrl(url)
        .setCreationDate(currentTime)
        .setExpirationDate(currentTime.plusDays(expiresInDays))
        .setVisitors(0);

    urlMappingRecordRepository.save(urlMappingRecord);
    urlMappingRecord.setUrlHash(base62Converter.base62From10(urlMappingRecord.getId()));

    return urlMappingRecord;
  }

  @Override
  public void save(UrlMappingRecord urlMappingRecord) {
    urlMappingRecordRepository.save(urlMappingRecord);
  }

  @Override
  public UrlMappingRecordsPage findAllOnPage(int page, int size) {
    final Page<UrlMappingRecord> p = urlMappingRecordRepository.findAll(PageRequest.of(page - 1, size));

    final List<UrlMappingRecord> content = p.getContent();
    content.forEach(umr -> umr.setUrlHash(base62Converter.base62From10(umr.getId())));

    return new UrlMappingRecordsPage(content, p.getNumber(), p.getTotalPages(), p.getTotalElements());
  }


  @Override
  public UrlMappingRecord findUrlMappingRecordByHash(String hash) {
    final long id = base62Converter.base10From62(hash);

    return urlMappingRecordRepository.findById(id)
        .orElseThrow(NoSuchHashException::new)
        .setUrlHash(hash);
  }

  public void incrementVisitorsCounter(UrlMappingRecord urlMappingRecord) {
    urlMappingRecordRepository.incrementVisitorCounterById(urlMappingRecord.getId());
  }
}