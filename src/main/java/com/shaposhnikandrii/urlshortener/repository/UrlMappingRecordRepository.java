package com.shaposhnikandrii.urlshortener.repository;

import com.shaposhnikandrii.urlshortener.domain.entity.UrlMappingRecord;
import org.springframework.data.repository.CrudRepository;

public interface UrlMappingRecordRepository extends CrudRepository<UrlMappingRecord, Long> {
}