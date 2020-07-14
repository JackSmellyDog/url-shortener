package com.shaposhnikandrii.urlshortener.domain;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Base62Converter {
  private static final String BASE_62_ALPHABET_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
  private static final String BASE_62_NUMBER_VALIDATION = "[0-9a-zA-Z]{1,6}";
  private static final char[] BASE_62_ALPHABET = BASE_62_ALPHABET_STRING.toCharArray();
  private static final int BASE = 62;


  public String base62From10(long base10Value) {
    StringBuilder encodedString = new StringBuilder();

    if (base10Value == 0) {
      return String.valueOf(BASE_62_ALPHABET[0]);
    }

    while (base10Value > 0) {
      encodedString.append(BASE_62_ALPHABET[(int) (base10Value % BASE)]);
      base10Value = base10Value / BASE;
    }

    return encodedString.reverse().toString();
  }

  public long base10From62(String base62Value) {
    Objects.requireNonNull(base62Value);

    if (!base62Value.matches(BASE_62_NUMBER_VALIDATION)) {
      final String message = String.format("Invalid value, string must must match this regular expression: %s", BASE_62_NUMBER_VALIDATION);
      throw new IllegalStateException(message);
    }

    char[] characters = base62Value.toCharArray();
    int length = characters.length;

    int decoded = 0;

    int counter = 1;
    for (char character : characters) {
      decoded += BASE_62_ALPHABET_STRING.indexOf(character) * Math.pow(BASE, length - counter);
      counter++;
    }

    return decoded;
  }

}
