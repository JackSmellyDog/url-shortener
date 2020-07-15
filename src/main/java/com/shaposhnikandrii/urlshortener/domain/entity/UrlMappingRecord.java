package com.shaposhnikandrii.urlshortener.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Accessors(chain = true)
@Table(name = "url_mapping_record")
@Entity
@DynamicUpdate
public class UrlMappingRecord {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "original_url", nullable = false)
  private String originalUrl;

  @Column(name = "url_hash")
  private String urlHash;

  @Column(name = "creation_date")
  private LocalDateTime creationDate;

  @Column(name = "expiration_date")
  private LocalDateTime expirationDate;

  @Column(name = "visitors")
  private Integer visitors;

}