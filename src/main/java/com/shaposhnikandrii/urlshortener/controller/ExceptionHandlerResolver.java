package com.shaposhnikandrii.urlshortener.controller;

import com.shaposhnikandrii.urlshortener.exception.NoSuchHashException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolationException;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerResolver {

  @ExceptionHandler(ConstraintViolationException.class)
  public ModelAndView validationError(ConstraintViolationException e) {
    return new ModelAndView("main-page", "error", e.getMessage());
  }

  @ExceptionHandler(NoSuchHashException.class)
  public ModelAndView noSuchHash(NoSuchHashException e) {
    return new ModelAndView("error-404", "error", e.getMessage());
  }



}
