package com.shaposhnikandrii.urlshortener.validator.url;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
public class UrlValidator implements ConstraintValidator<ValidUrl, String> {

  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    try {
      URL url = new URL(s);
      final String protocol = url.getProtocol();

      return "http".equals(protocol) || "https".equals(protocol);
    } catch (MalformedURLException e) {
      log.warn("Invalid url: ({})", s, e);
      return false;
    }
  }
}
