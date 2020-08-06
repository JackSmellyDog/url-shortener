package com.shaposhnikandrii.urlshortener.controller;

import com.shaposhnikandrii.urlshortener.service.UrlMappingRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin-panel")
@RequiredArgsConstructor
public class AdminController {
  private final UrlMappingRecordService urlMappingRecordService;


  @GetMapping("/login")
  public String login() {
    return "login-page";
  }

  @GetMapping("/records")
  public String records(Model model,
                        @RequestParam(value = "page", defaultValue = "1") Integer page,
                        @RequestParam(value = "size", defaultValue = "2") Integer size) {

    model.addAttribute("records", urlMappingRecordService.findAllOnPage(page, size));
    return "records";
  }

}
