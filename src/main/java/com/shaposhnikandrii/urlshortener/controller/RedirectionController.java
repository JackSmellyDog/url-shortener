package com.shaposhnikandrii.urlshortener.controller;

import com.shaposhnikandrii.urlshortener.service.UrlMappingRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class RedirectionController {
  private final UrlMappingRecordService urlMappingRecordService;


  @GetMapping("/")
  public String main() {
    return "index";
  }

  @PostMapping("/")
  public String main(Model model, @RequestParam String longUrl) {

    return "index";
  }

  @GetMapping("/{hash}")
  public String redirection(@PathVariable String hash) {
    return "redirect:" + null;
  }

}
