package com.shaposhnikandrii.urlshortener.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Accessors(chain = true)
@Table(name = "url_mapping_record")
@Entity
public class UrlMappingRecord {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "original_url", nullable = false)
  private String originalUrl;

  @Transient
  private String urlHash;

  @Column(name = "creation_date")
  private LocalDateTime creationDate;

  @Column(name = "expiration_date")
  private LocalDateTime expirationDate;

  @Column(name = "visitors", columnDefinition = "integer default 0")
  private Integer visitors;

}