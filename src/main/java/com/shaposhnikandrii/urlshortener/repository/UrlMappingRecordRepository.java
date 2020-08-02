package com.shaposhnikandrii.urlshortener.repository;

import com.shaposhnikandrii.urlshortener.domain.entity.UrlMappingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface UrlMappingRecordRepository extends JpaRepository<UrlMappingRecord, Long> {

  @Modifying
  @Transactional
  @Query("update UrlMappingRecord umr set umr.visitors = umr.visitors + 1 where umr.id = :id")
  void incrementVisitorCounterById(Long id);

}