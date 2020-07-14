package com.shaposhnikandrii.urlshortener.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Base62ConverterTest {

  @Autowired
  private Base62Converter base62Converter;


  @Test
  void base62From10() {
  }

  @Test
  void base10From62() {
  }
}