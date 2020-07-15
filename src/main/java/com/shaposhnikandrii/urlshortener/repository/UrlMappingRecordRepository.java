package com.shaposhnikandrii.urlshortener.repository;

import com.shaposhnikandrii.urlshortener.domain.entity.UrlMappingRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UrlMappingRecordRepository extends CrudRepository<UrlMappingRecord, Long> {
  Optional<UrlMappingRecord> findByUrlHash(String hash);
}