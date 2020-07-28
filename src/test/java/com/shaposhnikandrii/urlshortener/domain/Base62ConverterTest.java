package com.shaposhnikandrii.urlshortener.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class Base62ConverterTest {

  @Autowired
  private Base62Converter base62Converter;


  @Test
  void base62() {
    for (long i = 0; i < 100_000_000; i++ ) {
      final String base62Value = base62Converter.base62From10(i);
      final long base10Value = base62Converter.base10From62(base62Value);

      assertEquals(i, base10Value);
    }

  }

}