package com.shaposhnikandrii.urlshortener.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin-panel")
public class AdminController {

  @GetMapping("/login")
  public String login() {
    return "login-page";
  }

  @GetMapping("/records")
  public String records() {
    return "records";
  }

}
