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
  private static final String ERROR_MODEL_NAME = "error";

  @ExceptionHandler(ConstraintViolationException.class)
  public ModelAndView validationError(ConstraintViolationException e) {
    return new ModelAndView("main-page", ERROR_MODEL_NAME, e.getMessage());
  }

  @ExceptionHandler(NoSuchHashException.class)
  public ModelAndView noSuchHash(RuntimeException e) {
    return new ModelAndView("error-404", ERROR_MODEL_NAME, e.getMessage());
  }

  @ExceptionHandler
  public ModelAndView otherException(Throwable t) {
    log.error("Unexpected exception was thrown.", t);
    return new ModelAndView("main-page", ERROR_MODEL_NAME, "Something went wrong :(");
  }


}
