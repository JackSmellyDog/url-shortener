package com.shaposhnikandrii.urlshortener.controller;

import com.shaposhnikandrii.urlshortener.domain.entity.UrlMappingRecord;
import com.shaposhnikandrii.urlshortener.service.UrlMappingRecordService;
import com.shaposhnikandrii.urlshortener.validator.url.ValidUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
@Validated
public class RedirectionController {
  private final UrlMappingRecordService urlMappingRecordService;

  @Value("${domain-url}")
  private String domainUrl;


  @GetMapping("/")
  public String main() {
    return "main-page";
  }

  @PostMapping("/")
  public String main(Model model, @RequestParam @Valid @ValidUrl String longUrl) {
    final UrlMappingRecord urlMappingRecord = urlMappingRecordService.create(longUrl);

    model.addAttribute("longUrl", longUrl);
    model.addAttribute("shortUrl", String.format("%s/%s", domainUrl, urlMappingRecord.getUrlHash()));

    return "main-page";
  }

  @GetMapping("/{hash}")
  public String redirection(@PathVariable String hash) {
    final String originalUrl = urlMappingRecordService.findOriginalUrlByHash(hash);

    return "redirect:" + originalUrl;
  }

}
